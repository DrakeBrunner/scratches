import java.util.Arrays;


public class LabFractionTester {

	public static void main(String[] args) {

		// PART 1: Testing equality of fractions.
		// When are two fractions equal?




		Fraction f1 = new Fraction("3/5");
		Fraction f2 = new Fraction("2/1000");
		System.out.println(f1.equals(f2));






		// PART 2: Testing sorting.  Arrays.sort() only
		// works if Fractions are designed to be compared.
		// Create an array of Fractions.
		// Print it.  Sort it.  Print it again.
		Fraction[] fractions = {
				new Fraction("3/5"),
				new Fraction("-3/-5"),
				new Fraction("3/4"),
				new Fraction("7/1"),
				new Fraction("0/5"),
				new Fraction("10/9"),
				new Fraction("-10/1"),
				new Fraction("10/-1"),
				new Fraction("-1/8"),
				new Fraction("0/17"),
				new Fraction("1000/2"),
				new Fraction("2/1000")
		};
		
		// One way to sort this
		/*
		System.out.println(Arrays.toString(fractions));
		Arrays.sort(fractions);
		System.out.println(Arrays.toString(fractions));
		*/


		// Another way to sort this
		/*
		double[] doubleFractions = new double[fractions.length];
		
		for (int i = 0; i < doubleFractions.length; i++)
			doubleFractions[i] = fractions[i].toDouble();
			*/

	}
}
