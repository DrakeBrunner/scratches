package mizunonProject2;

import java.io.*;
import java.net.*;
import java.util.*;

public class BattleshipClient {
    private Socket socket = null;
    private DataInputStream dis = null;
    private DataOutputStream dos = null;

    private BattleshipGame bsg = null;

    public BattleshipClient() {
        try {
            connectToServer(inputIPAddress());
            startGame();
            closeConnection();
        }
        catch (UnknownHostException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Prompts the user for an IP address to connect to. If an empty String is
     * entered, 127.0.0.1 is returned.
     * 
     * @return The IP address entered by the user in String.
     */
    public String inputIPAddress() {
        Scanner in = new Scanner(System.in);

        String message = "Enter IP address you want to connect to: ";
        System.out.print(message);

        String address = in.nextLine();

        if (address.isEmpty()) {
            System.out.println("Empty IP address. Using localhost (127.0.0.1)");
            address = "127.0.0.1";
        }

        return address;
    }

    /**
     * Attempts to connect to the given ID address and the port specified in
     * Battleship class. Once a connection is made, DataInputStream and
     * DataOutputStream are obtained from the socket.
     * 
     * @param address
     *            The IP address to connect to.
     * @throws IOException
     *             If an I/O error occurs when creating a socket.
     * @throws UnknownHostException
     *             When the given host is not found.
     */
    public void connectToServer(String address)
            throws UnknownHostException, IOException {
        socket = new Socket(InetAddress.getByName(address),
                BattleshipGame.PORT_NUMBER);
        dis = new DataInputStream(socket.getInputStream());
        dos = new DataOutputStream(socket.getOutputStream());
    }

    /**
     * Starts a new round of Battleship.
     * TODO: separate into smaller methods
     */
    public void startGame() {
        bsg = new BattleshipGame();
        bsg.initBoard();
        bsg.printEnemy();

        while (true) {
            // Prompt for coordinate
            Scanner in = new Scanner(System.in);
            System.out.print("Select row: ");
            int j = in.nextInt();
            in.nextLine();
            System.out.print("Select column: ");
            int i = in.nextInt();
            in.nextLine();

            // Commence attack!
            int result = fire(i, j);
            if (result != BattleshipGame.RESULT_ILLEGAL) {
                bsg.setResultOfAttack(result, i, j);
            }
            bsg.printEnemy();

            printResult(result);

            int status = getGameStatus();

            if (status == BattleshipGame.GAME_CONTINUE) {
                String message = "'M' indicates \"miss.\" 'H' indicates \"hit.\"";
                message += "Enter your Move (enter negative number to quit).";
                System.out.println(message);
            }
            else {
                String message;
                switch (status) {
                    case BattleshipGame.GAME_WIN_CLIENT:
                        message = "Congratulations, you win!";
                        break;
                    case BattleshipGame.GAME_WIN_SERVER:
                        message = "You lose :(";
                        break;
                    default:
                        message = "Exiting game.";
                        break;
                }
                System.out.println(message);
                return;
            }
        }
    }

    /**
     * Prints out the result of the attack. Shows a thank you message when a
     * negative number is entered and the program is going to exit.
     * 
     * @param result
     *            The result of the attack.
     */
    public void printResult(int result) {
        String message = "";
        switch (result) {
            case BattleshipGame.RESULT_HIT:
                message = "Hit!";
                break;
            case BattleshipGame.RESULT_MISS:
                message = "Miss!";
                break;
            case BattleshipGame.RESULT_SUNK:
                message = "Sink!";
                break;
            default:
                message = "Invalid move!";
        }
        System.out.println(message);
    }

    /**
     * Attacks the enemy and waits for the result.
     * 
     * @param i
     *            The x-coordinate of the grid to attack.
     * @param j
     *            The y-coordinate of the grid to attack.
     * @return The result code received from the server.
     */
    public int fire(int i, int j) {
        // Send data
        try {
            dos.writeInt(i);
            dos.writeInt(j);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        // Receive result
        int result = BattleshipGame.RESULT_ILLEGAL;

        try {
            result = dis.readInt();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Receives the game status from the server.
     * 
     * @return The game status.
     */
    public int getGameStatus() {
        int status = BattleshipGame.GAME_ILLEGAL_EXIT;
        try {
            status = dis.readInt();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return status;
    }

    /**
     * Closes the connection with the server.
     */
    public void closeConnection() {
        try {
            dis.close();
            dos.close();
            socket.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new BattleshipClient();
    }
}