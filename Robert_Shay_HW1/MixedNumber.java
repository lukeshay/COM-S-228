package edu.iastate.summer18.cs228.hw1;

/**
 * MixedNumber object that is used to hold a number in the form of a fraction
 * and a whole number part.
 * 
 * @author Robert Shay
 */
@SuppressWarnings({ "serial" })
public class MixedNumber extends Number implements MixedNumberInterface, Comparable<MixedNumberInterface> {

	/**
	 * Stores the whole number part of the MixedFraction.
	 */
	private int whole;

	/**
	 * Stores the Fraction part of the MixedNumber.
	 */
	private Fraction frac;

	/**
	 * Stores the MixedNumber in just a Fraction.
	 */
	private Fraction oneFrac;

	public char sign = '+';

	/**
	 * Constructs a new MixedNumber object with no inputed values.
	 */
	public MixedNumber() {
	}

	/**
	 * Constructs a new MixedNumber with the inputed values.
	 * 
	 * @param aFraction
	 *            FractionInterface the MixedNumber is set too.
	 */
	public MixedNumber(FractionInterface aFraction) {
		setMixedNumber(0, aFraction);
	}

	/**
	 * @see MixedNumber(FractionInterface aFraction)
	 * @param num
	 *            MixedNumber whole number is set to this.
	 * @param aFraction
	 *            MixedNumber Fraction is set to this.
	 */
	public MixedNumber(int num, FractionInterface aFraction) {
		setMixedNumber(num, aFraction);
	}

	/**
	 * @see MixedNumber(FractionInterface aFraction)
	 * @param whole
	 *            MixedNumber whole number is set to this.
	 * @param numer
	 *            MixedNumber Fraction numerator is set to this.
	 * @param denom
	 *            MixedNumber Fraction denominator is set to this.
	 */
	public MixedNumber(int whole, int numer, int denom) {
		setMixedNumber(whole, new Fraction(numer, denom));
	}

	/**
	 * @see MixedNumber(FractionInterface aFraction)
	 * @param mNumber
	 *            MixedNumber is set to this MixedNumberInterface.
	 */
	public MixedNumber(MixedNumberInterface mNumber) {
		setMixedNumber(mNumber.getInteger(), mNumber.getFraction());
	}

	public MixedNumber(int num) {
		setMixedNumber(num, new Fraction(0, 0));
	}

	@Override
	public int getInteger() {
		return whole;
	}

	@Override
	public Fraction getFraction() {
		return frac;
	}

	@Override
	public void setMixedNumber(int num, FractionInterface aFraction) {
		Fraction tempFrac = (Fraction) aFraction;
		int n = 1;
		if (num != 0 && aFraction.getNumerator() < 0) n = -1;
		tempFrac = aFraction.multiply(new Fraction(n, 1));

		if (tempFrac.doubleValue() == 0) {
			frac = tempFrac;
			whole = num;
			oneFrac = new Fraction(num, 1);
			if (num < 0) sign = '-';
		}
		else {
			n = 1;
			if (num < 0 || tempFrac.getNumerator() < 0) {
				n = -1;
				sign = '-';
			}

			whole = (Math.abs(num) + Math.abs(simplify(tempFrac))) * n;

			frac = new Fraction(Math.abs(tempFrac.getNumerator() % tempFrac.getDenominator()),
					tempFrac.getDenominator());

			if (num == 0) oneFrac = tempFrac;
			else oneFrac = onlyFraction(whole, frac);
		}
	}

	@Override
	public void setMixedNumber(FractionInterface aFraction) {
		setMixedNumber(0, aFraction);

	}

	@Override
	public void setMixedNumber(int num) {
		setMixedNumber(num, new Fraction(0, 0));
	}

	@Override
	public void setMixedNumber(int wholeNum, int numer, int denom) {
		setMixedNumber(wholeNum, new Fraction(numer, denom));

	}

	@Override
	public void setMixedNumber(MixedNumberInterface mNumber) {
		setMixedNumber(mNumber.getInteger(), mNumber.getFraction());
	}

	@Override
	public MixedNumber add(int num, FractionInterface aFraction) {
		return new MixedNumber(0, oneFrac.add(onlyFraction(num, aFraction)));
	}

	@Override
	public MixedNumber subtract(int num, FractionInterface aFraction) {
		return new MixedNumber(0, oneFrac.subtract(onlyFraction(num, aFraction)));
	}

	@Override
	public MixedNumber multiply(int num, FractionInterface aFraction) {
		if (whole == 0 && sign == '-')
			return new MixedNumber(0, onlyFraction(num, aFraction).multiply(frac).multiply(new Fraction(-1, 1)));
		else return new MixedNumber(0, oneFrac.multiply(onlyFraction(num, aFraction)));
	}

	@Override
	public MixedNumber divide(int num, FractionInterface aFraction) {
		if (whole == 0 && sign == '-')
			return new MixedNumber(0, frac.divide(onlyFraction(num, aFraction)).multiply(new Fraction(-1, 1)));
		else return new MixedNumber(0, oneFrac.divide(onlyFraction(num, aFraction)));
	}

	@Override
	public MixedNumber add(FractionInterface aFraction) {
		return add(0, aFraction);
	}

	@Override
	public MixedNumber subtract(FractionInterface aFraction) {
		return subtract(0, aFraction);
	}

	@Override
	public MixedNumber multiply(FractionInterface aFraction) {
		return multiply(0, aFraction);
	}

	@Override
	public MixedNumber divide(FractionInterface aFraction) {
		return divide(0, aFraction);
	}

	@Override
	public MixedNumber add(MixedNumberInterface mNumber) {
		return add(mNumber.getInteger(), mNumber.getFraction());
	}

	@Override
	public MixedNumber subtract(MixedNumberInterface mNumber) {
		return subtract(mNumber.getInteger(), mNumber.getFraction());
	}

	@Override
	public MixedNumber multiply(MixedNumberInterface mNumber) {
		return multiply(mNumber.getInteger(), mNumber.getFraction());
	}

	@Override
	public MixedNumber divide(MixedNumberInterface mNumber) {
		return divide(mNumber.getInteger(), mNumber.getFraction());
	}

	@Override
	public MixedNumber add(int wholeNum, int numer, int denom) {
		return add(wholeNum, new Fraction(numer, denom));
	}

	@Override
	public MixedNumber subtract(int wholeNum, int numer, int denom) {
		return subtract(wholeNum, new Fraction(numer, denom));
	}

	@Override
	public MixedNumber multiply(int wholeNum, int numer, int denom) {
		return multiply(wholeNum, new Fraction(numer, denom));
	}

	@Override
	public MixedNumber divide(int wholeNum, int numer, int denom) {
		return divide(wholeNum, new Fraction(numer, denom));
	}

	@Override
	public MixedNumber add(int num) {
		return add(num, new Fraction(0, 0));
	}

	@Override
	public MixedNumber subtract(int num) {
		return subtract(num, new Fraction(0, 0));
	}

	@Override
	public MixedNumber multiply(int num) {
		return subtract(num, new Fraction(0, 0));
	}

	@Override
	public MixedNumber divide(int num) {
		return subtract(num, new Fraction(0, 0));
	}

	/**
	 * Private method that is used to find the number of times that the denominator
	 * goes into the numerator. It then returns that number.
	 * 
	 * @param aFraction
	 *            Fraction that the user wants to simplify.
	 * @return
	 */
	private int simplify(FractionInterface aFraction) {
		return Math.abs(aFraction.getNumerator() / aFraction.getDenominator());
	}

	/**
	 * Turns a number and fraction into only a fraction. The main use of this method
	 * is to simplify the add, subtract, multiply, and divide methods of
	 * MixedNumber.
	 * 
	 * @param num
	 *            Number part that is added to the fraction.
	 * @param aFraction
	 *            Fraction that is being added too.
	 * @return
	 */
	private Fraction onlyFraction(int num, FractionInterface aFraction) {
		int n1 = 1;

		if (num > 0 && aFraction.getNumerator() < 0) n1 = -1;
		if (num < 0 && aFraction.getNumerator() > 0) n1 = -1;

		return aFraction.add(new Fraction(num * n1, 1)).multiply(new Fraction(n1, 1));
	}

	@Override
	public int intValue() {
		if (whole == 0 && sign == '-') return frac.intValue() * -1;
		return oneFrac.intValue();
	}

	@Override
	public long longValue() {
		if (whole == 0 && sign == '-') return frac.longValue() * -1;
		return oneFrac.longValue();
	}

	@Override
	public float floatValue() {
		if (whole == 0 && sign == '-') return frac.floatValue() * -1;
		return oneFrac.floatValue();
	}

	@Override
	public double doubleValue() {
		if (whole == 0 && sign == '-') return frac.doubleValue() * -1;
		return oneFrac.doubleValue();
	}

	@Override
	public String toString() {
		if (whole == 0) if (sign == '-') return sign + frac.toString();
		else return frac.toString();
		return whole + " " + frac.toString();
	}

	@Override
	public boolean equals(Object mNumber) {/*
											 * if (mNumber == null && !initialized) { if (!initialized) return true;
											 * else return false; }
											 */
		try {
			MixedNumber mNum = (MixedNumber) mNumber;
			if (doubleValue() == mNum.doubleValue()) return true;
			else return false;
		}
		catch (ClassCastException ex) {
			return false;
		}
	}

	@Override
	public int compareTo(MixedNumberInterface o) {
		MixedNumber mNum = (MixedNumber) o;
		if (doubleValue() > mNum.doubleValue()) return 1;
		else if (doubleValue() < mNum.doubleValue()) return -1;
		else return 0;
	}
}
