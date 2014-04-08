import java.util.*;
import java.io.*;

public class Scanner_test {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("File name: ");
        String fileName = input.nextLine();

        try {
            Scanner file = new Scanner(new File(fileName));

            String line;
            while (!(line = file.nextLine()).equals("")) {
                System.out.println("Now at: " + line);
                System.out.println("Next Line is not empty");
            }
        }
        catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }
}
