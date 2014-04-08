package mizunonProject3;

/**
 * CSA 283 Project 1: Interface to capture events from
 * ChatInterface GUI.
 * 
 * @author Eric Bachmann
 * @version 1.0 September 10, 2007
 */
public interface ChatPeerInterfaceListener {

    /**
     * Contacts a friend and starts a chat thread with that friend.
     * 
     * @param friendName
     *            The screen of the friend to contact to.
     * @param friendIndex
     *            The index of the friend in the list.
     */
    public void contactFriend(String friendName, int friendIndex);

    /**
     * This method will be called whenever the user closes the
     * GUI. The implemented quit method should close all
     * streams, close all sockets and stop all threads that
     * are being used to support the interface.
     */
    public void quit();

    /**
     * This method will be called at regular intervals by the GUI. When called
     * the method should contact the server and obtain and updated list of
     * peers.
     * To update the list in the GUI. The method should first call the clearList
     * method of the ChatPeerInterface and then repeatedly call the
     * addFriendToList
     * to add all the peer that are currently in the system.
     */
    public void updateFriendList();

} // end ChatInterfaceListener interface