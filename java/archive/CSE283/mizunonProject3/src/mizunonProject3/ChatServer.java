package mizunonProject3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This class is the server for the P2P chat application.
 * It has the information of the currently connected hosts and handles
 * requests from hosts such as retrieving the current list of connected
 * hosts, opening/closing connections.
 * 
 * @author Naoki Mizuno
 */

public class ChatServer {
    private ServerSocket server = null;
    private Socket socket = null;

    /* Stores screen and IP, port of connected hosts */
    private ConcurrentHashMap<String, InetSocketAddress> clients;

    public static final int SERVER_PORT = 32100;

    /* Requests that the peers can ask to server */
    public static final int INVALID = 0;
    public static final int ADD = 1;
    public static final int REMOVE = 2;
    public static final int RETRIEVE_LIST = 3;

    public static final boolean DEBUG = true;

    /**
     * Creates a new ServerSocket and waits for requests from peers.
     */
    public ChatServer() {
        try {
            server = new ServerSocket(SERVER_PORT);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        // Initialize HashMap that stores connected hosts
        clients = new ConcurrentHashMap<String, InetSocketAddress>();

        while (true)
            waitForConnection();
    }

    /**
     * Waits for a connection from a host. Once a connection is made, this
     * method starts a new thread that responds to the host's request.
     */
    public void waitForConnection() {
        try {
            socket = server.accept();

            // Create new thread
            new ClientHandler(socket).start();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles connection from one client in a new thread.
     * 
     * @author Naoki Mizuno
     */
    class ClientHandler extends Thread {
        private Socket socket;
        private DataInputStream dis;
        private DataOutputStream dos;

        public ClientHandler(Socket socket) {
            this.socket = socket;

            // Get streams
            try {
                dis = new DataInputStream(socket.getInputStream());
                dos = new DataOutputStream(socket.getOutputStream());
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * Respond to host's request according to the request code.
         */
        @Override
        public void run() {
            int request = getRequest();

            switch (request) {
                case ADD:
                    addClient();
                    break;
                case REMOVE:
                    removeClient();
                    break;
                case RETRIEVE_LIST:
                    sendList();
                    break;
                default:
                    String host = socket.getInetAddress().getHostAddress();
                    String mes = String.format("Invalid request from %s", host);
                    System.err.println(mes);
            }

            closeConnection();
        }

        /**
         * Receives the type of request from host.
         * 
         * @return The type of request.
         */
        public int getRequest() {
            int requestCode = INVALID;

            try {
                requestCode = dis.readInt();
            }
            catch (IOException e) {
                e.printStackTrace();
            }

            return requestCode;
        }

        /**
         * Listens to and adds the received host to the list of currently
         * connected peers.
         */
        public void addClient() {
            String screen = getScreen();
            // Get client's IP
            byte[] ip = getIP();
            // Get client's port
            int port = getPort();
            InetSocketAddress isa = null;
            try {
                // Create InetSocketAddress from IP and port
                isa = new InetSocketAddress(InetAddress.getByAddress(ip), port);
            }
            catch (UnknownHostException e) {
                e.printStackTrace();
            }

            // Add those information to the HashMap
            clients.put(screen, isa);

            if (DEBUG)
                System.out.println(String.format("Added %s from %s (%s:%s)",
                        screen, isa.getHostName(),
                        isa.getAddress().getHostAddress(), isa.getPort()));
        }

        /**
         * Listens to and removes the received screen name from the list of
         * hosts.
         */
        public void removeClient() {
            String screen = getScreen();
            clients.remove(screen);

            if (DEBUG)
                System.out.println(String.format("Removed %s", screen));
        }

        /**
         * Receives the screen name from the connected host.
         * 
         * @return The screen name of the host.
         */
        public String getScreen() {
            String screen = "Invalid";
            try {
                screen = dis.readUTF();
            }
            catch (IOException e) {
                e.printStackTrace();
                System.err.println("Invalid screen name!");
            }

            return screen;
        }

        /**
         * Receives the IP address that the client is using. This is likely the
         * same IP address that is used when the client sent the request, but
         * it's received again just to make sure.
         * 
         * @return The IP address that the client is using.
         */
        public byte[] getIP() {
            byte[] ip = new byte[4];
            try {
                dis.read(ip);
            }
            catch (IOException e) {
                e.printStackTrace();
            }

            return ip;
        }

        /**
         * Receives the port number that the client is using.
         * 
         * @return The port number that the client is using for chat.
         */
        public int getPort() {
            int port = -1;
            try {
                port = dis.readInt();
            }
            catch (IOException e) {
                e.printStackTrace();
            }

            return port;
        }

        /**
         * Sends the list of currently connected peers.
         */
        public void sendList() {
            // First send the total number of hosts connected
            try {
                dos.writeInt(clients.size());
            }
            catch (IOException e1) {
                e1.printStackTrace();
            }

            Iterator<String> screens = clients.keySet().iterator();

            // Send each entry in the list of currently connected hosts
            while (screens.hasNext()) {
                String screen = screens.next();
                InetSocketAddress isa = clients.get(screen);
                byte[] ip = isa.getAddress().getAddress();

                try {
                    // Send screen
                    dos.writeUTF(screen);
                    // Send IP address
                    dos.write(ip);
                    // Send port number
                    dos.writeInt(isa.getPort());

                    dos.flush();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        /**
         * Closes connection with the client it's currently talking to.
         */
        public void closeConnection() {
            try {
                dis.close();
                dos.close();
                socket.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        new ChatServer();
    }
}