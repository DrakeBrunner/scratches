import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import spaceWar.*;

/**
 * @author bachmaer
 * 
 *         Driver class for a simple networked space game. Opponents try to
 *         destroy each
 *         other by ramming. Head on collisions destroy both ships. Ships move
 *         and turn
 *         through GUI mouse clicks. All friendly and alien ships are displayed
 *         on a 2D
 *         interface.
 */
public class SpaceGameClient implements SpaceGUIInterface {
    // Keeps track of the game state
    Sector sector;

    // User interface
    SpaceGameGUI gui;

    // IP address and port to identify ownship and the
    // DatagramSocket being used for game play messages.
    InetSocketAddress ownShipID;

    // Socket for sending and receiving
    // game play messages.
    DatagramSocket gamePlaySocket;

    // Socket used to register and to receive remove information
    // for ships and
    Socket reliableSocket;

    // Set to false to stops all receiving loops
    boolean playing = true;

    static final boolean DEBUG = false;

    DataInputStream dis;
    DataOutputStream dos;

    /**
     * Creates all components needed to start a space game. Creates Sector
     * canvas, GUI interface, a Sender object for sending update messages, a
     * Receiver object for receiving messages.
     * 
     * @throws UnknownHostException
     */
    public SpaceGameClient() {
        // Create UDP Datagram Socket for sending and receiving
        // game play messages.
        try {

            gamePlaySocket = new DatagramSocket();
            gamePlaySocket.setSoTimeout(100);

            // Instantiate ownShipID using the DatagramSocket port
            // and the local IP address.
            ownShipID = new InetSocketAddress(InetAddress.getLocalHost(),
                    gamePlaySocket.getLocalPort());

            // Create display, ownPort is used to uniquely identify the
            // controlled entity.
            sector = new Sector(ownShipID);

            // gui will call SpaceGame methods to handle user events
            gui = new SpaceGameGUI(this, sector);

            // Establish TCP connection with the server and pass the port number
            // of the gamePlaySocket (UDP connection).
            try {
                // Connect to server
                reliableSocket =
                        new Socket(Constants.SERVER_IP, Constants.SERVER_PORT);

                // Send port number for UDP connection to server
                DataOutputStream dos =
                        new DataOutputStream(reliableSocket.getOutputStream());
                dos.writeInt(gamePlaySocket.getLocalPort());
            }
            catch (IOException e) {
                e.printStackTrace();
            }

            // Call a method that uses TCP/IP to receive obstacles
            // from the server.
            // TODO

            // Start thread to listen on the TCP Socket and receive remove
            // messages.
            // TODO

            // Infinite loop or separate thread to receive update
            // messages from the server and use the messages to
            // update the sector display
            // TODO

        }
        catch (SocketException e) {
            System.err.println("Error creating game play datagram socket.");
            System.err.println("Server is not opening.");

        }
        catch (UnknownHostException e) {
            System.err.println("Error creating ownship ID. Exiting.");
            System.err.println("Server is not opening.");
        }

    } // end SpaceGame constructor

    // TODO

    /**
     * Causes sector.ownShip to turn and sends an update message for the heading
     * change.
     */
    public void turnRight() {
        if (sector.ownShip != null) {

            if (DEBUG)
                System.out.println(" Right Turn ");

            // Update the display
            sector.ownShip.rightTurn();

            // Send update message to server with new heading.
            // TODO

        }

    } // end turnRight

    /**
     * Causes sector.ownShip to turn and sends an update message for the heading
     * change.
     */
    public void turnLeft() {
        // See if the player has a ship in play
        if (sector.ownShip != null) {

            if (DEBUG)
                System.out.println(" Left Turn ");

            // Update the display
            sector.ownShip.leftTurn();

            // Send update message to other server with new heading.
            // TODO
        }

    } // end turnLeft

    /**
     * Causes sector.ownShip to turn and sends an update message for the heading
     * change.
     */
    public void fireTorpedo() {
        // See if the player has a ship in play
        if (sector.ownShip != null) {

            if (DEBUG)
                System.out.println("Informing server of new torpedo");

            // Send code to let server know a torpedo is being fired.
            // TODO

            // Send Position and heading
            // TODO

        }

    } // end turnLeft

    /**
     * Causes sector.ownShip to move forward and sends an update message for the
     * position change. If there is an obstacle in front of
     * the ship it will not move forward and a message is not sent.
     */
    public void moveFoward() {
        // Check if the player has and unblocked ship in the game
        if (sector.ownShip != null && sector.clearInfront()) {

            if (DEBUG)
                System.out.println(" Move Forward");

            // Update the displayed position of the ship
            sector.ownShip.moveForward();

            // Send a message with the updated position to server
            // TODO
        }

    } // end moveFoward

    /**
     * Causes sector.ownShip to move forward and sends an update message for the
     * position change. If there is an obstacle in front of
     * the ship it will not move forward and a message is not sent.
     */
    public void moveBackward() {
        // Check if the player has and unblocked ship in the game
        if (sector.ownShip != null && sector.clearBehind()) {

            if (DEBUG)
                System.out.println(" Move Backward");

            // Update the displayed position of the ship
            sector.ownShip.moveBackward();

            // Send a message with the updated position to server
            // TODO
        }

    } // end moveFoward

    /**
     * Creates a new sector.ownShip if one does not exist. Sends a join message
     * for the new ship.
     * 
     */
    public void join() {
        if (sector.ownShip == null) {

            if (DEBUG)
                System.out.println(" Join ");

            // Add a new ownShip to the sector display
            sector.createOwnSpaceCraft();

            // Send message to server let them know you have joined the game
            // using the
            // send object
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            dos = new DataOutputStream(baos);
            try {
                // Write IP address
                dos.write(InetAddress.getLocalHost().getAddress());
                // Write port
                dos.writeInt(gamePlaySocket.getLocalPort());
                // Write type (join)
                dos.writeInt(Constants.JOIN);
                // Write X position
                int x = sector.ownShip.getXPosition();
                dos.writeInt(x);
                // Write Y position
                int y = sector.ownShip.getXPosition();
                dos.writeInt(y);
                // Write heading
                int h = sector.ownShip.getHeading();
                dos.writeInt(h);

                dos.flush();
            }
            catch (IOException e) {
                e.printStackTrace();
            }

            // Send packet to server
            try {
                byte[] toSend = baos.toByteArray();
                DatagramPacket p = new DatagramPacket(toSend, toSend.length,
                        Constants.SERVER_IP, Constants.SERVER_PORT);
                gamePlaySocket.send(p);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

    } // end join

    /**
     * Perform clean-up for application shut down
     */
    public void stop() {
        if (DEBUG)
            System.out.println("stop");

        // Stop all thread and close all streams and sockets
        playing = false;

        // Send exit code to the server
        // TODO

    } // end stop

    /*
     * Starts the space game. Driver for the application.
     */
    public static void main(String[] args) {
        new SpaceGameClient();
    }

} // end SpaceGame class