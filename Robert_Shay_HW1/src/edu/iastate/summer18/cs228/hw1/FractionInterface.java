package edu.iastate.summer18.cs228.hw1;

/**
 * Interface that contains methods for use with fractions.
 * 
 * @author Robert Shay
 */
public interface FractionInterface {
	/**
	 * Method that the user can call to set the values of the Fraction. * @param
	 * numerator Numerator of the Fraction.
	 * 
	 * @param denominator
	 *            Denominator of the Fraction.
	 */
	public void setFraction(int numerator, int denominator);

	/**
	 * @see setFraction(int numerator, int denominator)
	 * @param aFraction
	 *            FractionInterface being set to the Fraction.
	 */
	public void setFraction(FractionInterface aFraction);

	/**
	 * Returns the int value of the numerator of the current Fraction.
	 * 
	 * @return
	 */
	public int getNumerator();

	/**
	 * Returns the int value of the denominator of the current Fraction.
	 * 
	 * @return
	 */
	public int getDenominator();

	/**
	 * Used to add a given Fraction to the current Fraction. It then returns that
	 * result as a Fraction.
	 * 
	 * @param aFraction
	 *            Given fraction that is being added to the current one.
	 * @return
	 */
	public Fraction add(FractionInterface aFraction);

	/**
	 * Used to subtract a given Fraction from the current Fraction. It then returns
	 * that result as a Fraction.
	 * 
	 * @param aFraction
	 *            Given fraction that is being subtracted from the current one.
	 * @return
	 */
	public Fraction subtract(FractionInterface aFraction);

	/**
	 * Used to multiply a given Fraction to with current Fraction. It then returns
	 * that result as a Fraction.
	 * 
	 * @param aFraction
	 *            Given fraction that is being multiplied with the current one.
	 * @return
	 */
	public Fraction multiply(FractionInterface aFraction);

	/**
	 * Used to divide a given Fraction from the current fraction. It then returns
	 * that result as a Fraction.
	 * 
	 * @param aFraction
	 *            Given fraction that is dividing the current one.
	 * @return
	 */
	public Fraction divide(FractionInterface aFraction);

	/**
	 * @see add(FractionInterface aFraction)
	 * @param num
	 *            Given int that is being added.
	 * @return
	 */
	public Fraction add(int num);

	/**
	 * @see subtract(FractionInterface aFraction)
	 * @param num
	 *            Given int that is being subtracted.
	 * @return
	 */
	public Fraction subtract(int num);

	/**
	 * @see multiply(FractionInterface aFraction)
	 * @param num
	 *            Given int that is being multiplied.
	 * @return
	 */
	public Fraction multiply(int num);

	/**
	 * @see divide(FractionInterface aFraction)
	 * @param num
	 *            Given int that is being divided.
	 * @return
	 */
	public Fraction divide(int num);

	/**
	 * @see add(FractionInterface aFraction)
	 * @param numer
	 *            Given int that is being added as the numerator.
	 * @param denom
	 *            Given int that is being added as the denominator.
	 * @return
	 */
	public Fraction add(int numer, int denom);

	/**
	 * @see subtract(FractionInterface aFraction)
	 * @param numer
	 *            Given int that is being subtracted as the numerator.
	 * @param denom
	 *            Given int that is being subtracted as the denominator.
	 * @return
	 */
	public Fraction subtract(int numer, int denom);

	/**
	 * @see multiply(FractionInterface aFraction)
	 * @param numer
	 *            Given int that is being multiplied as the numerator.
	 * @param denom
	 *            Given int that is being multiplied as the denominator.
	 * @return
	 */
	public Fraction multiply(int numer, int denom);

	/**
	 * @see divide(FractionInterface aFraction)
	 * @param num
	 *            Given int that is being divided as the numerator.
	 * @param denom
	 *            Given int that is being divided as the denominator.
	 * @return
	 */
	public Fraction divide(int numer, int denom);

	/**
	 * Used to get the reciprocal of the current Fraction.
	 * 
	 * @return
	 */
	public Fraction getReciprocal();
}