package edu.iastate.summer18.cs228.ex4;

import java.util.Scanner;

/**
 * A collection of different methods to perform different tasks.
 * 
 * @author Robert Shay
 */
public class Ex4_1 {
	public static void main(String... args) {
		displayDigits(12345); // displays 1.2.3.4.5.
		displayRowOfCharacters('*', 5);
	}

	/**
	 * A method displayRowOfCharacters that displays any given character the
	 * specified number of times on one line.
	 * 
	 * @param c
	 *            character to be displayed.
	 * @param n
	 *            number of times the character is to be displayed.
	 */
	public static void displayRowOfCharacters(char c, int n) {
		if (n == 1) System.out.print(c);
		else {
			displayRowOfCharacters(c, n - 1);
			System.out.print(c);
		}
	}

	/**
	 * Asks the user for integer input that is between 1 and 10, inclusive. If the
	 * input is out of range, the method should recursively ask the user to enter a
	 * new input value.
	 * 
	 * @param keyboard
	 * @return The value entered.
	 */
	@SuppressWarnings("resource")
	public static int getInput(Scanner keyboard) {
		keyboard = new Scanner(System.in);

		System.out.print("Enter an interger from 1 to 10: ");
		int num = keyboard.nextInt();

		if (num >= 1 || num >= 10) return num;
		else return getInput(keyboard);
	}

	/**
	 * A palindrome is a string that reads the same forward and backward. For
	 * example deed and level are palindromes. You did implement a similar method in
	 * exercise 3, however in this one you can assume that input string will only
	 * consist of lowercase letters, and that parameter won't be null. Implement a
	 * recursive method isPalindrome.
	 * 
	 * @param str
	 * @return True if palindrome, false if not.
	 */
	public static boolean isPalindrome(String str) {
		if (str.length() <= 1) return true;
		else if (str.length() == 2) return str.charAt(0) == str.charAt(1);
		else if (str.charAt(0) == str.charAt(str.length() - 1)) return isPalindrome(str.substring(1, str.length() - 2));
		else return false;
	}

	/**
	 * If n is positive integer in Java, n%10 is its rightmost digit and n/10 is the
	 * integer obtained by dropping the rightmost digit from n. Using these facts,
	 * write a recursive method, named displayDigits, that displays an integer n in
	 * decimal.
	 * 
	 * @param n
	 */
	public static void displayDigits(int n) {
		if (n > 10) displayDigits(n / 10);
		System.out.print(n % 10 + ".");
	}
}
