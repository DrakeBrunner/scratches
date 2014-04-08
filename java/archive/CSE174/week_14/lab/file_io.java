import java.util.*;
import java.io.*;

public class file_io {
    public static void main(String[] args) {
        try {
            String[] names = readNames("names.txt");

            for (int i = 0; i < names.length; i++)
                System.out.println(names[i]);
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public static String[] readNames(String FileName) throws Exception {
        BufferedReader br = new BufferedReader( new FileReader(FileName) );

        int index = 0;
        String[] ret = new String[4];
        String fn = br.readLine();
        String ln = "";

        while (fn != null) {
            ln = br.readLine();
            ret[index] = ln + ", " + fn;
            index++;
            fn = br.readLine();
        }

        return ret;
    }
}
    /*
    public static void main(String[] args) {
        try {
            writeByBuffer("MyFile.txt", "Hello, World\n", true);

            System.out.println(readByBuffer("MyFile.txt"));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void writeByBuffer(String FileName, String data, boolean append) throws Exception {
        BufferedWriter bw = new BufferedWriter( new FileWriter(FileName, append) );

        bw.write(data);
        bw.flush();
        bw.close();
    }
    
    public static String readByBuffer(String FileName) throws Exception {
        BufferedReader br = new BufferedReader( new FileReader(FileName) );
        StringBuilder sb = new StringBuilder();

        String line = br.readLine();

        while (line != null) {
            sb.append(line);
            sb.append("\n");
            line = br.readLine();
        }

        return sb.toString();
    }
}
*/