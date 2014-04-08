import javax.swing.JOptionPane;

/**
 * This class is for using and creating your own Exception class.
 * Using the value method, the class assumes 3 potential exceptions,
 * all of them being checked exceptions, and do the approriate action
 * when catching those exceptions.
 *
 * @author Naoki Mizuno
 */

public class Invest {
    /**
     * Returns the interest of the investment i after y years.
     * The parameters are given as a set of amount of money invested,
     * the annual interest rate, the number of years the money is invested,
     * and the number of times the interest is compounded.
     * This method throws exception when the following
     * a. The initial investment is not a positive number
     * b. The interest rate and number of years are not both positive numbers
     * c. The value for c (interest compounded) is less than 1
     * a and b are thrown to the method that called this method, and c is
     * handled by a try-catch statement. Inside this method.
     * The equation given for this calculation is:
     * i * (1 + r/c)^cy
     * @param i Amount of money invested in double (representing the * cents).
     * @param r The annual interest rate in double.
     * @param y The number of years the money is invested.
     * @param c The number of times the interest is compounded.
     * @return The amount of money you get when investing with the given conditions.
     * @throws IllegalInvestmentException When the investment amount is not a positive number.
     * @throws IllegalRateAndYearException When the rate and the year of investment are not both positive numbers.
     */
    public static double value(double i, double r, int y, int c)
        throws IllegalInvestmentException, IllegalRateAndYearException{

        if (i <= 0)
            throw new IllegalInvestmentException();
        else if (!(r > 0 && y > 0))
            throw new IllegalRateAndYearException();
        else if (c < 1) {
            try {
				throw new IllegalInterestCompoundedException();
			}
            catch (IllegalInterestCompoundedException e) {
				JOptionPane.showMessageDialog(null, e + "\nUsing 1 as the number of interest compounded.");
	            c = 1;
			}
        }

        return i * Math.pow((1 + r / c), c * y);
    }

    /**
     * The main method shows an input dialog for the 4 parameters that the
     * value method requires. It shows a JOptionPane and prompts the user the
     * invididual value and pass those values to the value method. Because the
     * value method throws some checked exceptions, it surrounds the calling
     * of the value method with try-catch statement.
     */
    public static void main(String[] args) {
        // Ask the user for the data
        // There is a potential error here where when the input data
        // is invalid, the program may crash.
        try {
            String text = JOptionPane.showInputDialog(null,
                    "Enter the initial investment:");
            double investment = Double.parseDouble(text);

            text = JOptionPane.showInputDialog(null,
                    "Enter the rate of interest in double:");
            double rate = Double.parseDouble(text);

            text = JOptionPane.showInputDialog(null,
                    "Enter the number of years of investment:");
            int year = Integer.parseInt(text);
            
            text = JOptionPane.showInputDialog(null,
                    "Enter the number of compounded investment:");
            int compounded = Integer.parseInt(text);


            try {
                JOptionPane.showMessageDialog(null,
                        String.format("%.2f", value(investment, rate, year, compounded)));
            }
            // Case a
            catch (IllegalInvestmentException e) {
                JOptionPane.showMessageDialog(null, e);
            }
            // Case b
            catch (IllegalRateAndYearException e) {
                JOptionPane.showMessageDialog(null, e);
            }

        }
        // When there was an invalid input
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "There was an invalid input.\nTerminating.");
        }
    }
}
