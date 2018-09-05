package edu.iastate.summer18.cs228.hw3;

import java.util.Scanner;

/**
 * This class evaluates a simple arithmetic Lisp expression.
 * 
 * @author Robert Shay
 */
public class LispExpressionEvaluator {
	/**
	 * Evaluates a Lisp expression. The algorithm: Scan the tokens in the string. If
	 * you see "(", push the next operator onto the stack. If you see an operand,
	 * push it onto the stack. If you see ")", Pop operands and push them onto a
	 * second stack until you find an operator. Apply the operator to the operands
	 * on the second stack. Push the result on the stack. If you run out of tokens,
	 * the value on the top of the stack is the value of the expression.
	 * 
	 * @param lispExp
	 *            A string that is a valid lisp expression.
	 * @return A double that is the value of the expression.
	 */
	public static Double evaluate(String lispExp) {
		StackInterface<LispToken> expressionStack = new LinkedStack<>();
		StackInterface<LispToken> secondStack = new LinkedStack<>();

		LispToken eval;

		String lisp = "";
		for (int i = 0; i < lispExp.length() - 1; i++) {
			char ch = lispExp.charAt(i);
			if (lispExp.charAt(i + 1) == ')' || ch == '(') lisp += ch + " ";
			else lisp += ch;
		}
		lisp += lispExp.charAt(lispExp.length() - 1);

		Scanner lispExpScanner = new Scanner(lisp);

		lispExpScanner = lispExpScanner.useDelimiter(" ");

		while (lispExpScanner.hasNext()) {
			String next = lispExpScanner.next();
			if (next.equals("(")) {
				expressionStack.push(new LispToken(lispExpScanner.next().charAt(0)));
				while (lispExpScanner.hasNextInt()) {
					expressionStack.push(new LispToken((double) lispExpScanner.nextInt()));
				}
			}
			else if (next.equals(")")) {
				int c = 0;
				while (!expressionStack.peek().isOperator()) {
					secondStack.push(expressionStack.pop());
					c++;
				}

				eval = expressionStack.pop();

				if (secondStack.isEmpty()) {
					expressionStack.push(new LispToken(eval.applyOperator(null, null)));
				}
				else {
					double d = secondStack.pop().getValue();

					if (c == 1) {
						d = eval.applyOperator(d, null);
					}
					else {
						while (!secondStack.isEmpty()) {
							d = eval.applyOperator(d, secondStack.pop().getValue());
						}
					}
					expressionStack.push(new LispToken(d));
				}
			}
		}
		lispExpScanner.close();
		return expressionStack.pop().getValue();
	} // end evaluate

	public static void main(String args[]) {
		Double result;
		String test1 = "(+ (- 6) (* 2 3 4) (/ (+ 3) (*) (- 2 3 1)))";
		result = evaluate(test1);
		System.out.println("Expression " + test1 + " evaluates to " + result);

		String test2 = "(+ (- 632) (* 21 3 4) (/ (+ 32) (*) (- 21 3 1)))";
		result = evaluate(test2);
		System.out.println("Expression " + test2 + " evaluates to " + result);

		String test3 = "(+ (/ 2) (* 2) (/ (+ 1) (+) (- 2 1 )))";
		result = evaluate(test3);
		System.out.println("Expression " + test3 + " evaluates to " + result);
	} // end main
} // end LispExpressionEvaluator

/*
 * Expression (+ (- 6) (* 2 3 4) (/ (+ 3) (*) (- 2 3 1))) evaluates to 16.5
 * Expression (+ (- 632) (* 21 3 4) (/ (+ 32) (*) (- 21 3 1))) evaluates to
 * -378.11764705882354 Expression (+ (/ 2) (* 2) (/ (+ 1) (+) (- 2 1 )))
 * evaluates to Infinity
 */
