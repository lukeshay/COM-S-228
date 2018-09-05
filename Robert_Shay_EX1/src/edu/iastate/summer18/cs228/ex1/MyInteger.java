package edu.iastate.summer18.cs228.ex1;

/**
 * MyInteger object is used to determine different things about ints.
 * 
 * @author Robert Shay
 */
public class MyInteger {
	/**
	 * Stores the given value for the Integer.
	 */
	private int value;

	/**
	 * Constructor for the MyInteger object.
	 * 
	 * @param value
	 *            Given value for the MyInteger
	 */
	public MyInteger(int value) {
		this.value = value;
	}

	/**
	 * Returns the value that was given for the current MyInteger.
	 * 
	 * @return value of the MyInteger
	 */
	public int getValue() {
		return value;
	}

	/**
	 * Returns true if the current MyInteger is even.
	 * 
	 * @return
	 */
	public boolean isEven() {
		return value % 2 == 0;
	}

	/**
	 * Returns true if the current MyInteger is odd.
	 * 
	 * @return
	 */
	public boolean isOdd() {
		return value % 2 == 1;
	}

	/**
	 * Determines whether the current MyInteger is prime. If it is prime, true is
	 * returned.
	 * 
	 * @return
	 */
	public boolean isPrime() {
		if (value == 2) { return true; }

		for (int i = 2; i < value; i++) {
			if (value % i == 0) return false;
		}

		return true;
	}

	/**
	 * Returns true if given value is even.
	 * 
	 * @param i
	 *            Given int value from the user.
	 * @return
	 */
	public boolean isEven(int i) {
		return i % 2 == 0;
	}

	/**
	 * Returns true if the given value is odd.
	 * 
	 * @param i
	 *            Given int value from the user.
	 * @return
	 */
	public boolean isOdd(int i) {
		return i % 2 == 1;
	}

	/**
	 * Checks whether the given int is prime. Returns true if it is prime.
	 * 
	 * @param i
	 *            Given int value from the user.
	 * @return
	 */
	public boolean isPrime(int i) {
		if (i == 2) { return true; }

		for (int j = 2; j < value; j++) {
			if (i % j == 0) return false;
		}

		return true;
	}

	/**
	 * Checks if the given MyInteger is even. Returns true if it is even.
	 * 
	 * @param mi
	 *            Given MyInteger from the user.
	 * @return
	 */
	public boolean isEven(MyInteger mi) {
		return mi.isEven();
	}

	/**
	 * Checks if the given MyInteger is odd. Returns true if it is odd.
	 * 
	 * @param mi
	 *            Given MyInteger from the user.
	 * @return
	 */
	public boolean isOdd(MyInteger mi) {
		return mi.isOdd();
	}

	/**
	 * Checks if the given MyInteger is prime. Returns true if it is prime.
	 * 
	 * @param mi
	 *            Given MyInteger from the user.
	 * @return
	 */
	public boolean isPrime(MyInteger mi) {
		return mi.isPrime();
	}

	/**
	 * Checks to see if the given int value is equal to the current MyInteger.
	 * Returns true if they are equal.
	 * 
	 * @param i
	 *            Given int that is compared to the current MyInteger.
	 * @return
	 */
	public boolean equals(int i) {
		return i == value;
	}

	/**
	 * Checks to see if the given MyInteger is equal to the current one. Returns
	 * true if they are equal.
	 * 
	 * @param mi
	 *            Given MyInteger value that is being compared.
	 * @return
	 */
	public boolean equals(MyInteger mi) {
		return mi.value == value;
	}

	/**
	 * Turns char array into an int without using built in methods.
	 * 
	 * @param nums
	 *            Given char array that is being converted.
	 * @return
	 */
	public static int parseInt(char[] nums) {
		int num = 0;
		int length = nums.length - 1;
		for (int i = 0; i < nums.length; i++) {
			int temp = nums[i] - '0';
			num += temp * Math.pow(10, length);
			length--;
		}
		return num;
	}

	/**
	 * Turns a given string into an int without using built in methods.
	 * 
	 * @param s
	 *            Given String that is be converted.
	 * @return
	 */
	public static int parseInt(String s) {
		int num = 0;
		int length = s.length() - 1;
		for (int i = 0; i < s.length(); i++) {
			int temp = s.charAt(i) - '0';
			num += temp * Math.pow(10, length);
			length--;
		}
		return num;
	}
}
