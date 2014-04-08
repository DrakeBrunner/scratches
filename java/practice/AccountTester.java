import java.util.Scanner;
import java.util.Random;

class Account {
    // Field
    private String name_var;
    private String number_var;
    private int balance_var;

    // Constructer
    Account (String n, String num, int b) {
        name_var = n;
        number_var = num;
        balance_var = b;
    }

    // Methods
    String name() {
        return name_var;
    }

    String number() {
        return number_var;
    }

    int balance() {
        return balance_var;
    }

    void deposit(int dep) {
        balance_var += dep;
    }

    void withdraw(int wthdrw) {
        balance_var -= wthdrw;
    }

}

class AccountTester {

    public static void main(String[] args) {
        // Naoki's Account
        Account naoki = new Account("Naoki", "12345", 90000);

        // Tetsushi's Account
        Account tet = new Account("Tetsushi", "67890", 500);

        System.out.print("Naoki's Account Information"); 
        System.out.print("Name: ");
        System.out.println(naoki.name());
        System.out.print("Account Number: ");
        System.out.println(naoki.number());
        System.out.print("Balance: ");
        System.out.println(naoki.balance());
        System.out.print("Tetsushi's Account Information"); 
        System.out.print("Name: ");
        System.out.println(tet.name());
        System.out.print("Account Number: ");
        System.out.println(tet.number());
        System.out.print("Balance: ");
        System.out.println(tet.balance());
    }
}