package edu.iastate.summer18.cs228.hw1;

/**
 * Class that implements the FractionInterface along with Comparable. It also
 * extends the Number class. This class is used to construct an object called a
 * Fraction.
 * 
 * @author Robert Shay
 */
@SuppressWarnings({ "serial" })
public class Fraction extends Number implements FractionInterface, Comparable<FractionInterface> {
	/**
	 * Private int variable to hold the numerator of the Fraction.
	 */
	private int numer;

	/**
	 * Private int variable to hold the denominator of the Fraction.
	 */
	private int denom;

	private boolean initialized;

	/**
	 * Constructor for a Fraction.
	 */
	public Fraction() {
		initialized = false;
	}

	/**
	 * Constructor for a Fraction that is set to the inputed values.
	 * 
	 * @param numerator
	 *            Numerator of the Fraction.
	 * @param denominator
	 *            Denominator of the Fraction.
	 */
	public Fraction(int numerator, int denominator) {
		setFraction(numerator, denominator);
	}

	/**
	 * @see Fraction(int numerator, int denominator).
	 * @param aFraction
	 *            FractionInterface that the Fraction is set too.
	 */
	public Fraction(FractionInterface aFraction) {
		setFraction(aFraction.getNumerator(), aFraction.getDenominator());
	}

	@Override
	public void setFraction(int numerator, int denominator) {
		if(denominator == 0) {}
		else if (numerator == 0) {
			denom = 0;
			numer = 0;
		}
		else {
			int sign = denominator / Math.abs(denominator);
			int temp = gcd(numerator, denominator);
			numer = sign * numerator / temp;
			denom = sign * denominator / temp;
		}
		initialized = true;
	}

	@Override
	public void setFraction(FractionInterface aFraction) {
		setFraction(aFraction.getNumerator(), aFraction.getDenominator());
	}

	@Override
	public int getNumerator() {
		assert initialized;
		return numer;
	}

	@Override
	public int getDenominator() {
		assert initialized;
		return denom;
	}

	@Override
	public Fraction add(FractionInterface aFraction) {
		assert initialized;
		if (aFraction.getDenominator() == 0) return new Fraction(numer, denom);
		else if (numer == 0) return (Fraction) aFraction;
		else return new Fraction((numer * aFraction.getDenominator()) + (aFraction.getNumerator() * denom),
				denom * aFraction.getDenominator());
	}

	@Override
	public Fraction subtract(FractionInterface aFraction) {
		assert initialized;
		if (aFraction.getDenominator() == 0) return new Fraction(numer, denom);
		else if (numer == 0) return (Fraction) aFraction.multiply(new Fraction(-1, 1));
		else return new Fraction((numer * aFraction.getDenominator()) - (aFraction.getNumerator() * denom),
				denom * aFraction.getDenominator());
	}

	@Override
	public Fraction multiply(FractionInterface aFraction) {
		assert initialized;
		return new Fraction(numer * aFraction.getNumerator(), denom * aFraction.getDenominator());
	}

	@Override
	public Fraction divide(FractionInterface aFraction) {
		assert initialized;
		return new Fraction(numer * aFraction.getDenominator(), denom * aFraction.getNumerator());
	}

	@Override
	public Fraction add(int num) {
		return add(new Fraction(num, 1));
	}

	@Override
	public Fraction subtract(int num) {
		return subtract(new Fraction(num, 1));
	}

	@Override
	public Fraction multiply(int num) {
		return multiply(new Fraction(num, 1));
	}

	@Override
	public Fraction divide(int num) {
		return divide(new Fraction(num, 1));
	}

	@Override
	public Fraction add(int numer, int denom) {
		return add(new Fraction(numer, denom));
	}

	@Override
	public Fraction subtract(int numer, int denom) {
		return subtract(new Fraction(numer, denom));
	}

	@Override
	public Fraction multiply(int numer, int denom) {
		return multiply(new Fraction(numer, denom));
	}

	@Override
	public Fraction divide(int numer, int denom) {
		return divide(new Fraction(numer, denom));
	}

	@Override
	public Fraction getReciprocal() {
		return new Fraction(denom, numer);
	}

	/**
	 * Sets the sign to the inputed char.
	 * 
	 * @param c
	 *            The desired sign.
	 * @return True if initialized, false if not.
	 */
	public boolean setSign(char c) {
		assert initialized;
		if (!initialized) return false;
		if (c == '-' && numer > 0) {
			numer *= -1;
		}
		if (c == '+' && numer < 0) {
			numer *= -1;
		}
		return true;
	}

	/**
	 * Returns the current sign of the fraction.
	 * 
	 * @return The sign of the fraction as a char.
	 */
	public char getSign() {
		assert initialized;
		if (doubleValue() < 0) return '-';
		else return '+';
	}

	/**
	 * Finds the greatest common divisor for the two numbers inputed.
	 * 
	 * @return
	 */
	private int gcd(int one, int two) {
		if (two == 0) {
			if (one == 0) return 1;
			return Math.abs(one);
		}
		else return gcd(two, one % two);
	}

	@Override
	public String toString() {
		assert initialized;
		return numer + "/" + denom;
	}

	@Override
	public boolean equals(Object fraction) {
		if (fraction == null) {
			if (!initialized) return true;
			else return false;
		}
		// Try statement to catch the ClassCastException if the user inputs a object
		// other than Fraction or FractionInterface.
		try {
			Fraction aFraction = (Fraction) fraction;
			if (aFraction.getNumerator() == getNumerator() && aFraction.getDenominator() == getDenominator())
				return true;
			else return false;
		}
		catch (ClassCastException ex) {
			return false;
		}
	}

	@Override
	public int intValue() {
		assert initialized;
		if (denom == 0) return 0;
		return numer / denom;
	}

	@Override
	public long longValue() {
		assert initialized;
		if (denom == 0) return (long) 0;
		return (long) numer / denom;
	}

	@Override
	public float floatValue() {
		assert initialized;
		if (denom == 0) return (float) 0;
		return (float) numer / denom;
	}

	@Override
	public double doubleValue() {
		assert initialized;
		if (denom == 0) return (double) 0;
		return (double) numer / denom;
	}

	@Override
	public int compareTo(FractionInterface o) {
		assert initialized;
		Fraction aFraction = (Fraction) o;
		if (doubleValue() > aFraction.doubleValue()) return 1;
		else if (doubleValue() < aFraction.doubleValue()) return -1;
		else return 0;
	}
}
