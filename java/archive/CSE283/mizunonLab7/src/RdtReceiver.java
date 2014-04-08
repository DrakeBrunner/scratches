import java.io.*;
import java.net.*;

/**
 * Implementation of the receiver side of RDT 3.0 from "Computer Networking" by
 * James F. Kurose and Keith Ross.
 * 
 */
public class RdtReceiver {

    /**
     * Default port number for receiving packets from a Rdt3pt0Sender Object
     */
    public static final int DEFAULT_PORT = 32100;

    public static final int PACKET_SIZE = RdtSender.MAX_DATA_IN_PACKET
            + RdtSender.NUMBER_OF_OVERHEAD_BYTES;

    // Private or protected data members
    DatagramSocket dgSock;

    // Indicates the sequence number of the next packet
    // Alternates between 0 and 1
    short expectedSequence = 0;

    private InetAddress sourceAddress = null;
    private int sourcePort = -1;

    /**
     * Creates a "reliable connection" using the default port number. Objects of
     * this class are used to receive data.
     * 
     * @param serverPort
     *            port number for the server to listen on
     * @throws SocketException
     */
    public RdtReceiver() throws SocketException {
        this(DEFAULT_PORT);

    } // end default constructor

    /**
     * Creates a "reliable connection" using a specified port number. Objects of
     * this class are used to receive data.
     * 
     * @param serverPort
     *            port number for the server to listen on
     * @throws SocketException
     */
    public RdtReceiver(int serverPort) throws SocketException {
        dgSock = new UDatagramSocket(serverPort);

    } // end Rdt3pt0Receiver

    /**
     * Returns received data via a byte array. When the method returns the array
     * will contain the received data. The byte array will be null when the
     * connection is closed by the the sender.
     * 
     * @return data or null if the connection is closed.
     * @throws IOException
     */
    public byte[] rdt_receive() throws IOException {
        do {
            byte[] arr = new byte[PACKET_SIZE];
            DatagramPacket packet = new DatagramPacket(arr, arr.length);
            dgSock.receive(packet);

            // Ignore packets from different IP addresses
            if (sourceAddress == null) {
                sourceAddress = packet.getAddress();
                sourcePort = packet.getPort();
            }
            else if (!packet.getAddress().getHostAddress()
                    .equals(sourceAddress.getHostAddress())
                    || sourcePort != packet.getPort())
                return new byte[0];

            int dataBytesReceived = packet.getLength()
                    - RdtSender.NUMBER_OF_OVERHEAD_BYTES;

            byte[] dataReceived = new byte[dataBytesReceived];
            ByteArrayInputStream bais = new ByteArrayInputStream(
                    packet.getData());
            DataInputStream dis = new DataInputStream(bais);

            // Get the actual data
            dis.read(dataReceived);

            // Get sequence number
            short receivedSequence = dis.readShort();

            // Get the checksum
            short negCheckSum = dis.readShort();
            /*
             * Calculated the checksum from the what was received.
             * However, the ByteArrayOutputStream needs to be reset because the
             * sequence number has already been read and will not be taken into
             * account otherwise.
             */
            bais.reset();
            // Get received data excluding the checksum
            byte[] toCheck = new byte[packet.getLength() - 2];
            dis.read(toCheck);
            short checkSumOfData = CheckSum.calculateChecksum(toCheck);

            // Received packet is good
            if (receivedSequence == expectedSequence
                    && negCheckSum + checkSumOfData == 0) {

                // Send ACK for the sequence number of received packet
                sendAck(receivedSequence, packet.getSocketAddress());

                // Flip expected sequence number
                expectedSequence = (short) (expectedSequence == 1 ? 0 : 1);

                // Empty packet means it's the last packet
                if (dataBytesReceived == 0)
                    return null;
                else
                    return dataReceived;
            }
            // Something went wrong so ACK the last good packet
            else {
                sendAck((short) (expectedSequence == 1 ? 0 : 1),
                        packet.getSocketAddress());
            }

            // Do this until good packet is received
        } while (true);

    } // end rdt_receive

    /**
     * Creates and acknowledgment message for the supplied sequence number and
     * sends it.
     * 
     * @param seq
     *            sequence number being acknowledged.
     * @param sender
     *            IP address and port number to which the acknowledgment is
     *            being sent
     * @throws IOException
     */
    public void sendAck(short seq, SocketAddress sender) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);

        try {
            dos.writeShort(seq);

            dos.writeShort(CheckSum.calculateNegativeChecksum(baos
                    .toByteArray()));

            DatagramPacket packet = new DatagramPacket(baos.toByteArray(),
                    baos.size(), sender);

            dgSock.send(packet);

        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    } // end sendAck

    /**
     * Closes the connection.
     */
    public void close() {
        dgSock.close();

        // Reset connection info
        sourceAddress = null;
        sourcePort = -1;

    } // end close

} // end RdtReceiver class
