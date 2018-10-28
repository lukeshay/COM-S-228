package edu.iastate.summer18.cs228.hw1;

public class Demo3 {
	public static void main(String[] args) {
		System.out.println("More Fraction tests...");

		Fraction[] f = new Fraction[] { new Fraction(-1, -4), new Fraction(-1, 4), new Fraction(1, -4),
				new Fraction(1, 4) };

		for (int i = 0; i < f.length; i++) {
			System.out.print(f[i].getNumerator() + ",");
			System.out.print(f[i].getDenominator() + ",");
			System.out.print(f[i].getSign() + ",");
			System.out.print(f[i].toString() + ";\n");
		}

		System.out.println("More MixedNumber tests...");

		MixedNumber[] mn = new MixedNumber[] { new MixedNumber(-1, new Fraction(-1, -4)),
				new MixedNumber(-1, new Fraction(-1, 4)), new MixedNumber(-1, new Fraction(1, -4)),
				new MixedNumber(-1, new Fraction(1, 4)), new MixedNumber(1, new Fraction(-1, -4)),
				new MixedNumber(1, new Fraction(-1, -4)), new MixedNumber(1, new Fraction(1, -4)),
				new MixedNumber(1, new Fraction(1, 4)), };

		for (int i = 0; i < mn.length; i++) {
			System.out.print(mn[i].getInteger() + " ,");
			System.out.print(mn[i].getFraction() + " ,");
			System.out.print(mn[i].getFraction().getSign() + " ,");
			System.out.print(mn[i].toString() + ", ");
			System.out.print(mn[i].sign + ";\n"); // for this example data field sign is set as public
		}
	}
}
