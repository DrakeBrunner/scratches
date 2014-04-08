package mizunonProject2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class BattleshipServer implements Runnable {
    private ServerSocket serverSocket = null;
    private Socket clientSocket = null;

    public BattleshipServer() {

        createServerSocket(BattleshipGame.PORT_NUMBER);

        while (true) {
            try {
                listen();
            }
            catch (IOException e) {
                e.printStackTrace();
            }

            String hostName = clientSocket.getInetAddress().getHostName();
            String hostAddress = clientSocket.getInetAddress().getHostAddress();
            System.out.println(String.format("Connection made with: %s (%s)",
                    hostAddress, hostName));
            Thread thread = new Thread(this);
            thread.start();
        }
    }

    /**
     * Starts the server listening to the given port number.
     * 
     * @param port
     *            The port number to listen to.
     */
    public void createServerSocket(int port) {
        try {
            serverSocket = new ServerSocket(port);
            String message =
                    String.format("Server has started on port %d", port);
            System.out.println(message);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Listens to the port until a connection is made. Once a connection is
     * made, DataInputStream and DataOutputStream are obtained from the socket.
     */
    public void listen() throws IOException {
        clientSocket = serverSocket.accept();
    }

    @Override
    public void run() {
        new BattleshipServerThread(clientSocket);
    }

    public static void main(String[] args) {
        new BattleshipServer();
    }
}