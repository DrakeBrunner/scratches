import java.util.*;
import java.io.*;

public class mizunon_Lab10 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        ArrayList<String> book = new ArrayList<String>();
        boolean continue_loop = true;

        while (continue_loop) {
            switch (menu()) {
                case 1:
                    System.out.print("Friend to add: ");
                    String to_add = input.nextLine();
                    add(book, to_add);
                    break;
                case 2:
                    System.out.print("Friend to find: ");
                    String to_find = input.nextLine();
                    int index = find(book, to_find);

                    if (index != -1)
                        System.out.printf("%s found at index %d\n", to_find, index);
                    else
                        System.out.println(to_find + " was not found!");

                    break;
                case 3:
                    show(book);
                    break;
                case 4:
                    try {
                        book = load();
                        System.out.println("Loaded and made ready!");
                    }
                    catch (Exception e) {
                        System.out.println("Error when loading file!\n" + e);
                    }
                    break;
                case 5:
                    try {
                        save(book);
                        System.out.println("Successfully Saved!");
                    }
                    catch (Exception e) {
                        System.out.println("Error when writing to file!\n" + e);
                    }
                    break;
                case 6:
                    continue_loop = false;
                    break;
            }
        }
    }

    public static int menu() {
        Scanner input = new Scanner(System.in);

        System.out.println("1. Add Friend");
        System.out.println("2. Find Friend");
        System.out.println("3. Show All Friends");
        System.out.println("4. Load Address Book");
        System.out.println("5. Save Address Book");
        System.out.println("6. Exit");

        int choice = input.nextInt();
        input.nextLine();
        return choice;
    }

    public static void add(ArrayList<String> book, String to_add) {
        book.add(to_add);
    }

    public static int find(ArrayList<String> book, String to_find) {
        return book.indexOf(to_find);
    }

    public static void show(ArrayList<String> book) {
        for (String line : book)
            System.out.println(line);
    }

    public static ArrayList<String> load() throws Exception{
        Scanner input = new Scanner(System.in);

        ArrayList<String> ret = new ArrayList<String>();

        System.out.print("File name: ");
        String filename = input.nextLine().trim();
        if (filename.equals(""))
            filename = "AddressBook.txt";

        BufferedReader br = new BufferedReader( new FileReader(filename) );

        String line = br.readLine();
        while (line != null) {
            ret.add(line);
            line = br.readLine();
        }

        return ret;
    }

    public static void save(ArrayList<String> book) throws Exception{
        Scanner input = new Scanner(System.in);
        boolean append = true;

        System.out.print("File Name: ");
        String filename = input.nextLine().trim();
        // Set as default file name if nothing was specified
        if (filename.equals(""))
            filename = "AddressBook.txt";

        System.out.print("Append? (Y/n)");

        if (input.nextLine().equals("n"))
            append = false;

        BufferedWriter bw = new BufferedWriter( new FileWriter(filename, append) );

        for (String s : book) {
            bw.write(s);
            bw.write("\n");
        }
        bw.flush();
        bw.close();
    }
}