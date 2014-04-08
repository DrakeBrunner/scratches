import java.util.*;

public class MIZUNON_SMITHCT5_Lab07 {
    public static void main(String[] args) {
        
        System.out.println( max(2, 4) );
        
        System.out.println( max(3, 5, 1) );
        
        System.out.println( getMUID(" Chad", "MizuNO", "smith") );
        
        System.out.println( extractLetters("abcdefgh") );
        
        System.out.println( gravity(5) );

        System.out.println( billPay(20.12, 29.83) );
        
        System.out.println( dateFormatter(12, 10, 90));
        
        System.out.println( secondsToTime(63292) );
        
        System.out.println( charCount("whuio;bohi", 'o') );
        
        System.out.println( palindrome("AABBAA") );
    }
    
    // Number 1 - max() with 2 integers
    public static int max(int num1, int num2){
        int max;
        
        if(num1 >= num2)
            max = num1;
        else
            max = num2;
        
        return max;
    }   

    // Number 2 - max() with 3 integers    
    public static int max(int num1, int num2, int num3){
    
        int max;
        
        if(num1 >= num2 && num1 >= num3)
            max = num1;
        else if(num2 >= num1 && num2 >= num3)
            max = num2;
        else
            max = num3;
            
        return max;
    }
    // Number 3 - getMUID() 
    public static String getMUID(String firstName, String middleName, String lastName){
        
        String muID;
        
        firstName = firstName.trim();
        lastName = lastName.trim();
        middleName = middleName.trim();
        if(firstName.length() < 5){
            
            if(middleName == "")
                muID = ((lastName) + firstName.substring(0,2));
            else
                muID = ((lastName) + firstName.charAt(0) + middleName.charAt(0));
            
        }
        
        else{
            if(middleName == "")
                muID = (lastName.substring(0,6) + firstName.substring(0,2));
            else
                muID = (lastName.substring(0,6) + firstName.charAt(0) + middleName.charAt(0));
        }
        return muID.toUpperCase();
        
    }
    
    // Number 4
    public static String extractLetters(String string) {
        String odd = "";
        
        for (int i = 0; i <= string.length()  - 1; i += 2) {
            odd = odd + string.charAt(i);
        }
        
        return odd;
    }
    
    // Number 5
    public static double gravity(double seconds) {
        return 9.8 * (seconds * seconds);
    }
    
    // Number 6
    public static double billPay(double bill, double amount) {
        if (amount > bill) {
            return (amount - bill);
        }
        else {
            return -1;
        }
    }
    
    // Number 7
    public static String dateFormatter(int month, int date, int year) {
        String formatted_date = "";
        
        if (year < 100) {
            if (year > 53) {
                year += 1900;
            }
            else {
                year += 2000;
            }
        }
        
        formatted_date = String.format("%02d/%02d/%4d", month, date, year);
        
        return formatted_date;
    }
    
    // Number 8
    public static String secondsToTime(int seconds) {
        
        int hours = (int)(seconds / 3600);
        seconds %= 3600;
        
        int minutes = (int)(seconds / 60);
        seconds %= 60;
        
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
    
    // Number 9
    public static int charCount(String string, char what) {
        int count = 0;
        
        for (int i = 0; i <= string.length() - 1; i++) {
            if (string.charAt(i) == what) {
                count++;
            }
        }
        
        return count;
    }
    
    // Number 10
    public static boolean palindrome(String check_this) {
        for (int i = 0; i < check_this.length(); i++) {
            if (check_this.charAt(i) != check_this.charAt(check_this.length() - 1 - i) ) {
                return false;
            }
        }
        
        return true;
    }
}