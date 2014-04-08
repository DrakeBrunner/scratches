package mizunonLab2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPNumberServer {
    public static final int SERVER_PORT = 32150;
    private ServerSocket serverSocket = null;
    private Socket clientSocket = null;
    private DataInputStream dis = null;
    private DataOutputStream dos = null;

    public TCPNumberServer() {
        createServerSocket();
        displayContactInfo();
        // handleOneClient();
        listenForClients();
        // closeServer();
    }

    protected void createServerSocket() {
        try {
            serverSocket = new ServerSocket(SERVER_PORT);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void displayContactInfo() {
        System.out.println("Number Server standing by to accept Clients:");
        try {
            System.out.println(String.format("IP: %s",
                    InetAddress.getLocalHost()));
        }
        catch (UnknownHostException e) {
            // Should happen only when host machine doesn't have an IP address
            e.printStackTrace();
        }
        System.out.println(String.format("Port: %s",
                serverSocket.getLocalPort()));
        System.out.println();
    }

    protected void listenForClients() {
        while (!serverSocket.isClosed()) {
            handleOneClient();
            // System.out.println("handleOneClient() was called");
        }
    }

    protected void handleOneClient() {
        try {
            clientSocket = serverSocket.accept();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        createClientStreams();
        sendAndReceiveNumbers();
        closeClientConnection();
    }

    protected void createClientStreams() {
        try {
            dis = new DataInputStream(clientSocket.getInputStream());
            dos = new DataOutputStream(clientSocket.getOutputStream());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    protected void sendAndReceiveNumbers() {
        float product = 0, f1 = 0, f2 = 0;

        // Read from DataInputStream
        try {
            f1 = dis.readFloat();
            f2 = dis.readFloat();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        product = f1 * f2;

        // Write using DataOutputStream
        try {
            dos.writeFloat(product);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        // Close connection if a negative number is received
        if (f1 < 0 || f2 < 0) {
            // No need for this because it is called in handleOneClient()
            // closeClientConnection();
            closeServer();
            // Better to close client connection before closing server's socket?
        }
    }

    protected void closeClientConnection() {
        try {
            clientSocket.close();
            System.out.println("Client's socket was closed!");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void closeServer() {
        try {
            serverSocket.close();
            System.out.println("Server was closed!");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
