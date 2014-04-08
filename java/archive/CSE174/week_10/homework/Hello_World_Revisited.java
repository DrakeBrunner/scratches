import java.util.*;

public class Hello_World_Revisited {
    public static void main(String[] args) {
        int rounds = getPos();

        for (int i = 0; i < rounds; i++) {
            System.out.println("Hello There");
        }

    }

    public static int getPos() {
        Scanner input = new Scanner(System.in);

        int user_input;
        while (true) {

            System.out.println("Please input a positive integer");
            user_input = input.nextInt();

            if (user_input > 0)
                break;
        }

        return user_input;
    }
}
