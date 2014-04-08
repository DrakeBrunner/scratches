import java.util.*;
public class Week_05_TueNotes {
    public static void main(String args[]) {
        Scanner input = new Scanner(System.in);
        
        //******************************************************************************
        System.out.print("Please input a month number: ");
        int number = input.nextInt();
        System.out.print("Do you want to use the short version? (true/false) ");
        boolean use_short = input.nextBoolean();
        System.out.println("The month you chose is " + month( number, use_short ));
        input.nextLine();
        //******************************************************************************
        
        String stu01 = "", stu02 = "", stu01Letter = "", stu02Letter = "";
        int stu01Grade = 0, stu02Grade = 0;
        
        System.out.print("Enter student 1's Name: "); stu01 = input.nextLine();
        System.out.print("Enter student 2's Name: "); stu02 = input.nextLine();
        
        System.out.printf("Enter %s's Grade: ", stu01); stu01Grade = ABS( input.nextInt() );

        System.out.printf("Enter %s's Grade: ", stu02); stu02Grade = ABS( input.nextInt() );
                
        System.out.println("***********************************************************");
        
        stu01Letter = LetterGrade(stu01Grade);
        stu02Letter = LetterGrade(stu02Grade);
        
        System.out.printf("%s's grade is %d (%s)\n", stu01, stu01Grade, stu01Letter);
        System.out.printf("%s's grade is %d (%s)\n", stu02, stu02Grade, stu02Letter);
    }
    
    public static String LetterGrade(int grade) {
        String ret = "";
        
        if (grade >= 90) { ret ="A"; }
        else if (grade >= 80) { ret = "B"; }
        else if (grade >= 70) { ret = "C"; }
        else if (grade >= 60) { ret = "D"; }
        else { ret = "E"; }
        
        return ret;
    }
    
    public static int ABS(int score) {
        if (score < 0) { return -1 * score; }
        return score;
    }
    
    public static String month(int month_number, boolean use_short) {
        String[] months = {"January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December", };
        
        if (use_short == true && month_number >= 0 && month_number < 12) {
            return(months[month_number - 1].substring(0,3) + ".");
        }
        
        if (month_number >= 0 && month_number < 12) {
            return months[month_number -1];
        }
        return "Invalid";
    }
}
