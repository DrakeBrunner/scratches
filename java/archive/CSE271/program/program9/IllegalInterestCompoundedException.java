/**
 * This is an Exception class that will be thrown when the number of times the
 * interes is compounded is not a positive integer.
 *
 * @author Naoki Mizuno
 */
public class IllegalInterestCompoundedException extends Exception {
    public IllegalInterestCompoundedException() {
        super("The number of times the interest is compounded should be a positive integer.");
    }
}
