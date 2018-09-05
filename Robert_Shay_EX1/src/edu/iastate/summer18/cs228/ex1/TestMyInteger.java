package edu.iastate.summer18.cs228.ex1;
/**
 * 
 * @author Robert Shay
 *
 */
public class TestMyInteger {
	public static void main(String[] args) {
		MyInteger integer = new MyInteger(5);

		System.out.println(integer.getValue());
		System.out.println(integer.isEven());
		System.out.println(integer.isOdd());
		System.out.println(integer.isPrime());

		System.out.println(integer.isEven(4));
		System.out.println(integer.isOdd(4));
		System.out.println(integer.isPrime(4));

		System.out.println(integer.isEven(integer));
		System.out.println(integer.isEven(integer));
		System.out.println(integer.isPrime(integer));

		System.out.println(integer.equals(6));
		System.out.println(integer.equals(integer));

		char[] nums = { '2', '3', '6', '3' };
		System.out.println(MyInteger.parseInt(nums));

		System.out.println(MyInteger.parseInt("1498"));
	}
}