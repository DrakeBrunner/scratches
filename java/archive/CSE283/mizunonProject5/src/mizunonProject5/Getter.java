package mizunonProject5;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.util.Vector;

/**
 * Given a URL, this class opens a connection with the server and retrieves the
 * HTML for that web page.
 * 
 * @author Naoki Mizuno
 * 
 */

public class Getter {
    public static final boolean DEBUG = false;
    public static final String CRLF = "\r\n";
    private URL url;

    private Socket connection = null;

    /**
     * Connects to the HTTP server at the given URL.
     * 
     * @param url
     *            The URL to connect to.
     * @throws IOException
     *             When it fails to connect to the HTTP server.
     */
    public Getter(URL url) throws IOException {
        this.url = url;

        InetAddress ip = InetAddress.getByName(this.url.getHost());
        this.connection = new Socket(ip, 80);
    }

    /**
     * Sends a GET message to the server.
     * 
     * @throws IOException
     *             When the socket fails to get the OutputStream.
     */
    public void sendGet() throws IOException {
        String mes = "";
        // Add a "/" if getPath() is empty
        String path = url.getPath().equals("") ? "/" : url.getPath();
        mes = String.format("GET %s HTTP/1.1 %s", path, CRLF);
        mes += String.format("Host: %s %s", url.getHost(), CRLF);
        mes += String.format("Connection: close %s", CRLF);
        mes += CRLF;

        DataOutputStream dos =
                new DataOutputStream(connection.getOutputStream());
        dos.writeBytes(mes);
        dos.flush();
    }

    /**
     * Receives the HTML and extracts the URLs that are found in that
     * HTML.
     * 
     * @return List of URLs found in the received HTML.
     * @throws IOException
     */
    public Vector<URL> getLinks() throws IOException {

        BufferedReader br =
                new BufferedReader(new InputStreamReader(
                        connection.getInputStream()));
        // Get response code
        String resp = br.readLine();
        if (DEBUG)
            System.out.println(resp);

        int code = Integer.parseInt(resp.split(" ")[1]);
        if (code != 200) {
            System.err.printf("%d was returned from %s\n", code, url);
            return null;
        }

        // Skip empty lines
        do {
            resp = br.readLine();
        } while (!resp.equals(""));
        resp = br.readLine();

        // Process the HTML line by line
        Vector<URL> urls = new Vector<URL>();
        while ((resp = br.readLine()) != null
                && !resp.equals("0")
                && !resp.trim().equals("</html>")) {
            if (DEBUG)
                System.out.println(resp);
            Vector<URL> urlsInLine = processLine(resp);
            // Add URLs in line to vector of URL
            for (int i = 0; i < urlsInLine.size(); i++)
                urls.add(urlsInLine.get(i));
        }

        if (DEBUG)
            System.out.println(urls);

        connection.close();

        return urls;
    }

    /**
     * Recursively processes a line of HTML text by searching for "href" tags.
     * Creates an URL object for each href tag that refers to an object that
     * can be downloaded using the HTTP protocol. Returns the URL objects in
     * a Vector. If no objects are found, the size of the vector will be zero.
     * 
     * @param line
     *            String to be searched for href tags
     * @return vector containing a URL object for each href tag found.
     * @throws IOException
     */
    protected Vector<URL> processLine(String line) throws IOException {

        // Vector of URL objects created while processing "line"
        Vector<URL> URLS = new Vector<URL>();

        int beginHost; // Index of the beginning of the first host found
        int endOfRef = 0; // Index of the end of the url

        // Look for an href tag
        int index = line.indexOf("href");

        // Find the beginning of the referenced URL
        beginHost = line.indexOf("http", index);

        // Are there any href tags in line?
        if (index != -1 && beginHost > index) {

            // Find the end of the URL
            endOfRef = line.indexOf("\"", beginHost);

            Vector<URL> moreURLS = null;

            try {

                URL found = new URL(line.substring(beginHost, endOfRef));

                // Create a URL object and add it to the vector of URLs
                URLS.add(found);

                // Recursive call to look for URLs in the rest of the line
                moreURLS = processLine(line.substring(endOfRef));

            }
            catch (StringIndexOutOfBoundsException e) {
            }
            catch (MalformedURLException e) {
            }

            // If more URLs were found in the remainder of the line,
            // add them to the vector
            if (moreURLS != null && moreURLS.size() > 0) {

                URLS.addAll(moreURLS);
            }
        }

        // return the vector of URL objects
        return URLS;

    } // end processLine
}