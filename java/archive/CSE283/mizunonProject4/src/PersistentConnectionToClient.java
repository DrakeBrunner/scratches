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
        try {
            DataInputStream dis =
                    new DataInputStream(clientConnection.getInputStream());
            udpPort = dis.readInt();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        // loop till playing is set to false
        while (thisClientIsPlaying && spaceGameServer.playing) {

            // TODO

        } // end while

        // TODO

    } // end run

    // TODO

    protected void sendRemoveToClient(SpaceCraft sc) {
        // TODO

    } // end sendRemoveToClient

} // end PersistentConnectionToClient class