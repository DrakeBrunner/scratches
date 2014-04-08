package mizunonLab3;

import java.io.*;
import java.net.*;

public class UDPServer {

    private DatagramSocket ds = null;
    private DatagramPacket dp = null;

    private float f1 = 0f, f2 = 0f;

    public static final int SERVER_PORT = 32150;

    public UDPServer() {
        printLocalIPAddressAndPort();
        createSocket();

        while (true) {
            try {
                receiveFloats();
            }
            catch (IOException e) {
                e.printStackTrace();
            }

            // Debug
            System.out.println(String.format("first: %s", f1));
            System.out.println(String.format("second: %s", f2));

            replyFloat(f1 * f2);
            if (f1 < 0 || f2 < 0)
                break;
        }
    }

    /**
     * Initializes the DatagramSocket instance variable with the defined port
     * number.
     */
    public void createSocket() {
        try {
            ds = new DatagramSocket(SERVER_PORT);
        }
        catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public void replyFloat(float f) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);

        try {
            dos.writeFloat(f);
            dos.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        byte[] byteArray = baos.toByteArray();
        dp = new DatagramPacket(byteArray, byteArray.length,
                dp.getAddress(), dp.getPort());
        // Send data
        try {
            ds.send(dp);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Waits until the socket receives data and then read in two floats.
     * 
     * @throws IOException
     *             When it fails to read float from the data received.
     */
    public void receiveFloats() throws IOException {
        dp = new DatagramPacket(new byte[8], 8);

        // Receive
        try {
            ds.receive(dp);
            System.out.println("Packet received from: " + dp.getAddress());
            System.out.println("Length is: " + dp.getLength());
        }
        catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error when receiving data");
        }

        ByteArrayInputStream bais = new ByteArrayInputStream(dp.getData());
        DataInputStream dis = new DataInputStream(bais);

        f1 = dis.readFloat();
        f2 = dis.readFloat();
    }

    public void printLocalIPAddressAndPort() {
        try {
            System.out.println(String.format("IP: %s",
                    InetAddress.getLocalHost().getHostAddress()));
            System.out.println(String.format("Port: %d", SERVER_PORT));
        }
        catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new UDPServer();
    }
}
