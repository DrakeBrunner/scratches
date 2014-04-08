package mizunonLab2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TCPNumberClient {
    private String serverAddress = "127.0.0.1";

    private float f1, f2, answer;
    private Socket socket = null;
    private DataInputStream dis = null;
    private DataOutputStream dos = null;

    public TCPNumberClient() {
        inputIPAddress();

        createSocket();
        getStreams();
        inputFloat();
        sendNumbers();
        receiveNumber();

        System.out.println(answer);

        closeAll();
    }

    public void inputFloat() {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter first number: ");
        f1 = in.nextFloat();
        System.out.print("Enter second number: ");
        f2 = in.nextFloat();
    }

    public void inputIPAddress() {
        Scanner in = new Scanner(System.in);
        System.out.print("Input IP address: ");

        String input = in.nextLine();
        if (!input.isEmpty()) {
            serverAddress = input;
            String message = String.format(
                    "Server's IP address was changed to %s", serverAddress);
            System.out.println(message);
        }
    }

    public void createSocket() {
        try {
            socket = new Socket(InetAddress.getByName(serverAddress),
                    TCPNumberServer.SERVER_PORT);
            // socket = new Socket(InetAddress.getLocalHost(),
            // TCPNumberServer.SERVER_PORT);
        }
        catch (UnknownHostException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getStreams() {
        try {
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendNumbers() {
        try {
            dos.writeFloat(f1);
            dos.writeFloat(f2);
            dos.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void receiveNumber() {
        try {
            answer = dis.readFloat();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeAll() {
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
