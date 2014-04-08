import java.util.*;

public class MIZUNON_SMITHCT5_Lab08 {
    // player_x is X and computer recognize it as 1
    static int player_x = 0;
    // player_y is Y and computer recognize it as -1
    static int player_y = 0;
    static int[] status = {0, 0, 0, 0, 0, 0, 0, 0, 0,};
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int menu_choice = 0;

        while (true) {
            drawMenu();

            menu_choice = input.nextInt();
            input.nextLine();
            
            if (menu_choice == 1)
                play();
            else if (menu_choice == 2)
                reset_scores();
            else
                break;
        }
    }

    public static void drawMenu(){
        

        System.out.println("");
        System.out.println("|-----=================----------|");        
        System.out.println("|----|                 |---------|");
        System.out.println("|----| 1. PLAY         |---------|");
        System.out.println("|----| 2. RESET SCORES |---------|");
        System.out.println("|----| 3. QUIT         |---------|");
        System.out.println("|----|                 |---------|");
        System.out.println("|-----=================----------|");
        
                
        
    }

    public static void play() {
        Scanner input = new Scanner(System.in);
        String user_input = "";
        int index_for_status = -1;
        // 1 is X, -1 is O
        int current_player = 1;

        while (true) {
            drawBoard();

            System.out.print("Current Player: ");
            if (current_player == 1)
                System.out.println("X");
            else
                System.out.println("Y");

            System.out.println("Enter column and row (example: A3): ");
            user_input = input.nextLine();
            // User inputs String such as "C2" but we need to convert it to int
            index_for_status = convert_user_input(user_input);
            // convert_user_input returns -1 when error
            if (index_for_status == -1) {
                System.out.println("Invalid selection!");
                continue;
            }

            // If the place is already taken
            if (status[index_for_status] != 0) {
                System.out.println("\nThat place is taken!");
                continue;
            }

            // See if there's a winner
            boolean there_is_a_winner = checkForWin(index_for_status, current_player);
            // This flag is for determining whether it's a tie
            boolean not_a_tie = false;
            for (int i = 0; i < 9; i++) {
                if (status[i] == 0) {
                    not_a_tie = true;
                    break;
                }
            }
            // break if it's a tie
            if (there_is_a_winner == false && not_a_tie != true) {
                System.out.println("It's a tie!");
                clear_board();
                break;
            }

            // Check if either wins and congratulate them if necessary
            if (there_is_a_winner == true) {
                congratulateWinner();
                // 1 is X, -1 is Y
                if (current_player == 1)
                    player_x++;
                else
                    player_y++;

                clear_board();

                break;
            }

            // Switch turns
            current_player *= -1;
        }
    }
    
    public static void drawBoard(){
        // Create an array for displaying the board
        String[] status_for_users = new String[9];
        for (int i = 0; i < 9; i++) {
            if (status[i] == 1)
                status_for_users[i] = "X";
            else if (status[i] == -1)
                status_for_users[i] = "O";
            else
                status_for_users[i] = " ";
        }



        System.out.format("WINS:  Player 1(X): %d,  Player 2(O):%d\n", player_x, player_y);
        System.out.format("       A   B    C\n");
        System.out.println("");
        System.out.format("   1   %s | %s | %s  \n", status_for_users[0], status_for_users[1], status_for_users[2]);
        System.out.format("      ---+---+---\n");
        System.out.format("   2   %s | %s | %s  \n", status_for_users[3], status_for_users[4], status_for_users[5]);
        System.out.format("      ---+---+---\n");
        System.out.format("   3   %s | %s | %s  \n", status_for_users[6], status_for_users[7], status_for_users[8]);
    }
    
    public static int convert_user_input(String user_input){
        user_input = user_input.trim().toUpperCase();

        if (user_input.equals("A1"))
            return 0;
        else if (user_input.equals("B1"))
            return 1;
        else if (user_input.equals("C1"))
            return 2;
        else if (user_input.equals("A2"))
            return 3;
        else if (user_input.equals("B2"))
            return 4;
        else if (user_input.equals("C2"))
            return 5;
        else if (user_input.equals("A3"))
            return 6;
        else if (user_input.equals("B3"))
            return 7;
        else if (user_input.equals("C3"))
            return 8;
        else
            return -1;
    }

    public static boolean checkForWin(int index, int current_player){

        status[index] = current_player;
        
        for (int i = 0; i <= 6; i++) {
            // Check accross when i represents the square on the left side
            if (status[i] == current_player && status[i + 1] == current_player && status[i + 2] == current_player && i % 3 == 0)
                return true;
        }
        for (int i = 0; i <= 2; i++) {
            // Check straight
            if (status[i] == current_player && status[i + 3] == current_player && status[i + 6] == current_player)
                return true;
        }
        // Check diagnol
        if (status[0] == current_player && status[4] == current_player && status[8] == current_player)
            return true;
        else if (status[2] == current_player && status[4] == current_player && status[6] == current_player)
            return true;
        else
            return false;
    }
    
    public static void congratulateWinner() {
        System.out.println("\n*********************************************************");
        System.out.println("  Congratulations, there is the winner for this round!!");
        System.out.println("*********************************************************");
    }
    
    public static void reset_scores() {
        System.out.println("Scores have been reset!");
        System.out.println("The final scores were");
        System.out.printf("X: %d Y: %d so ", player_x, player_y);
        if (player_x > player_y)
            System.out.println("Player X wins overall!");
        else if (player_y > player_y)
            System.out.println("Player Y wins overall!");
        else
            System.out.println("it's a tie overall!");

        player_x = player_y = 0;
    }

    public static void clear_board() {
        // Clear the board for the new game!
        for (int i = 0; i < 9; i++)
            status[i] = 0;
    }
}
