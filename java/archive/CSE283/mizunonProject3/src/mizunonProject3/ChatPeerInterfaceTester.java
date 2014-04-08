package mizunonProject3;
import javax.swing.JOptionPane;

// Dummy class for testing the GUI events. 
public class ChatPeerInterfaceTester implements ChatPeerInterfaceListener {
    private ChatPeerInterface face;

    public ChatPeerInterfaceTester() {

        // Ask the user for a screen name.
        String name = JOptionPane.showInputDialog("Enter Screen Name: ");

        // Did the user enter a screen name?
        if (name != null && !name.isEmpty()) {
            // Create the GUI interface
            face = new ChatPeerInterface(this, name);

            for (int i = 0; i < 5; i++) {
                // How friends can be added to the list display
                face.addFriendToList("friend" + i, i);
            }
        }
    }

    @Override
    public void contactFriend(String friendName, int friendIndex) {
        String str = String.format("Contact %s at index %d",
                friendName, friendIndex);
        System.out.println(str);
    }

    @Override
    public void quit() {
        String str = "Unregister with the server,"
                + " close connections and clean up while the GUI disposes itself.";
        System.out.println(str);
    }

    @Override
    public void updateFriendList() {
        String str = "Time to contact the server and update the list of friends.";
        System.out.println(str);
    }

    // main for testing purposes only.
    public static void main(String[] args) {
        new ChatPeerInterfaceTester();
    }
}