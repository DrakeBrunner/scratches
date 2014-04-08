package mizunonLab6;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class DerbyUDPClient {

    DatagramSocket dgs = null;

    public DerbyUDPClient() {
        try {
            dgs = new DatagramSocket();
        }
        catch (SocketException e) {
            e.printStackTrace();
        }

        String ipAddress = inputServerIP();
        String year = inputYear();

        sendYear(year, ipAddress, DerbyUDPServer.SERVER_PORT);

        int length = receiveLengthOfString();
        String stringLine = receiveStringLine(length);

        System.out.println(stringLine);
    }

    String inputServerIP() {
        System.out.print("Ender IP address of the server: ");
        Scanner input = new Scanner(System.in);
        String ipAddress = input.nextLine();

        if (ipAddress.isEmpty())
            ipAddress = "127.0.0.1";

        return ipAddress;
    }

    /**
     * Prompts the user for a year between 1875 and 2011. This method does not
     * check for the validity of the data.
     * 
     * @return The input year in String.
     */
    String inputYear() {
        System.out.println("Enter year between 1875 and 2011: ");
        Scanner input = new Scanner(System.in);
        String year = input.nextLine();

        return year;
    }

    void sendYear(String year, String ipAddress, int portNumber) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        try {
            dos.writeUTF(year);
            dos.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        byte[] buf = baos.toByteArray();
        try {
            DatagramPacket packet = new DatagramPacket(buf, buf.length,
                    InetAddress.getByName(ipAddress), portNumber);
            dgs.send(packet);
        }
        catch (UnknownHostException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    int receiveLengthOfString() {
        byte[] lengthArray = new byte[4];
        DatagramPacket packet = new DatagramPacket(lengthArray,
                lengthArray.length);
        try {
            dgs.receive(packet);
        }
        catch (IOException e1) {
            e1.printStackTrace();
        }

        ByteArrayInputStream bais = new ByteArrayInputStream(packet.getData());
        DataInputStream dis = new DataInputStream(bais);
        int length = -1;
        try {
            length = dis.readInt();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return length;
    }

    String receiveStringLine(int length) {
        byte[] buf = new byte[length];
        DatagramPacket packet = new DatagramPacket(buf, length);
        try {
            dgs.receive(packet);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        ByteArrayInputStream bais = new ByteArrayInputStream(packet.getData());
        DataInputStream dis = new DataInputStream(bais);

        String ret = "";
        try {
            ret = dis.readUTF();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return ret;
    }

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        new DerbyUDPClient();
    } // end main

} // end DerbyUDPClient
