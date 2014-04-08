import java.util.*;

public class Week_04_TueNotes {
    public static void main(String args[]) {
        
        Scanner stdin = new Scanner(System.in);
        String miamiid = "";
        int flag = 0;
        
        System.out.print("First name? ");  String first = stdin.nextLine();
        System.out.print("Last name? ");   String last = stdin.nextLine();
        first = first.trim();
        last = last.trim();
        
        first = first.toUpperCase();
        last = last.toUpperCase();
        
        if (last.length() < 6) {
            miamiid += last.substring(0, last.length());
            if (last.length() == 0) {
                flag = 1;
            }
        }
        else {
            miamiid += last.substring(0, 6);
        }
        
        if (first.length() != 0) {
            miamiid += first.substring(0,1);
        }
        else {
            flag = 1;
        }

        if (flag == 0) {
            System.out.println("Your Miami ID is: " + miamiid);
        }
        
        
        
        Scanner input = new Scanner(System.in);
        int die1 = 0;
        int die2 = 0;
        int tries = 0;
        
        int doubles = 0;
        
        System.out.print("What is your guess?");
        int user_guess = input.nextInt();
        
        do {
            die1 = (int)(6 * Math.random() + 1);
            die2 = (int)(6 * Math.random() + 1);
            
            System.out.printf("%d, %d\n", die1, die2);
            
            if (die1 == die2) {
                doubles++;
            }
            
            tries++;
        } while (die1 + die2 != 12);
        
        System.out.printf("It took you %d rolls to get double 6's!\nAnd you rolled %d doubles including 6's!\n", tries, doubles);
//        System.out.println("It took you " + tries + " rolls to get double 6's!");
//        System.out.printf("And you rolled %d doubles including 6's!", doubles);
        
        if (user_guess == tries) {
            System.out.println("Congratulations! You're guess was right!");
        }
        else if (user_guess > tries) {
            System.out.println("You're guess was too high!");
        }
        else {
            System.out.println("You're guess was too low!");
        }
    }
}