package mizunonLab6;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class DerbyUDPServer {

    // Socket for UDP communication with clients
    DatagramSocket dgs = null;

    // Total number of clients server has sent data to
    int clientCount = 0;

    // Port number that server listens on
    static final int SERVER_PORT = 31250;

    // Array list containing a list of strings describing
    // Kentucky derby winners
    ArrayList<String> winners = new ArrayList<String>();

    public DerbyUDPServer() throws IOException {
        dgs = new DatagramSocket(SERVER_PORT);
        readInDataFile();
        displayContactInfo();

        while (true) {
            handleOneClient();
        }
    } // end DerbyUDPServer constructor

    protected void readInDataFile() throws IOException {
        String singleWinner;

        BufferedReader in = new BufferedReader(new FileReader("derby.txt"));

        while ((singleWinner = in.readLine()) != null)
            winners.add(singleWinner);

        in.close();

    } // end readInDataFile

    /*
     * Displays IP address and port number that clients can use to contact the
     * server to the console.
     */
    protected void displayContactInfo() {
        try {
            // Display contact information.
            System.out.println("Number Server standing by to accept Clients:"
                    + "\nIP : " + InetAddress.getLocalHost() + "\nPort: "
                    + dgs.getLocalPort() + "\n\n");

        }
        catch (UnknownHostException e) {
            // NS lookup for host IP failed?
            // This should only happen if the host machine does
            // not have an IP address.
            e.printStackTrace();
        }

    } // end displayContactInfo

    public void handleOneClient() {

        // Prepare to receive data
        byte[] buf = new byte[6];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);

        try {
            dgs.receive(packet);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        // Read received data
        ByteArrayInputStream bais = new ByteArrayInputStream(packet.getData());
        DataInputStream dis = new DataInputStream(bais);
        int year = -1;
        try {
            year = Integer.parseInt(dis.readUTF());
        }
        catch (NumberFormatException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        String yearLine = getYearLine(year);

        sendLineLength(yearLine.length() + 2,
                packet.getAddress(), packet.getPort());
        sendBackLine(yearLine, packet.getAddress(), packet.getPort());

        // TODO: what if exception happened and data not sent?
        clientCount++;
    }

    public String getYearLine(int year) {

        if (year < 2012 && year > 1874)
            return winners.get(2011 - year);
        else
            return "No derby info for that year";

    } // getYearLine

    public void sendLineLength(int length, InetAddress destination, int port) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream(4);
        DataOutputStream dos = new DataOutputStream(baos);
        try {
            dos.writeInt(length);
            dos.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        byte[] buf = baos.toByteArray();
        DatagramPacket lengthData = new DatagramPacket(buf, buf.length,
                destination, port);
        try {
            dgs.send(lengthData);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        // TODO: check if client got the length correctly?

    }

    public void sendBackLine(String yearLine, InetAddress destination, int port) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream(
                yearLine.length() + 2);
        DataOutputStream dos = new DataOutputStream(baos);
        try {
            dos.writeUTF(yearLine);
            dos.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        byte[] dataArray = baos.toByteArray();
        DatagramPacket data = new DatagramPacket(dataArray, dataArray.length,
                destination, port);
        try {
            dgs.send(data);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        new DerbyUDPServer();

    } // end main

} // end DerbyUDPServer
