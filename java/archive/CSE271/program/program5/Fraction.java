/**
 * Class for calculating fractions
 * @author Naoki Mizuno
 */

class Fraction {
    private int numerator;      // The upper part of fraction
    private int denominator;    // The lower part of fraction

    /**
     * Constructs a fraction with denominator being 1
     * when only 1 parameter is specified
     * @param numerator The upper part of the fraction
     */
    public Fraction(int numerator) {
        this(numerator, 1);
    }

    /**
     * Constructs a fraction with specified denominator and numerator
     * @param numerator The numerator of the fraction
     * @param denominator The denominator of the fraction
     */
    public Fraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    /**
     * Constructs a fraction from a String separated by a '/'
     * @param fractionString String representation of the fraction
     */
    public Fraction(String fractionString) {
        // Split the string
        String[] dn = fractionString.split("/");

        this.numerator = Integer.parseInt(dn[0]);
        this.denominator = Integer.parseInt(dn[1]);
    }

    /**
     * Getter method for numerator
     * @return value of numerator
     */
    public int getNumerator() {
        return numerator;
    }

    /**
     * Getter method for denominator
     * @return value of denominator
     */
    public int getDenominator() {
        return denominator;
    }

    /**
     * Returns double value for the fraction
     * @return double value for the fraction
     */
    public double toDouble() {
        return (double)numerator / (double)denominator;
    }

    /**
     * Returns a String representation of the fraction
     * in the form of "n/d"
     * @return String representation of the fraction
     */
    public String toString() {
        return numerator + "/" + denominator;
    }

    /**
     * Returns a String representation of the function as a mixed number
     * @return mixed number String representation of the fraction
     */
    public String toMixedNumber() {
        int i = (int)(numerator / denominator);
        // If the fraction is an int
        if (i == toDouble())
            return i + "";
        else if (i != 0)
            return i + " " + Math.abs(numerator - i * denominator) + "/" + Math.abs(denominator);
        else
            return toString();
    }

    /**
     * Returns a 3-line String representation of the fraction,
     * where the numerator is above the denominator separated
     * by a fraction bar made of hyphens
     * @return a 3-line String representation
     */
    public String toPrettyString() {
        // Count how many digits
        int i = 10;
        int numeratorDigit = 1;
        int denominatorDigit = 1;
        while (!(numerator % i == numerator && denominator % i == denominator)) {
            if (numerator % i != numerator)
                numeratorDigit++;
            if (denominator % i != denominator)
                denominatorDigit++;
            i *= 10;
        }

        // Account for the negative sign
        if (numerator < 0)
            numeratorDigit++;
        if (denominator < 0)
            denominatorDigit++;

        String ret = "";

        // Add space(s) in front of numerator
        for (i = 0; i < (numeratorDigit < denominatorDigit ? 1 + (denominatorDigit - numeratorDigit) / 2 : 1); i++)
            ret += " ";
        ret += numerator + "\n";

        // Add fraction bar
        for (i = 0; i < 2 + (numeratorDigit > denominatorDigit ? numeratorDigit : denominatorDigit); i++)
            ret += "-";
        ret += "\n";

        // Add space(s) in front of denominator
        for (i = 0; i < (numeratorDigit > denominatorDigit ? 1 + (numeratorDigit - denominatorDigit) / 2 : 1); i++)
            ret += " ";
        return ret + denominator;
    }

    /**
     * Changes signs if both numbers are negative
     */
    public void fixSigns() {
        if (numerator < 0 && denominator < 0) {
            numerator *= -1;
            denominator *= -1;
        }
    }

    /**
     * Returns the absolute value of the greatest common divisor of the
     * fraction using the Euclidean algorithm
     * @return the greatest common divisor of the fraction
     */
    private int gcd() {
        int a = Math.abs(numerator);
        int b = Math.abs(denominator);
        // If either is 0, return 0
        if (numerator * denominator == 0)
            return 0;
        while (a != 0 && b != 0) {
            if (a > b)
                a %= b;
            else
                b %= a;
        }
        return a > b ? a : b;
    }

    /**
     * Changes the numerator and denominator to the lowest terms
     */
    public void simplify() {
        int gcd = gcd();
        // Change signs first
        fixSigns();
        // Move negative sign to the numerator
        if (denominator < 0) {
            numerator *= -1;
            denominator *= -1;
        }

        // Don't do anything when dividing by zero
        if (gcd == 0)
            return;
        else {
            numerator /= gcd;
            denominator /= gcd;
        }
    }

    /**
     * Returns a new Fraction that is the reciprocal of the fraction
     * @return new Fraction that is the reciprocal of the fraction
     */
    public Fraction getReciprocal() {
        // Don't worry, it's primative data type
        return new Fraction(denominator, numerator);
    }

    /**
     * Multiplies this fraction with the given Fraction
     * @param other Fraction that is multiplied with "this" fraction
     * @return the result of multiplication
     */
    public Fraction multiply(Fraction other) {
        Fraction ret =  new Fraction(this.numerator * other.numerator, this.denominator * other.denominator);
        ret.simplify();
        return ret;
    }

    /**
     * Divides this fraction with the given Fraction
     * @param other Fraction that is divided with "this" fraction
     * @return the result of division
     */
    public Fraction divide(Fraction other) {
        Fraction ret =  new Fraction(this.numerator * other.denominator, this.denominator * other.numerator);
        ret.simplify();
        return ret;
    }

    /**
     * Adds this fraction to the given Fraction
     * @param other Fraction that is added with "this" fraction
     * @return the result of addition
     */
    public Fraction add(Fraction other) {
        Fraction ret =  new Fraction((this.numerator * other.denominator) + (other.numerator * this.denominator), this.denominator * other.denominator);
        ret.simplify();
        return ret;
    }

    /**
     * Subtracts this fraction to the given Fraction
     * @param other Fraction that is subtracts with "this" fraction
     * @return the result of subtraction
     */
    public Fraction subtract(Fraction other) {
        Fraction ret =  new Fraction((this.numerator * other.denominator) - (other.numerator * this.denominator), this.denominator * other.denominator);
        ret.simplify();
        return ret;
    }
}
