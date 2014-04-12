import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.ArrayList;

import spaceWar.Constants;
import spaceWar.SpaceCraft;

/**
 * Class to receive and forward UDP packets containing
 * updates from clients. In addition, it checks for
 * collisions caused by client movements and sends
 * appropriate removal information.
 * 
 * @author bachmaer
 */
class BestEffortServer extends Thread {

    // Socket through which all client UDP messages are received
    protected DatagramSocket gamePlaySocket = null;

    // Reference to the server which holds the sector to be updated
    SpaceGameServer spaceGameServer;

    /**
     * Creates DatagramSocket through which all client update messages
     * will be received and forwarded.
     */
    public BestEffortServer(SpaceGameServer spaceGameServer) {

        // Save reference to the server
        this.spaceGameServer = spaceGameServer;

        try {
            gamePlaySocket = new DatagramSocket(Constants.SERVER_PORT);
        }
        catch (IOException e) {
            String s = "Error creating socket to receive and forward UDP messages.";
            System.err.println(s);
            spaceGameServer.playing = false;
        }

    } // end gamePlayServer

    /**
     * run method that continuously receives update messages, updates the
     * display, and then forwards update messages.
     */
    @Override
    public void run() {
        DatagramPacket packet = new DatagramPacket(new byte[24], 24);
        ByteArrayInputStream bais = null;

        // Receive and forward messages. Update the sector display
        while (spaceGameServer.playing) {
            try {
                gamePlaySocket.receive(packet);
                bais = new ByteArrayInputStream(packet.getData());
                DataInputStream dis = new DataInputStream(bais);

                // Read IP
                byte[] ip = new byte[4];
                dis.read(ip);
                // Read port
                int port = dis.readInt();
                // Create an InetSocketAddress used as an ID
                InetAddress ipAddress = InetAddress.getByAddress(ip);
                InetSocketAddress id = new InetSocketAddress(ipAddress, port);
                // Read type
                int type = dis.readInt();
                // Read X
                int x = dis.readInt();
                // Read Y
                int y = dis.readInt();
                // Read heading
                int heading = dis.readInt();

                processUpdate(id, type, x, y, heading);

                // TODO: forward message
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

        // TODO: close server?

    } // end run

    /**
     * Processes the update for the received ship. Depending on the
     * <code>type</code>, it updates the sector.
     * 
     * @param id
     *            The identifier of the client.
     * @param type
     *            Type of request from the client.
     * @param x
     *            The X position of the client.
     * @param y
     *            The Y position of the client.
     * @param heading
     *            The heading of the client.
     */
    void processUpdate(InetSocketAddress id, int type, int x, int y, int heading) {
        if (type == Constants.JOIN || type == Constants.UPDATE_SHIP) {
            // Temporary ship
            SpaceCraft ship = new SpaceCraft(id, x, y, heading);

            // Update the sector
            spaceGameServer.sector.updateOrAddSpaceCraft(ship);

            // Check collision
            ArrayList<SpaceCraft> collided =
                    spaceGameServer.sector.collisionCheck(ship);

            // Remove ships that collided
            if (collided != null) {
                for (SpaceCraft sc : collided) {
                    spaceGameServer.sendRemoves(sc);
                }
            }
        }
    }

} // end BestEffortServer class
