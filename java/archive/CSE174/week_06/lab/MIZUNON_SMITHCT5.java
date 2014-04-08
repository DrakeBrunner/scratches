import java.util.*;

public class MIZUNON_SMITHCT5 {
    public static void main(String args[]) {
        Scanner input = new Scanner(System.in);
        
        boolean continue_loop = true;
        
        while (continue_loop == true) {
            System.out.print("1. Days in month\n2. Times table\n3. Exit\n");
            
            switch (input.nextInt()) {
                case 1: daysInMonth(); break;
                case 2: timesTable(); break;
                default: continue_loop = false;
            }
        }
    }
    
    public static void daysInMonth() {
        Scanner input = new Scanner (System.in);
        
        int month = 0;
        int year = 0;
        int days = 0;
        
        System.out.println("Enter the number corresponding to what month it is (example: 3 = March)");
        month = input.nextInt();
        
        System.out.println("Enter the current year");
        year = input.nextInt();
            
        // Leap Year Code
        if (((year % 4 == 0) &&!(year % 100 == 0)) || (year % 400 == 0)){
                
            switch(month){
                
                case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                    days = 31;
                    break;
                case 4: case 6: case 9: case 11:
                    days = 30;
                    break;
                case 2:
                    days = 29;
                    break;
            }
        }
        else {
        
            switch(month){
                
                case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                    days = 31;
                    break;
                case 4: case 6: case 9: case 11:
                    days = 30;
                    break;
                case 2:
                    days = 28;
                    break;
                }
            }

            System.out.println("The number of days within the " + month + " month in the year of " + year + " is " + days);

        draw_border();
    }
    
    public static void timesTable() {
        // Print the numbers
        System.out.print("   ");
        for (int i = 1; i <= 10; i++) {
            System.out.format("%4d", i);
        }
        System.out.println();
        
        // Print the lines
        System.out.print("   ");
         for (int i = 1; i <= 10; i++) {
            System.out.print(" ---");
        }
        System.out.println();
        
        // Print the table
        for (int i = 1; i <= 10; i++) {
            System.out.format("%2d|", i);
            for (int j = 1; j <= 10; j++) {
                System.out.format(" %03d", i * j);
            }
            System.out.println();
        }
        // It looks better when entering the next loop
        System.out.println();
        draw_border();
    }
    
    public static void draw_border() {
        for (int i = 0; i < 45; i++) {
            System.out.print("*");
        }
        System.out.println();
    }
}