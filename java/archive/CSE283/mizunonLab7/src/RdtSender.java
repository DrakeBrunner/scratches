import java.io.*;
import java.net.*;
import java.io.*;
import java.net.*;
import java.io.*;
import java.net.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Implementation of the RDT 3.0 protocol from "Computer Networking" by James F.
 * Kurose and Keith Ross.
 * 
 */
public class RdtSender {

    /**
     * Default maximum packet size in bytes including the header
     */
    static final int MAX_DATA_IN_PACKET = 50;

    static final int NUMBER_OF_OVERHEAD_BYTES = 4;

    // Socket for sending and receiving data
    DatagramSocket dgSock;

    // IP address and port number to which to send all data
    InetSocketAddress destination;

    // Indicates the sequence number of the next packet to send
    // Alternates between 0 and 1
    short currentSequence = 0;

    /**
     * Creates a "reliable connection" to the specified address and port number.
     * Objects of this class will be used to send data.
     * 
     * @param destinationAddress
     *            IP address of the receiving host
     * @param destinationPort
     *            port number for the receiving application
     */
    public RdtSender(String destinationAddress, int destinationPort) {
        try {
            dgSock = new UDatagramSocket();
            dgSock.setSoTimeout(10);
            destination = new InetSocketAddress(destinationAddress,
                    destinationPort);
        }
        catch (SocketException e) {
            e.printStackTrace();
        }

    } // end SelectiveRepeatSender constructor

    /**
     * Accepts data contained in a byte array to be transmitted
     * 
     * Returns true if the packets was sent successfully and received.
     * 
     * @param data
     *            byte array containing data to be sent.
     * @return true if the data was accepted for transmission false if the data
     *         was not accepted
     * @throws IOException
     */
    public boolean rdt_send(byte[] data) throws IOException {
        do {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataOutputStream dos = new DataOutputStream(baos);

            // Write the actual data
            dos.write(data);

            // Write sequence number
            dos.writeShort(currentSequence);

            // Write the checksum AFTER the actual data
            short checkSum = CheckSum.calculateNegativeChecksum(baos
                    .toByteArray());
            dos.writeShort(checkSum);

            // Send data
            byte[] dataToSend = baos.toByteArray();
            dgSock.send(new DatagramPacket(dataToSend,
                    dataToSend.length, destination));

            // Wait for ACK
        } while (!receiveAck(currentSequence));

        currentSequence = (short) (currentSequence == 1 ? 0 : 1);
        return true;
    } // end rdt_send

    /**
     * Waits for and acknowledgment to be received. Returns true if and
     * acknowledgment for the desired sequence number as specified by the input
     * parameter is received.
     * 
     * @param seq
     *            desired sequence number to be acknowledged
     * 
     * @return false if the acknowledgment received does not match the sequence
     *         number parameter or a timeout occurs. Otherwise it returns true.
     */
    public boolean receiveAck(short seq) {

        DatagramPacket pack = new DatagramPacket(
                new byte[NUMBER_OF_OVERHEAD_BYTES + 2],
                NUMBER_OF_OVERHEAD_BYTES + 2);

        try {
            dgSock.receive(pack);

            ByteArrayInputStream bais = new ByteArrayInputStream(pack.getData());
            DataInputStream dis = new DataInputStream(bais);

            short acknowledgedSequenceNumber = dis.readShort();
            short negCkSm = dis.readShort();

            bais.reset();
            byte[] sequenceBytes = new byte[2];
            dis.read(sequenceBytes);

            if ((negCkSm + CheckSum.calculateChecksum(sequenceBytes) == 0)
                    && (acknowledgedSequenceNumber == seq))
                return true;
            else
                return false;

        }
        catch (SocketTimeoutException e) {
            return false;
        }
        catch (IOException e) {
            return false;
        }

    } // end receiveAck

    /**
     * Closes the connection by sending an empty message to the receiver. The
     * empty message still contains a checksum and sequence number.
     * 
     * @throws IOException
     * 
     */
    public void close() throws IOException {
        rdt_send(new byte[0]);
        dgSock.close();
    } // end close

} // end SelectiveRepeatSender class
