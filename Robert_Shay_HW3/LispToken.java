package edu.iastate.summer18.cs228.hw3;

/**
 * This class represents either an operand or an operator for an arithmetic
 * expression in Lisp.
 * 
 * @author Robert Shay
 */
public class LispToken {
	private Character operator;
	private Double operand;
	private boolean isOperator;

	/** Constructors for objects of class LispToken. */
	public LispToken(Character anOperator) {
		operator = anOperator;
		isOperator = true;
		operand = 0.0;
	} // end constructor

	public LispToken(Double value) {
		operand = value;
		isOperator = false;
		operator = ' ';
	} // end constructor

	/**
	 * Applies this operator to two given operand values.
	 * 
	 * @param value1
	 *            The value of the first operand.
	 * @param value2
	 *            The value of the second operand.
	 * @return The real result of the operation.
	 */
	public Double applyOperator(Double value1, Double value2) {
		double returned = 0.0;

		if (value1 == null && value2 == null) {
			if (operator.equals('*')) returned = 1.0;
		}
		else if (value2 == null) {
			if (operator == '+') returned = value1;
			else if (operator == '-') returned = value1 * -1;
			else if (operator == '*') returned = value1;
			else returned = 1 / value1;
		}
		else {
			if (operator == '+') returned = value1 + value2;
			else if (operator == '-') returned = value1 - value2;
			else if (operator == '*') returned = value1 * value2;
			else returned = value1 / value2;
		}
		return returned;
	} // end applyOperator

	/**
	 * Gets the identity value of this operator. For example, x + 0 = x, so 0 is the
	 * identity for + and will be the value associated with the expression (+).
	 * 
	 * @return The identity value of the operator.
	 */
	public Double getIdentity() {
		if (operand == '/' || operand == '*') return 0.0;
		else return 1.0;
	} // endGetIdentity

	/**
	 * Detects whether this operator returns a value when it has no operands.
	 * 
	 * @return True if the operator returns a value when it has no operands, or
	 *         false if not.
	 */
	public boolean takesZeroOperands() {
		if (operator.equals('*') || operator.equals('+')) return true;
		else return false;
	} // end takesZeroOperands

	/**
	 * Gets the value of this operand.
	 * 
	 * @return The real value of the operand.
	 */
	public Double getValue() {
		return operand;
	} // end getValue

	/**
	 * Returns true if the object is an operator.
	 * 
	 * @return True is this object is an operator.
	 */
	public boolean isOperator() {
		return isOperator;
	} // end isOperator

	// Returns String version of either operator or operand
	public String toString() {
		if (isOperator) return "" + operator;
		else return "" + operand;

	} // end toString
} // end LispToken
