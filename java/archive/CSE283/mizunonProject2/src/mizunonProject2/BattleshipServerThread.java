package mizunonProject2;

import java.io.*;
import java.net.*;
import java.util.Random;

public class BattleshipServerThread {
    private Socket clientSocket = null;
    private DataInputStream dis = null;
    private DataOutputStream dos = null;

    private BattleshipGame bsg = null;

    public BattleshipServerThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
        try {
            dis = new DataInputStream(clientSocket.getInputStream());
            dos = new DataOutputStream(clientSocket.getOutputStream());
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        startGame();
        resetClientConnection();
    }

    /**
     * Starts a new round of Battleship.
     */
    public void startGame() {
        bsg = new BattleshipGame();
        bsg.initBoard();

        deploy();
        bsg.printAllies();
        for (int i = 0; i < BattleshipGame.MAX_GUESS; i++) {
            int status = waitForAttack();

            if (bsg.allShipsSunk()) {
                sendGameStatus(BattleshipGame.GAME_WIN_CLIENT);
                return;
            }
            else {
                if (i == BattleshipGame.MAX_GUESS - 1)
                    sendGameStatus(BattleshipGame.GAME_WIN_SERVER);
                else
                    sendGameStatus(status);

                // Don't count hit towards the total number of guesses
                if (status == BattleshipGame.STATUS_HIT)
                    i--;
            }

            // Exit unless the status is to continue game
            if (status != BattleshipGame.GAME_CONTINUE)
                return;
        }
    }

    /**
     * Randomly places a cruiser, destroyer, and a battleship on the board.
     */
    public void deploy() {
        Random rand = new Random();
        int i;
        int j;
        boolean alignNorthSouth;

        // Deploy destroyer
        do {
            i = rand.nextInt(bsg.getBoardSize());
            j = rand.nextInt(bsg.getBoardSize());
            alignNorthSouth = rand.nextBoolean();
        } while (!bsg.deployShip(BattleshipGame.STATUS_DESTROYER,
                i, j, 2, alignNorthSouth));

        // Deploy cruiser
        do {
            i = rand.nextInt(bsg.getBoardSize());
            j = rand.nextInt(bsg.getBoardSize());
            alignNorthSouth = rand.nextBoolean();
        } while (!bsg.deployShip(BattleshipGame.STATUS_CRUISER,
                i, j, 3, alignNorthSouth));

        // Deploy battleship
        do {
            i = rand.nextInt(bsg.getBoardSize());
            j = rand.nextInt(bsg.getBoardSize());
            alignNorthSouth = rand.nextBoolean();
        } while (!bsg.deployShip(BattleshipGame.STATUS_BATTLESHIP,
                i, j, 4, alignNorthSouth));

        // Deploy aircraft carrier
        do {
            i = rand.nextInt(bsg.getBoardSize());
            j = rand.nextInt(bsg.getBoardSize());
            alignNorthSouth = rand.nextBoolean();
        } while (!bsg.deployShip(BattleshipGame.STATUS_AIRCRAFT_CARRIER,
                i, j, 5, alignNorthSouth));

    }

    /**
     * Waits for an attack. This method automatically sends back the result code
     * to the client but leaves sending back the game status code to the calling
     * method.
     * 
     * @return The game status code.
     */
    public int waitForAttack() {
        int status = BattleshipGame.GAME_ILLEGAL_EXIT;

        try {
            int result = BattleshipGame.RESULT_ILLEGAL;

            int i = dis.readInt();
            int j = dis.readInt();

            result = bsg.attacked(i, j);
            replyToAttack(result);

            // Continue only if valid action was taken
            if (result != BattleshipGame.RESULT_ILLEGAL)
                status = BattleshipGame.GAME_CONTINUE;
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return status;
    }

    /**
     * Reply to the client about the attack.
     * 
     * @param result
     *            The result code for the attack the client made.
     */
    public void replyToAttack(int result) {
        try {
            dos.writeInt(result);
            dos.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sends the given game status to the client.
     * 
     * @param status
     *            The status of the game.
     */
    public void sendGameStatus(int status) {
        try {
            dos.writeInt(status);
            dos.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Resets the Socket, DataInputStream, and DataOutputStream after the game
     * ended.
     */
    public void resetClientConnection() {
        // First, close all
        try {
            clientSocket.close();
            dis.close();
            dos.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        dis = null;
        dos = null;
        clientSocket = null;
        bsg = null;
    }
}