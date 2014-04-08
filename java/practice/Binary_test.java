import java.io.*;
import java.util.*;

public class Binary_test {
    public static void main(String[] args) {
        String binFile = "test.bin";

        try {
            DataOutputStream out = new DataOutputStream(new FileOutputStream(binFile));
            ObjectOutputStream oOut = new ObjectOutputStream(new FileOutputStream("cal.bin"));
            Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
            cal.clear();
            cal.set(2011, Calendar.OCTOBER, 10);
            oOut.writeObject(cal);
            /* out.writeInt(1); */
            /* out.writeInt(2); */
            /* out.writeInt(3); */
            /* out.writeInt(4); */
            /* out.writeInt(5); */
            out.writeUTF("hello");
            out.writeUTF("world!!!");
        }
        catch (IOException e) {
            e.printStackTrace();
        }


        try {
            DataInputStream in = new DataInputStream(new FileInputStream(binFile));
            ObjectInputStream oIn = new ObjectInputStream(new FileInputStream("cal.bin"));
            Calendar got = (Calendar)oIn.readObject();
            System.out.println(got.getTime());

            String s = in.readUTF();
            System.out.println(s);
            s = in.readUTF();
            System.out.println(s);

            /* System.out.println("Available (should be 20): " + in.available()); */
            /* for (int i = 0; i <= in.available(); i++) { */
            /*     int tmp; */
            /*     if ((tmp= in.readInt()) == 5) */
            /*         System.out.printf("Detected %d\n", tmp); */
            /*     else */
            /*         in.skipBytes(4); */
            /* } */

            /*
            System.out.println("Read 1 int");
            System.out.println(in.readInt());
            System.out.println("Read another int");
            System.out.println(in.readInt());
            System.out.println("Skip 4 bytes (= size of int)");
            in.skipBytes(4);
            System.out.println("Read int");
            System.out.println(in.readInt());
            System.out.println("Skip -8 bytes (ignored when negative)");
            in.skipBytes(-8);
            System.out.println("Read int (should be the same as second)");
            System.out.println(in.readInt());
            */
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
