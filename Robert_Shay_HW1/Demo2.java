package edu.iastate.summer18.cs228.hw1;
public class Demo2 {
		public static void main(String[] args)
		{
			FractionInterface firstOperand = null;
			FractionInterface secondOperand = null;
			FractionInterface result = null;

			Fraction nineSixteenths = new Fraction(9, 16);	// 9/16
			Fraction oneFourth = new Fraction(-1, 4);		// 1/4
			

			System.out.println(oneFourth.getReciprocal());
			
			System.out.println(oneFourth.doubleValue());
			
			// 7/8 + 9/16
			firstOperand = new Fraction(7, 8);
			result = firstOperand.add(nineSixteenths);
			System.out.println("The sum of " + firstOperand + " and " +
					nineSixteenths + " is \t\t" + result);

			// 9/16 - 7/8
			firstOperand = nineSixteenths;
			secondOperand = new Fraction(7, 8);
			result = firstOperand.subtract(secondOperand);
			System.out.println("The difference of " + firstOperand	+
					" and " +	secondOperand + " is \t" + result);

			// 15/-2 * 1/4
			firstOperand.setFraction(15, -2);
			result = firstOperand.multiply(oneFourth);
			System.out.println("The product of " + firstOperand	+
					" and " +	oneFourth + " is \t" + result);

			// (-21/2) / (3/7)
			firstOperand.setFraction(-21, 2);
			secondOperand.setFraction(3, 7);
			result = firstOperand.divide(secondOperand);
			System.out.println("The quotient of " + firstOperand	+
					" and " +	secondOperand + " is \t" + result);

			// -21/2 + 7/8
			firstOperand.setFraction(-21, 2);
			secondOperand.setFraction(7, 8);
			result = firstOperand.add(secondOperand);
			System.out.println("The sum of " + firstOperand	+
					" and " +	secondOperand + " is \t\t" + result);

			System.out.println();

			// Equality check
			if (firstOperand.equals(firstOperand))
				System.out.println("Identity of fractions OK");
			else
				System.out.println("ERROR in identity of fractions");

			secondOperand.setFraction(-42, 4);
			if (firstOperand.equals(secondOperand))
				System.out.println("Equality of fractions OK");
			else
				System.out.println("ERROR in equality of fractions");

			// Comparison check
			Fraction first  = (Fraction)firstOperand;
			Fraction second = (Fraction)secondOperand;

			if (first.compareTo(second) == 0)
				System.out.println("Fractions == operator OK");
			else
				System.out.println("ERROR in fractions == operator");

			second.setFraction(7, 8);
			if (first.compareTo(second) < 0)
				System.out.println("Fractions < operator OK");
			else
				System.out.println("ERROR in fractions < operator");

			if (second.compareTo(first) > 0)
				System.out.println("Fractions > operator OK");
			else
				System.out.println("ERROR in fractions > operator");

			System.out.println();
		}	// end main
	} // end Demo2

	/*
	-4/1
-0.25
The sum of 7/8 and 9/16 is 		23/16
The difference of 9/16 and 7/8 is 	-5/16
The product of -15/2 and -1/4 is 	15/8
The quotient of -21/2 and 3/7 is 	-49/2
The sum of -21/2 and 7/8 is 		-77/8

Identity of fractions OK
Equality of fractions OK
Fractions == operator OK
Fractions < operator OK
Fractions > operator OK


	 */


