import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;

import spaceWar.Constants;
import spaceWar.Obstacle;
import spaceWar.SpaceCraft;
import spaceWar.Torpedo;

/**
 * @author bachmaer
 * 
 *         Class to listen for clients sending information reliably
 *         using TCP. It takes care of the following events:
 *         1. Client coming into the game
 *         2. Client firing torpedoes
 *         3. Client leaving the game
 *         4. Sending remove messages to the client
 */
public class PersistentConnectionToClient extends Thread {

    Socket clientConnection = null;

    SpaceGameServer spaceGameServer;

    boolean thisClientIsPlaying = true;

    public PersistentConnectionToClient(Socket sock,
            SpaceGameServer spaceGameServer) {

        this.clientConnection = sock;
        this.spaceGameServer = spaceGameServer;

    } // end PersistentConnectionToClient

    /**
     * Listens for join and exiting clients using TCP. Joining clients are sent
     * the x and y coordinates of all obstacles followed by a negative number.
     * Receives fire messages from clients and the exit code when a client is
     * leaving the game.
     */
    @Override
    public void run() {

        // Receive port number of UDP connection
        int udpPort = 0;
        DataInputStream dis = null;
        try {
            dis = new DataInputStream(clientConnection.getInputStream());
            udpPort = dis.readInt();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        // Send obstacles
        sendObstacles();

        SpaceCraft ship = null;
        InetSocketAddress clientID = new InetSocketAddress(
                clientConnection.getInetAddress(), udpPort);
        // loop till playing is set to false
        while (thisClientIsPlaying && spaceGameServer.playing) {
            try {
                int code = dis.readInt();

                // When client wants to exit
                if (code == Constants.EXIT) {
                    ship = new SpaceCraft(clientID, 0, 0, 0);
                    break;
                }
                // If a torpedo is fired
                else if (code == Constants.FIRED_TORPEDO) {
                    int x = dis.readInt();
                    int y = dis.readInt();
                    int heading = dis.readInt();

                    Torpedo torpedo = new Torpedo(clientID, x, y, heading);
                    // Add to own sector
                    spaceGameServer.sector.updateOrAddTorpedo(torpedo);
                    // Send update to clients
                    spaceGameServer.sendTorpedoUpdate(torpedo,
                            spaceGameServer.torpUpdater.dgsock);
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Remove from list
        spaceGameServer.removeClientDatagramSocketAddresses(clientID);
        spaceGameServer.removePersistentConnection(this);

        // Tell all clients to remove that client
        spaceGameServer.sendRemoves(ship);

        // Remove from sector
        spaceGameServer.sector.removeSpaceCraft(ship);

    } // end run

    /**
     * Sends all the obstacles to this client. The data is sent in the order of
     * X coordinate, Y coordinate. A negative number is sent after the final
     * obstacle.
     */
    private void sendObstacles() {
        ArrayList<Obstacle> obstacles = spaceGameServer.sector.getObstacles();
        DataOutputStream dos = null;

        try {
            // Send the coordinates of all the obstacles
            for (int i = 0; i < obstacles.size(); i++) {
                Obstacle obs = obstacles.get(i);
                int x = obs.getXPosition();
                int y = obs.getYPosition();
                dos = new DataOutputStream(clientConnection.getOutputStream());
                dos.writeInt(x);
                dos.writeInt(y);
            }

            // Send a negative number to indicate the end
            dos.writeInt(-1);

            dos.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sends a message to this client telling to remove the given space ship
     * from it's sector.
     * 
     * @param sc
     *            The space ship that is removed.
     */
    protected void sendRemoveToClient(SpaceCraft sc) {
        try {
            DataOutputStream dos =
                    new DataOutputStream(clientConnection.getOutputStream());
            // IP
            dos.write(sc.ID.getAddress().getAddress());
            // Port
            dos.writeInt(sc.ID.getPort());
            // Type
            if (sc instanceof Torpedo)
                dos.writeInt(Constants.REMOVE_TORPEDO);
            else
                dos.writeInt(Constants.REMOVE_SHIP);

            dos.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    } // end sendRemoveToClient

} // end PersistentConnectionToClient class
