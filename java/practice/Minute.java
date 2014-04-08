import java.util.Scanner;

/**
 * This program reads in a time and determines the time one minute later
 */

public class Minute {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        for (int i = 0; i < 10; i++) {
            String line = input.nextLine();
            int index = line.indexOf(':');
            int hour = Integer.parseInt(line.substring(0, index));
            int minute = Integer.parseInt(line.substring(index + 1, index + 3));
            String ampm = line.substring(index + 3);


            if (minute == 59) {
                // When we have to change the am/pm
                if (hour == 11) {
                    // Switch am and pm according to the first letter
                    if (ampm.equals("am"))
                        ampm = "pm";
                    else
                        ampm = "am";
                }
                // Hour will be incremented in all cases
                hour++;
            }
            minute++;
            hour %= 12;
            if (hour == 0)
                hour += 12;
            minute %= 60;

            System.out.printf("%d:%02d%s\n", hour, minute, ampm);
        }
        input.close();
    }
}