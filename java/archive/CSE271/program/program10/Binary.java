import java.util.*;
import java.io.*;

/**
 * This class reads/writes binary files. A prompt is shown and the user can
 * choose several actions, including printing the content of the binary file,
 * searching, writing the searched results to a user-specified binary file.
 * The schema file will only contain int, char, and double
 * @author Naoki Mizuno
 * TODO: Maximize code reuse by creating helper methods to read schema file, and binary file
 * TODO: Put those helper methods in main so that the other methods just needs to use the ArrayList to print
 */

public class Binary {
    // Total bytes in the schema file's variable type
    private static int chunkByte = 0;
    private static ArrayList<String> schemaContent = new ArrayList<String>();
    /**
     * Shows a menu of actions that the user can perform.
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        // Ask for schema file location
        System.out.print("Enter name of schema file: ");
        String schema = input.nextLine();
        // Ask for binary file location
        System.out.print("Enter name of binary file: ");
        String binary = input.nextLine();

        // Read schema file
        try {
            readSchema(schema);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int choice;
        while ((choice =showMenu()) != 6) {
            switch (choice) {
                case 1:
                    showSchema();
                    break;
                case 2:
                    showBinary(binary);
                    break;
                case 3:
                    System.out.print("Enter text file name: ");
                    writeBinary(binary, input.nextLine());
                    break;
                case 4:
                    searchAndShow(binary);
                    break;
                case 5:
                    System.out.print("Enter name of binary file to save data in: ");
                    searchAndWrite(binary, input.nextLine());
                    break;
            }
        }
    }

    /**
     * Shows a formated menu of actions that the user can choose.
     * @return The number in the menu that the user choose.
     */
    public static int showMenu() {
        System.out.println("Supported operations:");
        System.out.println("\t1. Print schema");
        System.out.println("\t2. Print binary data on screen");
        System.out.println("\t3. Print binary data to text file");
        System.out.println("\t4. Search and print data on screen");
        System.out.println("\t5. Search and save data to binary file");
        System.out.println("\t6. Quit");
        System.out.print("Enter your choice: ");

        String choice = new Scanner(System.in).nextLine();
        return Integer.parseInt(choice);
    }

    /**
     * Read the content of the schema file, which it's name is given by the
     * parameter.
     * @param fileName The filename of the schema file.
     * @throws FileNotFoundException When the given filename is not found.
     */
    public static void readSchema(String fileName) throws FileNotFoundException {
        Scanner read = new Scanner(new File(fileName));
        while (read.hasNext()) {
            String type = read.nextLine();
            schemaContent.add(type);
            if (type.equals("int"))
                chunkByte += 4;
            if (type.equals("char"))
                chunkByte += 2;
            if (type.equals("double"))
                chunkByte += 8;
        }

        // Exit the program when there is nothing in the schema file.
        if (schemaContent.size() == 0) {
            System.out.println("Empty schema file!");
            System.exit(0);
        }
    }

    /**
     * Prints the data in the binary file onto the screen according to the
     * content of the schema file.
     * @param binary The filename of the binary file that will be read from.
     */
    public static void showBinary(String binary) {
        // Use DataInputStream to read in the binary
        DataInputStream input;
        try {
            input = new DataInputStream(new FileInputStream(binary));

            // Read in the data according to schema
            // We know that the schema file only contains int, char, and double
            while (true) {
                try {
                    String s = "";
                    for (int i = 0; i < schemaContent.size(); i++) {
                        if (schemaContent.get(i).equals("int"))
                            s += input.readInt();
                        if (schemaContent.get(i).equals("char"))
                            s += "'" + input.readChar() + "'";
                        if (schemaContent.get(i).equals("double"))
                            s += input.readDouble();

                        s += ", ";
                    }
                    // Remove the last ", " so that it corresponds to the
                    // format shown in the example
                    System.out.println(s.substring(0, s.length() - 2));
                }
                catch (IOException e) {
                    // Break when reaching the end of file
                    break;
                }
            }   // End of while loop

        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the content of the schema file.
     * Uses the ArrayList that is filled after given the filename of the
     * schema so there is no worry that the schemaContent is null.
     */
    public static void showSchema() {
        for (int i = 0; i < schemaContent.size(); i++)
            System.out.println(schemaContent.get(i));
    }

    /**
     * Writes the binary data to a text file that the user specified.
     * It reads from one binary file according to the schema and
     * writes that to the other file.
     * @param from The filename of the binary file that is read from.
     * @param to The filename of the binary file that will be written to.
     */
    public static void writeBinary(String from, String to) {
        DataInputStream in;
        PrintWriter out;
        try {
            in = new DataInputStream(new FileInputStream(from));
            out = new PrintWriter(new File(to));
            try {
                while (true) {
                    String line = "";
                    for (int i = 0; i < schemaContent.size(); i++) {
                        if (schemaContent.get(i).equals("int"))
                            line += in.readInt();
                        if (schemaContent.get(i).equals("char"))
                            line += "'" + in.readChar() + "'";
                        if (schemaContent.get(i).equals("double"))
                            line += in.readDouble();

                        line += ", ";
                    }
                    out.println(line.substring(0, line.length() - 2));
                }
            }
            catch (EOFException e) {
                // This is caused by DataInputStream
                // This means that it's the end of the input file.
                // Nothing has to be done
            }
            catch (IOException e) {
                // This is caused by the DataOutputStream
                e.printStackTrace();
            }
            finally {
                out.flush();
                out.close();
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * Search for whatever the user inputs from the binary file and
     * print the result to the screen.
     * @param filename The filename of the binary file that will be searched.
     */
    public static void searchAndShow(String filename) {

        System.out.printf("Enter search value of type '%s': ",
                schemaContent.get(0));
        String val = new Scanner(System.in).nextLine();

        try {
            DataInputStream in = new DataInputStream(new FileInputStream(filename));
            // Proceed by chunk
            int limit = in.available();
            for (int i = 0; i < limit;) {
                if (schemaContent.get(0).equals("int")) {
                    int val_int = Integer.parseInt(val);
                    if (in.readInt() == val_int)
                        System.out.println(getChunk(val_int + ", ", in));
                    else
                        in.skipBytes(chunkByte - 4);
                    i += 4;
                }
                if (schemaContent.get(0).equals("char")) {
                    char val_char = val.charAt(0);
                    if (in.readChar() == val_char)
                        System.out.println(getChunk("'" + val_char + "'" + ", ", in));
                    else
                        in.skipBytes(chunkByte - 2);
                    i += 2;
                }
                if (schemaContent.get(0).equals("double")) {
                    double val_double = Double.parseDouble(val);
                    if (in.readDouble() == val_double)
                        System.out.println(getChunk(val_double + ", ", in));
                    else
                        in.skipBytes(chunkByte - 8);
                    i += 8;
                }
            }
        }
        catch (IOException e) {
            // Reaching here simply means that the file has reached the end
            // However, spitting out an EOFException means that there was
            // something wrong with the schema file, since it should increase the i,
            // which represents the bytes count, according to the data type.
            // For example, when the schema says that the binary file contains
            // (double double) but the actual file contained (int double
            // double), then the total bytes available won't be divisible by
            // the chunk's byte.
            // Although this is not a critical error, I included the following
            // line of code for the purpose of informing the user that there
            // went something wrong while reading the binary file.
            e.printStackTrace();
        }
    }

    /**
     * Returns a String representation of one chunk of data (one schema file)
     * from a DataInputStream. The data will be separated by ", ".
     * Note that the DataInputStream will start from the last byte read, so
     * when using in the searchAndShow method, be careful about the first
     * data.
     * This method assumes that one data has already been read from the chunk,
     * and that that data is contained in the given String.
     * Thus, the for loop starts from 1, ignoring the first data.
     * @param s The String that the other data will be concatenated to.
     * @param in A DataInputStream object of a file that will be read from.
     */
    public static String getChunk(String s, DataInputStream in)
            throws IOException {
        for (int j = 1; j < schemaContent.size(); j++) {
            if (schemaContent.get(j).equals("int"))
                s += in.readInt();
            if (schemaContent.get(j).equals("char"))
                s += "'" + in.readChar() + "'";
            if (schemaContent.get(j).equals("double"))
                s += in.readDouble();
            s += ", ";
        }
        return s.substring(0, s.length() - 2);
    }

    /**
     * Search whatever the user inputs and write the result to a file as a
     * binary file.
     * @param from The binary file that the input will be searched.
     * @param to The filename that the matched results will be written to.
     */
    public static void searchAndWrite(String from, String to) {

        System.out.printf("Enter search value of type '%s': ",
                schemaContent.get(0));
        String val = new Scanner(System.in).nextLine();

        try {
            DataInputStream in = new DataInputStream(new FileInputStream(from));
            DataOutputStream out = new DataOutputStream(new FileOutputStream(to));
            // Proceed by chunk
            int limit = in.available();
            for (int i = 0; i < limit;) {
                if (schemaContent.get(0).equals("int")) {
                    int val_int = Integer.parseInt(val);
                    if (in.readInt() == val_int) {
                        out.writeInt(val_int);
                        writeChunk(in, out);
                    }
                    else
                        in.skipBytes(chunkByte - 4);
                    i += 4;
                }
                if (schemaContent.get(0).equals("char")) {
                    char val_char = val.charAt(0);
                    if (in.readChar() == val_char) {
                        out.writeChar(val_char);
                        writeChunk(in, out);
                    }
                    else
                        in.skipBytes(chunkByte - 2);
                    i += 2;
                }
                if (schemaContent.get(0).equals("double")) {
                    double val_double = Double.parseDouble(val);
                    if (in.readDouble() == val_double) {
                        out.writeDouble(val_double);
                        writeChunk(in, out);
                    }
                    else
                        in.skipBytes(chunkByte - 8);
                    i += 8;
                }
            }
        }
        catch (IOException e) {
        }
    }

    /**
     * Writes one chunk of data to a binary file specified by the given
     * DataOutputStream. It will read from the also given DataInputStream.
     * Note that this method assumes the first element in the schema file to
     * be written via the same DataOutputStream prior to the calling of this
     * method.
     * @param in A DataInputStream object that contains information about the binary file to be read from.
     * @param out A DataOutputStream object that will be used to write the chunk to.
     */
    public static void writeChunk(DataInputStream in, DataOutputStream out)
            throws IOException {
        for (int i = 1; i < schemaContent.size(); i++) {
            if (schemaContent.get(i).equals("int"))
                out.writeInt(in.readInt());
            if (schemaContent.get(i).equals("char"))
                out.writeChar(in.readChar());
            if (schemaContent.get(i).equals("double"))
                out.writeDouble(in.readDouble());
        }
    }
}
