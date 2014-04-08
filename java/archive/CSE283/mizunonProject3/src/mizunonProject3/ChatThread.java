package mizunonProject3;
import java.io.*;
import java.net.*;

public class ChatThread extends Thread implements ChatThreadInterfaceListener {

    // Socket connection to another user
    protected Socket sock;

    // GUI Interface for the conversation
    protected ChatThreadGUI face;

    // Flag indicating when conversation has ended
    protected boolean doneFlag = false;

    // Screen name of the owner of the thread
    protected String owner = "";

    // Screen name of the owner to which the socket is connected
    protected String talkingToName = "";

    // Stream for sending text
    protected DataOutputStream oos = null;

    // Stream for receiving text
    protected DataInputStream ois = null;

    // Time interval in milliseconds at which the doneFlag will be checked
    public static final int TIME_OUT_INTERVAL = 15;

    /**
     * Constructs an object the is capable of carrying on a text based
     * conversation using the supplied socket. Creates a GUI interface
     * that can be used to send and receive text using the supplied socket.
     * 
     * @param owner
     *            screen of the client that created this object
     * @param socket
     *            Connection to another ChatThread on a remote host
     * 
     * @throws IOException
     */
    public ChatThread(String owner, Socket socket) {
        // Save reference to communication socket
        this.sock = socket;

        // Save the screen name of the owner
        this.owner = owner;

        // Create an interface and register self to listen to
        // event
        face = new ChatThreadGUI("Unknown");
        face.addChatThreadGUIListener(this);

        // Exchange screen names and instantiate streams
        initializeConnection();

        // Set socket timeout so the thread can end gracefully
        try {
            this.sock.setSoTimeout(TIME_OUT_INTERVAL);
        }
        catch (SocketException e) {
            System.err.println("Error setting time on connection.");
        }

        // Start thread to listen for incoming strings
        start();
    }

    /**
     * Creates streams for sending and receiving text. Exchanges screen
     * names and set the title for the GUIs
     */
    protected void initializeConnection() {
        try {
            // Instantiate stream for sending text
            oos = new DataOutputStream(sock.getOutputStream());

            // Exchange screen names
            oos.writeUTF(owner);

            // Instantiate stream for receiving text
            ois = new DataInputStream(sock.getInputStream());

            // Read screen name of who the connection is with
            talkingToName = ois.readUTF();

            // Set the tile on the GUI interface
            face.setTitle(owner + " talking to " + talkingToName);
        }
        catch (IOException e) {
            System.err.println("Error setting up the connection." +
                    "\nConnection may have been lost.");
        }
    }

    /**
     * Called by interface each time the send button is pressed.
     * Sends the string into the OutputStream.
     * 
     * @param text
     *            Text to be sent.
     */
    @Override
    public void sendMessage(String message) {
        try {
            // Send message to chat client
            oos.writeUTF(message.toString());
        }
        catch (IOException e) {
            System.err.println("Error sending message to " + talkingToName +
                    "\nConnection may have been lost.");
        }

    } // end sendMessage

    /**
     * Listens for incoming messages.
     */
    @Override
    public void run() {
        try {
            // infinite loop will exit when quit method
            // sets the doneFlag to true
            while (doneFlag == false) {
                try {
                    // read in strings
                    face.appendToIn(ois.readUTF());
                }
                catch (SocketTimeoutException e) {
                    // Loop back up to check the doneFlag
                }
            }
        }
        catch (IOException e1) {
            // System.err.println("Error with connection to " + talkingToName );
            finish();
        }

        closeConnection();
    }

    /**
     * Close the connection gracefully.
     */
    protected void closeConnection() {
        // Wait for the receiving thread to get out
        // of the receiving mode.
        try {
            sleep(2 * TIME_OUT_INTERVAL);
        }
        catch (InterruptedException e1) {
            System.err.println("Interrupted while closing connection to "
                    + talkingToName);
        }

        // Close the communication socket.
        try {
            this.sock.close();
        }
        catch (IOException e) {
            System.err.println("Error closing connection to " + talkingToName);
        }
    }

    /**
     * Stops the communication thread and closes the GUI. This method would be
     * called by the thread that created this object.
     */
    public void finish() {
        // Close socket connection and streams
        System.out.println("\nClosing connection to " + talkingToName);

        quit();

        // Close the GUI
        face.dispose();
    }

    /**
     * Called by the GUI interface to stop this communication thread.
     */
    @Override
    public void quit() {
        // Stop the read thread
        doneFlag = true;
    }
}