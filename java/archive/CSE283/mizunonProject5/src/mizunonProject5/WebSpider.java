package mizunonProject5;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;

/**
 * Gets the URLs in a given URL and follows those links to get more links.
 * 
 * @author Naoki Mizuno
 * 
 */

public class WebSpider {
    public static void main(String[] args) {
        search("http://miamioh.edu", 1);
        // search("http://www.mubookstore.muohio.edu/", 0);
    }

    /**
     * Helper method that prints the given number of spaces.
     * 
     * @param amount
     *            The number of spaces to be printed.
     */
    public static void printSpaces(int amount) {
        for (int i = 0; i < amount; i++)
            System.out.print(" ");
    }

    /**
     * Does a search on the given URL with the also given path. Recursively
     * performs searches on the links found for the given amount of times.
     * Prints out the URLs found and the total number of links found.
     * 
     * @param url
     *            The URL. Must be a valid URL with the protocol and the host.
     * @param path
     *            The path after the host.
     * @param depth
     *            Number of times to descend into the links found.
     */
    public static void search(String url, String path, int depth) {
        search(url + path, depth);
    }

    /**
     * Does a search on the given URL. Recursively performs searches on the
     * links found for the given amount of times.
     * Prints out the URLs found and the total number of links found.
     * 
     * @param url
     *            Appropriately formed URL to search for.
     * @param depth
     *            Number of times to descend into the links found.
     */
    public static void search(String url, int depth) {
        try {
            URL u = new URL(url);
            search(u, depth);
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Does a search on the given URL for the given amount of times. Prints out
     * the total number of links found.
     * 
     * @param url
     *            An URL object for the URL to search for.
     * @param depth
     *            Number of times to descend into the links found.
     */
    public static void search(URL url, int depth) {
        int total = search(url, depth, depth);
        System.out.printf("Total objects = %d\n", total);
    }

    /**
     * Helper method for the search. Does a recursive search on the given URL
     * for the given amount of times. Prints out the total number of links
     * found.
     * 
     * @param url
     *            An URL object for the URL to search for.
     * @param depth
     *            Number of times to descend into the links found.
     * @param origDepth
     *            The original depth given by the callee.
     * @return The total number of links found in this URL.
     */
    private static int search(URL url, int depth, int origDepth) {
        int total = 0;

        printSpaces((origDepth - depth) * 4);
        System.out.printf("Searched %s and found:\n", url);

        Getter getter = null;
        Vector<URL> urls = null;
        try {
            getter = new Getter(url);
        }
        catch (IOException e) {
            return total;
        }

        try {
            getter.sendGet();
            urls = getter.getLinks();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        if (urls == null)
            return total;
        else
            total += urls.size();

        for (int i = 0; i < urls.size(); i++) {
            printSpaces((origDepth - depth) * 4 + 8);
            System.out.println(urls.get(i));
        }

        printSpaces((origDepth - depth) * 4);
        System.out.printf("Found %d referenced objects in %s\n\n", total, url);

        // Recursively search each URL
        for (int i = 0; i < urls.size(); i++)
            if (depth > 0 && !urls.get(i).equals(urls))
                total += search(urls.get(i), depth - 1, depth);

        return total;
    }
}