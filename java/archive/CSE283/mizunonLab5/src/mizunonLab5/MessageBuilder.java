package mizunonLab5;

import java.io.IOException;

/*
 * Created on Feb 8, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */

/**
 * @author bachmaer
 * 
 *         To change the template for this generated type comment go to Window -
 *         Preferences - Java - Code Generation - Code and Comments
 */
public class MessageBuilder implements MailInterfaceListener {

    // Server, sender, recipient, subject and body for the message.
    private String server;
    private String from;
    private String to;
    private String subject;
    private String body;

    // TCP connection to the server.
    SMTPConnection smtpConnection = null;

    // GUI interface for the mail application
    MailInterface mailFace;

    /**
     * Instantiates an interface and stores a reference to it in a data member.
     * 
     */
    public MessageBuilder() {
        mailFace = new MailInterface(this);

    } // end constructor

    /**
     * Called by the interface when the send message is pressed. It opens an
     * SMTPConnection with the appropriate server. Puts together mail message
     * commands, and message data and uses the SMTP connection to send them.
     * 
     * @return true if the mail was sent successfully.
     */
    public boolean sendMail() {
        // Retrieve the data contained in the text fields and areas of the GUI
        server = mailFace.getServer();
        from = mailFace.getFrom();
        to = mailFace.getTo();
        subject = mailFace.getSubject();
        body = mailFace.getEscapedBody();

        // Instantiate an SMTPConnection object if not created
        if (smtpConnection == null) {
            try {
                smtpConnection = new SMTPConnection(server);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Checks whether the entered email addresses are valid
        if (!isValidAddress(from)) {
            System.err.println("Invalid email address for 'from'");
            return false;
        }
        if (!isValidAddress(to)) {
            System.err.println("Invalid email address for 'to'");
            return false;
        }

        // Send SMTP commands and check the response to each in order to
        // send and email message.
        // TODO: separate method(s)

        String command = "";
        // From
        command = String.format("mail from: <%s>", from);
        try {
            if (!smtpConnection.send(command, 250)) {
                System.err.println("Invalid 'from' field");
                return false;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        // To
        command = String.format("rcpt to: <%s>", to);
        try {
            if (!smtpConnection.send(command, 250)) {
                System.err.println("Invalid 'to' field");
                return false;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        // Send data
        command = "data" + SMTPConnection.CRLF;
        command += String.format("from: %s%s", from, SMTPConnection.CRLF);
        command += String.format("to: %s%s", to, SMTPConnection.CRLF);
        command += String.format("subject: %s%s", subject, SMTPConnection.CRLF);

        try {
            if (!smtpConnection.send(command, 354)) {
                System.err.println("Error sending 'data' command");
                return false;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        boolean dataSent = false;
        command = String.format("%s%s", body, SMTPConnection.CRLF);
        // '.' represents end of message
        command += "." + SMTPConnection.CRLF;
        try {
            dataSent = smtpConnection.send(command, 250);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        // Return true if the message sent successfully
        return dataSent;

    } // end sendMail

    /**
     * Performs any required closing operations and releases resources when the
     * GUI is closed down.
     */
    public void close() {
        // If the smtpConnection is not null, close it down.
        if (smtpConnection != null)
            smtpConnection.close();
    } // end close

    /**
     * Checks whether the email address is valid. Checks that the address has
     * only one @-sign and is not the empty string.
     * 
     * @return true if the address is valid
     */
    private boolean isValidAddress(final String address) {

        int atPlace = address.indexOf('@');

        if (address.length() == 0 || atPlace < 1
                || (address.length() - atPlace) <= 1
                || atPlace != address.lastIndexOf('@')) {

            return false;
        }
        else {

            return true;
        }

    } // end isValid

    public static void main(String[] args) {
        new MessageBuilder();

    } // end main

} // end MailSender
