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
 *         destroy each other by ramming. Head on collisions destroy both ships.
 *         Ships move and turn through GUI mouse clicks. All friendly and alien
 *         ships are displayed on a 2D interface.
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
                System.exit(1);
            }

            // Call a method that uses TCP/IP to receive obstacles
            // from the server.
            receiveObstacles();

            // Start thread to listen on the TCP Socket and receive remove
            // messages.
            new Receiver().start();

            // Infinite loop or separate thread to receive update
            // messages from the server and use the messages to
            // update the sector display
            new Updater().start();
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

    /**
     * Receives all the obstacles from the server. Data is sent in the order of
     * x coordinate, y coordinate. Negative number is sent at the end,
     * indicating the end of all obstacles.
     */
    private void receiveObstacles() {
        DataInputStream dis = null;
        try {
            dis = new DataInputStream(reliableSocket.getInputStream());
            while (true) {
                int x = dis.readInt();

                // Negative number indicates all obstacles has been sent
                if (x < 0)
                    break;
                int y = dis.readInt();

                sector.addObstacle(x, y);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

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
            sendPacket(Constants.UPDATE_SHIP);

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
            sendPacket(Constants.UPDATE_SHIP);
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
            DataOutputStream dos = null;
            try {
                dos = new DataOutputStream(reliableSocket.getOutputStream());
                dos.writeInt(Constants.FIRED_TORPEDO);
            }
            catch (IOException e) {
                e.printStackTrace();
            }

            // Send Position and heading
            try {
                dos.writeInt(sector.ownShip.getXPosition());
                dos.writeInt(sector.ownShip.getYPosition());
                dos.writeInt(sector.ownShip.getHeading());
            }
            catch (IOException e) {
                e.printStackTrace();
            }

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
            sendPacket(Constants.UPDATE_SHIP);
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
            sendPacket(Constants.UPDATE_SHIP);
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
            // using the send object
            sendPacket(Constants.JOIN);
        }

    } // end join

    /**
     * Perform clean-up for application shut down. Sends the EXIT code and then
     * the x, y coordinates and the heading.
     */
    public void stop() {
        if (DEBUG)
            System.out.println("stop");

        // Stop all thread and close all streams and sockets
        playing = false;

        // Send exit code to the server
        try {
            DataOutputStream dos =
                    new DataOutputStream(reliableSocket.getOutputStream());
            dos.writeInt(Constants.EXIT);
            // dos.writeInt(sector.ownShip.getXPosition());
            // dos.writeInt(sector.ownShip.getYPosition());
            // dos.writeInt(sector.ownShip.getHeading());
            dos.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    } // end stop

    /**
     * Helper method that sends a packet with the given request type using UDP.
     * 
     * @param type
     *            The type of request (e.g. JOIN, UPDATE_SHIP)
     */
    private void sendPacket(int type) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        dos = new DataOutputStream(baos);
        try {
            // Write IP address
            dos.write(InetAddress.getLocalHost().getAddress());
            // Write port
            dos.writeInt(gamePlaySocket.getLocalPort());
            // Write type
            dos.writeInt(type);
            // Write X position
            int x = sector.ownShip.getXPosition();
            dos.writeInt(x);
            // Write Y position
            int y = sector.ownShip.getYPosition();
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

    /*
     * Starts the space game. Driver for the application.
     */
    public static void main(String[] args) {
        new SpaceGameClient();
    }

    /**
     * Class that is used to receive remove messages from server. Remove
     * messages are messages telling this client that some other client has left
     * the game and that spaceship needs to be removed from the sector. This
     * class uses TCP.
     * 
     * @author Naoki
     */
    class Receiver extends Thread {
        /**
         * Listens for messages sent from the server. The message are such as
         * removal of a ship or removal of a torpedo.
         */
        @Override
        public void run() {
            while (playing) {
                try {
                    dis = new DataInputStream(reliableSocket.getInputStream());
                    byte[] ip = new byte[4];
                    dis.read(ip);
                    int port = dis.readInt();
                    int code = dis.readInt();

                    InetSocketAddress id = new InetSocketAddress(
                            InetAddress.getByAddress(ip), port);

                    if (code == Constants.REMOVE_SHIP)
                        sector.removeSpaceCraft(new SpaceCraft(id, 0, 0, 0));
                    else if (code == Constants.REMOVE_TORPEDO)
                        sector.removeTorpedo(new Torpedo(id, 0, 0, 0));
                }
                catch (IOException e) {
                    e.printStackTrace();

                    // Close when it fails to communicate with server
                    System.exit(0);
                }
            }
        }
    }

    /**
     * Class that is used to update sector according to messages sent by the
     * server using UDP. Update messages are messages such as updating the
     * location of ships and updating the location of ships.
     * 
     * @author Naoki
     */
    class Updater extends Thread {
        @Override
        public void run() {
            while (playing) {
                try {
                    DatagramPacket packet = new DatagramPacket(new byte[24], 24);
                    gamePlaySocket.receive(packet);

                    ByteArrayInputStream bais = new ByteArrayInputStream(
                            packet.getData());
                    DataInputStream dis = new DataInputStream(bais);

                    byte[] ip = new byte[4];
                    dis.read(ip);
                    int port = dis.readInt();
                    int type = dis.readInt();
                    int x = dis.readInt();
                    int y = dis.readInt();
                    int heading = dis.readInt();

                    // Create InetSocketAddress
                    InetSocketAddress id = new InetSocketAddress(
                            InetAddress.getByAddress(ip), port);

                    if (type == Constants.UPDATE_SHIP) {
                        // Create or update ship
                        AlienSpaceCraft ship =
                                new AlienSpaceCraft(id, x, y, heading);
                        sector.updateOrAddSpaceCraft(ship);
                    }
                    else if (type == Constants.UPDATE_TORPEDO) {
                        Torpedo torpedo =
                                new Torpedo(id, x, y, heading);
                        sector.updateOrAddTorpedo(torpedo);
                    }
                }
                catch (SocketTimeoutException e) {
                    // Don't do anything
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
} // end SpaceGame class
