package edu.iastate.summer18.cs228.ex3;

/**
 * Contains three methods that were specified in exercise 3.
 * @author Robert Shay
 */
public class Ex3_2 {
	/**
	 * Checks whether the inputed string is a palindrome. Uses a stack to check.
	 * 
	 * @param str
	 * @return True if palindrome, false if not.
	 */
	public static boolean palindrome(String str) {
		StackInterface<Character> stack = new LinkedStack<Character>();
		String s = str.toLowerCase(); // Sets all chars in the string to lowercase.

		// Pushes each char into a stack
		for (int i = 0; i < s.length(); i++) {
			stack.push(s.charAt(i));
		}

		String reverse = "";

		// Pops each char back out. The resulting string is the reverse of the inputed.
		for (int i = 0; i < s.length(); i++) {
			reverse += stack.pop();
		}
		return reverse.equals(s);
	}

	/**
	 * Checks if the inputed string has an equal number of 1s and 0s.
	 * 
	 * @param str
	 * @return True if equal, false if not.
	 */
	public static boolean equals01(String str) {
		StackInterface<Character> stack = new LinkedStack<>();

		for (int i = 0; i < str.length(); i++)
			if (stack.isEmpty() || stack.peek() == str.charAt(i)) stack.push(str.charAt(i)); // Pushes char if the stack
																								// is empty or if the
																								// previous char is the
																								// same.
			else stack.pop(); // If the previous char was not the same, it is popped.

		return stack.isEmpty();
	}

	/**
	 * Displays the stack in the order inputed. The stack is then put back to it's
	 * original order.
	 * 
	 * @param ls
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void display(StackInterface ls) {
		StackInterface<Object> temp = new LinkedStack<>();
		while (!ls.isEmpty()) // Puts the inputed stack into a temp stack in the reverse direction.
			temp.push(ls.pop());

		while (!temp.isEmpty()) {
			System.out.print(temp.peek() + " "); // Prints the top object in the temp stack.
			ls.push(temp.pop()); // Objects are returned back to the inputed stack.
		}
	}
}
