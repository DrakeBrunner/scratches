/**
 * This is an exception class that gets thrown when the initial money that was
 * invesested is not a positive number.
 *
 * @author Naoki Mizuno
 */
public class IllegalInvestmentException extends Exception {
    public IllegalInvestmentException() {
        super("The investent amount should be more than zero.");
    }
}
