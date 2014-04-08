/**
 * This is an exception class that gets thrown when the rate of the annual
 * interest rate and/or the number of years the money is invested are not
 * both positive numbers.
 *
 * @author Naoki Mizuno
 */
public class IllegalRateAndYearException extends Exception {
    public IllegalRateAndYearException() {
        super("The rate and the year must both be positive.");
    }
}
