package mizunonProject3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import javax.swing.JOptionPane;

/**
 * This class is run by peers. It contacts the server every certain period of
 * time and updates the list of peers that are currently connected. This class
 * listens to connections from other peers and starts a new GUI window when
 * connecting to another peer. It contacts the server when joining and leaving
 * the network.
 * 
 * @author Naoki Mizuno
 * 
 */

public class ChatPeer implements ChatPeerInterfaceListener {
    private ChatPeerInterface chatPeerInterface;

    private ServerSocket serverSocket = null;
    private Socket socket = null;
    /* HashMap of peers retrieved from server */
    private HashMap<String, InetSocketAddress> peers;
    /* List of peers that this host is connected to */
    private ArrayList<ChatThread> connections;

    /* Information about this host */
    private String screen;

    public static final String SERVER_IP = "127.0.0.1";

    /* Becomes true when quit() is called and program needs to be closed */
    private boolean closeFlag = false;

    /**
     * Contacts the server, starts the GUI, and listens to connections from
     * other peers.
     */
    public ChatPeer() {
        try {
            // Bind to random port
            serverSocket = new ServerSocket(0);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        peers = new HashMap<String, InetSocketAddress>();
        connections = new ArrayList<ChatThread>();

        screen = inputScreen();
        // Add this host to the server
        addToServer();

        // Create GUI (this also updates the HashMap of connected hosts
        chatPeerInterface = new ChatPeerInterface(this, screen);

        // Wait for connection until quit() is called and program exits
        while (closeFlag == false) {
            try {
                socket = serverSocket.accept();
            }
            catch (IOException e) {
                e.printStackTrace();
            }

            // Start chat
            connections.add(new ChatThread(screen, socket));
        }
    }

    /**
     * Requests to the server to add this host to it's list of connected peers.
     * The screen name must be set before calling this method. Also,
     * ServerSocket also needs to be binded before calling this method.
     */
    private void addToServer() {
        try {
            Socket socket = new Socket(SERVER_IP, ChatServer.SERVER_PORT);

            requestToServer(socket, ChatServer.ADD);
            sendScreen(socket, screen);
            sendIP(socket, socket.getLocalAddress().getAddress());
            sendPort(socket, serverSocket.getLocalPort());

            socket.close();
        }
        catch (UnknownHostException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Requests the server to remove this host from it's list of currently
     * connected peers. The screen name must be set before calling this method.
     */
    private void removeFromServer() {
        try {
            Socket socket = new Socket(SERVER_IP, ChatServer.SERVER_PORT);
            requestToServer(socket, ChatServer.REMOVE);
            sendScreen(socket, screen);
            socket.close();
        }
        catch (UnknownHostException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sends a request to the server.
     * 
     * @param socket
     *            The socket connection to the server.
     * @param requestCode
     *            Type of request.
     */
    public void requestToServer(Socket socket, int requestCode) {
        try {
            DataOutputStream dos =
                    new DataOutputStream(socket.getOutputStream());
            dos.writeInt(requestCode);
            dos.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sends the screen name to the server.
     * 
     * @param socket
     *            The socket connection to the server.
     * @param screen
     *            The screen name to be sent to the server.
     */
    public void sendScreen(Socket socket, String screen) {
        try {
            DataOutputStream dos =
                    new DataOutputStream(socket.getOutputStream());
            dos.writeUTF(screen);
            dos.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sends the host's IP address to the server.
     * 
     * @param socket
     *            The socket connection to the server.
     * @param ip
     *            The IP address of this host.
     */
    public void sendIP(Socket socket, byte[] ip) {
        try {
            DataOutputStream dos =
                    new DataOutputStream(socket.getOutputStream());
            dos.write(ip);
            dos.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sends the port number that the host uses for listening to connections
     * from other peers.
     * 
     * @param socket
     *            The socket connection to the server.
     * @param port
     *            The port number that the host is using to listen to
     *            connections from peers.
     */
    public void sendPort(Socket socket, int port) {
        try {
            DataOutputStream dos =
                    new DataOutputStream(socket.getOutputStream());
            dos.writeInt(port);
            dos.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows a JOptionPane and prompts for a screen name. Screen name must not
     * be empty.
     * 
     * @return The screen name that the user input.
     */
    public String inputScreen() {
        String name = JOptionPane.showInputDialog("Enter Screen Name");

        while (name == null || name.isEmpty()) {
            String mes = "Invalid Screen Name. Enter again";
            name = JOptionPane.showInputDialog(mes);
        }

        return name;
    }

    @Override
    public void contactFriend(String friendName, int friendIndex) {
        InetSocketAddress socketAddress = peers.get(friendName);

        try {
            Socket socket = new Socket(socketAddress.getAddress(),
                    socketAddress.getPort());
            connections.add(new ChatThread(screen, socket));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void quit() {
        removeFromServer();

        // Close all connections before closing the friends list GUI
        for (int i = 0; i < connections.size(); i++) {
            if (connections.get(i).isAlive())
                connections.get(i).quit();
        }

        closeFlag = true;
        System.exit(0);
    }

    @Override
    public void updateFriendList() {
        if (peers != null)
            peers.clear();

        try {
            Socket socket = new Socket(SERVER_IP, ChatServer.SERVER_PORT);
            requestToServer(socket, ChatServer.RETRIEVE_LIST);

            DataInputStream dis = new DataInputStream(socket.getInputStream());

            // First, receive the size
            int size = dis.readInt();

            for (int i = 0; i < size; i++) {
                // Get screen
                String screen = dis.readUTF();
                // Get IP
                byte[] ip = new byte[4];
                dis.read(ip);
                // Get port
                int port = dis.readInt();

                InetSocketAddress peer = new InetSocketAddress(
                        InetAddress.getByAddress(ip), port);

                // Add to hash
                peers.put(screen, peer);
            }
        }
        catch (UnknownHostException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        // Add retrieved peers to the list of the GUI
        chatPeerInterface.clearList();
        Iterator<String> screens = peers.keySet().iterator();
        int i = 0;
        // Only add other friends
        while (screens.hasNext()) {
            String peerScreen = screens.next();
            if (!peerScreen.equals(screen))
                chatPeerInterface.addFriendToList(peerScreen, i);
            i++;
        }
    }

    public static void main(String[] args) {
        new ChatPeer();
    }
}