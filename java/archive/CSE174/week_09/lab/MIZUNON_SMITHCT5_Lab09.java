import java.util.*;

public class MIZUNON_SMITHCT5_Lab09 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String[] names = {"Skippers", "Gilligan", "Professor", "Mary", "Cindy",};

        System.out.print("Please input what to search: ");
        String search_for = input.nextLine();
        int count = 0;

        for (int i = 0; i < names.length; i++) {
            for (int j = 0; j < names[i].length(); j++) {
                if (j == names[i].trim().toLowerCase().indexOf(search_for))
                    count++;
            }
        }

        System.out.println("The letter " + search_for + " appeared " + count + " times");
    }
}