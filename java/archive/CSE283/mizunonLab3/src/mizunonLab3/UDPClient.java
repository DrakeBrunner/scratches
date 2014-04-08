package mizunonLab3;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class UDPClient {

    private DatagramSocket ds = null;

    Scanner in = new Scanner(System.in);

    public UDPClient() {
        createSocket();

        System.out.print("Enter IP address: ");
        String ipAddress = inputIPAddress();

        System.out.println("Enter 2 floats:");
        sendFloats(ipAddress, in.nextFloat(), in.nextFloat());

        try {
            System.out.println(receiveFloat());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializes the DatagramSocket instance variable with the the port number
     * chosen by the OS.
     */
    public void createSocket() {
        try {
            ds = new DatagramSocket();
        }
        catch (SocketException e) {
            e.printStackTrace();
        }
    }

    /**
     * Asks the user for an IP address. 127.0.0.1 is used when nothing is
     * entered.
     * 
     * @return
     */
    public String inputIPAddress() {
        System.out.print("Input IP address: ");
        String ipAddress = in.nextLine();
        if (ipAddress.isEmpty())
            ipAddress = "127.0.0.1";

        return ipAddress;
    }

    public void sendFloats(String ipAddress, float f1, float f2) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);

        DatagramPacket packet = null;

        try {
            dos.writeFloat(f1);
            dos.writeFloat(f2);
            dos.flush();

            byte[] byteArray = baos.toByteArray();
            packet = new DatagramPacket(byteArray, byteArray.length,
                    InetAddress.getByName(ipAddress), UDPServer.SERVER_PORT);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        //packet.setData(baos.toByteArray());

        // Send data
        try {
            ds.send(packet);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Waits until the socket receives data and then read in one float.
     * 
     * @return One float that was received.
     * @throws IOException
     *             When it fails to read float from the data received.
     */
    public float receiveFloat() throws IOException {
        DatagramPacket dp = new DatagramPacket(new byte[4], 4);
        // Receive
        try {
            ds.receive(dp);
        }
        catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error when receiving data");
        }

        ByteArrayInputStream bais = new ByteArrayInputStream(dp.getData());
        DataInputStream dis = new DataInputStream(bais);

        return dis.readFloat();
    }

    public static void main(String[] args) {
        new UDPClient();
    }
}
