package edu.iastate.summer18.cs228.hw1;

/**
 * Interface for a MixedNumber object.
 * 
 * @author Robert Shay
 */
public interface MixedNumberInterface {

	/**
	 * Sets the value of the MixedNumber using the inputed int and Fraction.
	 * 
	 * @param num
	 *            Whole number part of the MixedNumber.
	 * @param aFraction
	 *            FractionInterface part of the MixedNumber.
	 */
	public void setMixedNumber(int num, FractionInterface aFraction);

	/**
	 * @see setMixedNumber(int num, FractionInterface aFraction) This only has a fraction
	 *      being inputed.
	 * @param aFraction
	 *            FractionInterface for the MixedNumber to be set too.
	 */
	public void setMixedNumber(FractionInterface aFraction);

	/**
	 * @see setMixedNumber(int num, FractionInterface aFraction) This 0nly has an int being
	 *      inputed.
	 * @param num
	 *            int for the MixedNumber to be set too.
	 */
	public void setMixedNumber(int num);

	/**
	 * @see setMixedNumber(int num, FractionInterface aFraction) This has a the whole number
	 *      part, numerator, and denominator inputed.
	 * @param wholeNum
	 *            The whole number part of MixedNumber is set to this value.
	 * @param numer
	 *            The numerator of the Fraction part of MixedNumber.
	 * @param denom
	 *            The denominator of the Fraction part of MixedNumber.
	 */
	public void setMixedNumber(int wholeNum, int numer, int denom);

	/**
	 * @see setMixedNumber(int num, FractionInterface aFraction) This only has a MixedNumber
	 *      or MixedNumberInterface inputed.
	 * @param mNumber
	 *            MixedNumberInterface the MixedNumber is being set to.
	 */
	public void setMixedNumber(MixedNumberInterface mNumber);

	/**
	 * Returns the int part of the MixedNumber.
	 * 
	 * @return
	 */
	public int getInteger();

	/**
	 * Returns the fraction part of the MixedNumber.
	 * 
	 * @return
	 */
	public Fraction getFraction();

	/**
	 * Adds the current MixedNumber to the inputed values.
	 * 
	 * @param wholeNum
	 *            Whole number that is being added.
	 * @param numer
	 *            Numerator of the Fraction that is being added.
	 * @param denom
	 *            Denominator of the Fraction that is being added.
	 * @return
	 */
	public MixedNumber add(int wholeNum, int numer, int denom);

	/**
	 * Subtracts the current MixedNumber to the inputed values.
	 * 
	 * @param wholeNum
	 *            Whole number that is being subtracted.
	 * @param numer
	 *            Numerator of the Fraction that is being subtracted.
	 * @param denom
	 *            Denominator of the Fraction that is being subtracted.
	 * @return
	 */
	public MixedNumber subtract(int wholeNum, int numer, int denom);

	/**
	 * Multiplies the current MixedNumber to the inputed values.
	 * 
	 * @param wholeNum
	 *            Whole number that is being multiplied.
	 * @param numer
	 *            Numerator of the Fraction that is being multiplied.
	 * @param denom
	 *            Denominator of the Fraction that is being multiplied.
	 * @return
	 */
	public MixedNumber multiply(int wholeNum, int numer, int denom);

	/**
	 * Divides the current MixedNumber to the inputed values.
	 * 
	 * @param wholeNum
	 *            Whole number that is being divided.
	 * @param numer
	 *            Numerator of the Fraction that is being divided.
	 * @param denom
	 *            Denominator of the Fraction that is being divided.
	 * @return
	 */
	public MixedNumber divide(int wholeNum, int numer, int denom);

	/**
	 * @see add(int wholeNum, int numer, int denom)
	 * @param num
	 *            Number that is being added.
	 * @param aFraction
	 *            FractionInterface that is being added.
	 * @return
	 */
	public MixedNumber add(int num, FractionInterface aFraction);

	/**
	 * @see subtract(int wholeNum, int numer, int denom)
	 * @param num
	 *            Number that is being subtracted.
	 * @param aFraction
	 *            FractionInterface that is being added.
	 * @return
	 */
	public MixedNumber subtract(int num, FractionInterface aFraction);

	/**
	 * @see multiply(int wholeNum, int numer, int denom)
	 * @param num
	 *            Number that is being multiplied.
	 * @param aFraction
	 *            FractionInterface that is being multiplied.
	 * @return
	 */
	public MixedNumber multiply(int num, FractionInterface aFraction);

	/**
	 * @see divide(int wholeNum, int numer, int denom)
	 * @param num
	 *            Number that is being divided.
	 * @param aFraction
	 *            FractionInterface that is being divided.
	 * @return
	 */
	public MixedNumber divide(int num, FractionInterface aFraction);

	/**
	 * @see add(int wholeNum, int numer, int denom)
	 * @param aFraction
	 *            FractionInterface that is being added.
	 * @return
	 */
	public MixedNumber add(FractionInterface aFraction);

	/**
	 * @see subtract(int wholeNum, int numer, int denom)
	 * @param aFraction
	 *            FractionInterface that is being subtracted.
	 * @return
	 */
	public MixedNumber subtract(FractionInterface aFraction);

	/**
	 * @see multiply(int wholeNum, int numer, int denom)
	 * @param aFraction
	 *            FractionInterface that is being multiplied.
	 * @return
	 */
	public MixedNumber multiply(FractionInterface aFraction);

	/**
	 * @see divide(int wholeNum, int numer, int denom)
	 * @param aFraction
	 *            FractionInterface that is being divided.
	 * @return
	 */
	public MixedNumber divide(FractionInterface aFraction);

	/**
	 * @see add(int wholeNum, int numer, int denom)
	 * @param mNumber
	 *            MixedNumberInterface that is being added.
	 * @return
	 */
	public MixedNumber add(MixedNumberInterface mNumber);

	/**
	 * @see subtract(int wholeNum, int numer, int denom)
	 * @param mNumber
	 *            MixedNumberInterface that is being subtracted.
	 * @return
	 */
	public MixedNumber subtract(MixedNumberInterface mNumber);

	/**
	 * @see multiply(int wholeNum, int numer, int denom)
	 * @param mNumber
	 *            MixedNumberInterface that is being multiplied.
	 * @return
	 */
	public MixedNumber multiply(MixedNumberInterface mNumber);

	/**
	 * @see divide(int wholeNum, int numer, int denom)
	 * @param mNumber
	 *            MixedNumberInterface that is being divided.
	 * @return
	 */
	public MixedNumber divide(MixedNumberInterface mNumber);

	/**
	 * @see add(int wholeNum, int numer, int denom)
	 * @param num
	 *            int that is being added.
	 * @return
	 */
	public MixedNumber add(int num);

	/**
	 * @see subtract(int wholeNum, int numer, int denom)
	 * @param num
	 *            int that is being subtracted.
	 * @return
	 */
	public MixedNumber subtract(int num);

	/**
	 * @see multiply(int wholeNum, int numer, int denom)
	 * @param num
	 *            int that is being multiplied.
	 * @return
	 */
	public MixedNumber multiply(int num);

	/**
	 * @see divide(int wholeNum, int numer, int denom)
	 * @param num
	 *            int that is being divided.
	 * @return
	 */
	public MixedNumber divide(int num);
}
