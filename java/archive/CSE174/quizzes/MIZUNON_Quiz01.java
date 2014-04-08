import java.util.*;

public class MIZUNON_Quiz01 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Flag
        boolean continue_loop = true;

        while (continue_loop == true) {
            switch (MenuPrintAndSelect()) {
                case 1:
                    System.out.println( Magic_8_ball( input.nextLine() ) );
                    break;
                case 2:
                    System.out.println( Pig_Latin( input.nextLine() ) );
                    break;
                case 3:
                    System.out.println( Random_Card() );
                    break;
                case 4:
                    continue_loop = false;
            }
        }
    }

    public static int MenuPrintAndSelect() {
        Scanner input = new Scanner(System.in);
        
        System.out.print("1. Magic 8-Ball\n2. Pig Latin\n3. Random Card\n4. Exit\n");
        int choice = input.nextInt();
        // Clear buffer
        input.nextLine();

        return choice;
    }

    public static String Magic_8_ball(String arg) {
        int length = arg.length();

        boolean it_is_even;
        if (length % 2 == 0)
            it_is_even = true;
        else
            it_is_even = false;

        if (length <= 10) {
            if (it_is_even)
                return "Yes, in due time.";
            else
                return "My sources say no.";
        }
        else if (length <= 20) {
            if (it_is_even)
                return "Definitely not.";
            else
                return "YES!";
        }
        else if (length <= 25) {
            if (it_is_even)
                return "You will have to wait.";
            else
                return "I have my doubts.";
        }
        else if (length <= 30) {
            if (it_is_even)
                return "Outlook so so.";
            else
                return "Looks good to me!";
        }
        else {
            if (it_is_even)
                return "NO!";
            else
                return "Are you kidding?";
        }
    }

    public static String Pig_Latin(String word) {
        word = word.trim().toLowerCase();

        // Return error if there are more than 2 words
        for (int i = 0; i < word.length() - 1; i++) {
            if (word.charAt(i) == ' ') {
                return "Only one word, please!";
            }
        }

        if (word.charAt(0) == 'a' || word.charAt(0) == 'e' || word.charAt(0) == 'i'
                || word.charAt(0) == 'o' || word.charAt(0) == 'u')
            return word + "hay";
        else
            return word.substring(1, word.length()) + word.charAt(0) + "ay";
    }

    public static String Random_Card() {
        String ret;

        int number = (int)(Math.random() * 13 + 1);
        switch (number) {
            case 1:
                ret = "Ace";
                break;
            case 11:
                ret = "Jack";
                break;
            case 12:
                ret = "Queen";
                break;
            case 13:
                ret = "King";
                break;
            default:
                ret = String.format("%d", number);
        }

        int suit = (int)(Math.random() * 4 + 1);
        switch (suit) {
            case 1:
                ret += " of Spades";
                break;
            case 2:
                ret += " of Hearts";
                break;
            case 3:
                ret += " of Diamonds";
                break;
            case 4:
                ret += " of Clubs";
                break;
        }

        return ret;
    }
}