package mizunonLab1;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * This class allows reading from binary file, writing to a file in binary,
 * writing to a file as text, and/or connects to a server and write to it.
 * 
 * @author Naoki Mizuno
 * 
 */

public class ReadingWritingData {
    public static final String NUMERIC_FILE_NAME = "numericTypes.dat";
    public static final String STRING_FILE_NAME = "textTypes.dat";
    public static final String ADDRESS = "10.33.6.94";
    public static final int PORT = 32100;

    protected String filename;

    protected int int1, int2;
    protected float float1, float2;
    protected double double1, double2;
    protected long long1, long2;

    /**
     * Creates a new ReadingWritingData instance with the given file name.
     * 
     * @param filename
     *            The file name used for reading.
     */
    public ReadingWritingData(String filename) {
        this.filename = filename;

        // Call reading method
        readFile();

        // Call writing methods
        writeNumericValues();
        writeStringValues();
        writeToSocket();
    }

    /**
     * Reads from a binary file and changes the instance variables according to
     * that. Then, prints out the values read in the order read.
     */
    public void readFile() {
        File datFile = new File(filename);
        DataInputStream dis = null;
        try {
            FileInputStream fis = new FileInputStream(datFile);
            dis = new DataInputStream(fis);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Start reading from file
        try {
            int1 = dis.readInt();
            int2 = dis.readInt();
            float1 = dis.readFloat();
            float2 = dis.readFloat();
            double1 = dis.readDouble();
            double2 = dis.readDouble();
            long1 = dis.readLong();
            long2 = dis.readLong();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        // Print out the values read from file
        System.out.println("Values read from differentTypes.dat");
        String vals = String.format("%d %d %s %s %s %s %d %d", int1, int2,
                float1, float2, double1, double2, long1, long2);
        System.out.println(vals);
    }

    /**
     * Writes the values to a file as binary data.
     */
    public void writeNumericValues() {
        File datFile = new File(NUMERIC_FILE_NAME);
        DataOutputStream dos = null;
        try {
            FileOutputStream fos = new FileOutputStream(datFile);
            dos = new DataOutputStream(fos);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Start writing to file
        try {
            dos.writeInt(int1);
            dos.writeInt(int2);
            dos.writeFloat(float1);
            dos.writeFloat(float2);
            dos.writeDouble(double1);
            dos.writeDouble(double2);
            dos.writeLong(long1);
            dos.writeLong(long2);

            dos.flush();
            dos.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes the values to a file as text.
     */
    public void writeStringValues() {
        try {
            FileWriter fw = new FileWriter(new File(STRING_FILE_NAME));
            BufferedWriter bw = new BufferedWriter(fw);
            String vals = String.format("%d %d %s %s %s %s %d %d", int1, int2,
                    float1, float2, double1, double2, long1, long2);
            bw.write(vals);
            bw.flush();
            bw.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Establishes a connection and write using DataOutputStream.
     */
    public void writeToSocket() {
        DataOutputStream dos;
        try {
            Socket socket = new Socket(InetAddress.getByName(ADDRESS), PORT);
            dos = new DataOutputStream(socket.getOutputStream());
            // Write to output stream
            dos.writeFloat(float1);
            dos.writeFloat(float2);            dos.writeInt(int1);
            dos.writeInt(int2);

            dos.writeDouble(double1);
            dos.writeDouble(double2);
            dos.writeLong(long1);
            dos.writeLong(long2);

            dos.flush();
            dos.close();

            socket.close();
        }
        catch (UnknownHostException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ReadingWritingData("differentTypes.dat");

        // Use this to test writeNumericValues() method
        // new ReadingWritingData(ReadingWritingData.NUMERIC_FILE_NAME);
    }
}
