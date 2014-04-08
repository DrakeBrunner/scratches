import java.util.*;

public class SMITHC_NAOKI_Lab_05
{

    public static void main(String[] args)
    {
    
        Scanner input = new Scanner(System.in);
        
        int human_wins = 0;
        int computer_wins = 0;
        int round_count = 0;
        
      
        String[] weapons = {"Rock", "Paper", "Scissors", "Spock", "Lizard"};
        
        while (round_count < 11) {
            
            System.out.println("Lets play a game of Rock, Paper, Scissors, Spock, Lizard! Enter your choice of weapon using the corresponding number to the menu items below");
            System.out.println("Shoot! (1) Rock | (2) Paper | (3) Scissors | (4) Spock | (5) Lizard");

            
            int computer = (int)(5 * Math.random() + 1);
            
            
            computer += 5;
            
            
            int human = input.nextInt();
            
            if (human + 2 == computer || human + 4 == computer || human + 7 == computer || human + 9 == computer) {
                System.out.println(weapons[human - 1] + " beats " + weapons[computer - 6] + "\t You won the round...");
                human_wins++;
                round_count++;
            }
            else if (human + 5 == computer) {
                System.out.println("You both chose " + weapons[human - 1] + ". It's a tie.");

            }
            else {
                System.out.println(weapons[computer - 6] + " beats " + weapons[human - 1] + "\t Computer wins this round!");
                computer_wins++;
                round_count++;
            }
            
            System.out.printf("Human: %d Computer: %d\n\n", human_wins, computer_wins);
            
            if (human_wins == 6) {
                System.out.println("Congratulations!! You have beaten the computer!!!!");
                break;
            }
            
            if (computer_wins == 6) {
                System.out.println("Sorry, the computer won...");
                break;
            }
            
        }
    
    
    }

}