import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

public class ReadIntoArrayList {
    public static final String FILENAME = "dic.txt";
    public static void main(String[] args) {
        ArrayList<String> al = new ArrayList<String>();

        try {
            Scanner input = new Scanner(new File(FILENAME));

            while (input.hasNext())
                al.add(input.next());
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("Finished Reading " + al.size() + " words:");
        for (int i = 0; i < al.size(); i++)
            System.out.print(al.get(i) + " ");
        System.out.println();
    }
}
