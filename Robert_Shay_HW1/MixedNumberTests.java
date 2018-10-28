package edu.iastate.summer18.cs228.hw1;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Test cases for the MixedNumber class.
 * 
 * @author Robert Shay
 */
public class MixedNumberTests {
	MixedNumber myNum = new MixedNumber();
	MixedNumber num = new MixedNumber(-1, 1, 2);

	public void setup1() {
		myNum.setMixedNumber(new Fraction(3, 2));
	}

	public void setup2() {
		myNum.setMixedNumber(num);
	}

	public void setup3() {
		myNum.setMixedNumber(7, new Fraction(3, -2));
	}

	public void setup4() {
		myNum.setMixedNumber(4, new Fraction(0, 2));
	}

	public void setup5() {
		myNum.setMixedNumber(0, -4, 7);
	}

	@Test
	public void setupTest1() {
		setup1();
		assertEquals (1, (int) myNum.getInteger());
		assert (myNum.getFraction().getNumerator() == 1);
		assert (myNum.getFraction().getDenominator() == 2);

		assertEquals("1 1/2", myNum.toString());
	}

	@Test
	public void setupTest2() {
		setup2();
		assert (myNum.getInteger() == -1);
		assert (myNum.getFraction().getNumerator() == 1);
		assert (myNum.getFraction().getDenominator() == 2);

		assertEquals("-1 1/2", myNum.toString());
	}

	@Test
	public void setupTest3() {
		setup3();
		assert (myNum.getInteger() == 8);
		assert (myNum.getFraction().getNumerator() == 1);
		assert (myNum.getFraction().getDenominator() == 2);

		assertEquals("8 1/2", myNum.toString());
	}

	@Test
	public void setupTest4() {
		setup4();
		assert (myNum.getInteger() == 4);
		assert (myNum.getFraction().getNumerator() == 0);
		assert (myNum.getFraction().getDenominator() == 0);

		assertEquals("4 0/0", myNum.toString());
	}

	@Test
	public void setupTest5() {
		setup5();
		assert (myNum.getInteger() == 0);
		assert (myNum.getFraction().getNumerator() == 4);
		assert (myNum.getFraction().getDenominator() == 7);

		assertEquals("-4/7", myNum.toString());
	}

	@Test
	public void addTest1() {
		setup1();
		assertEquals(3, myNum.add(new MixedNumber(1, 1, 2)).getInteger());
		assertEquals(0, myNum.add(1, new Fraction(1, 2)).getFraction().getNumerator());
		assertEquals(0, myNum.add(1, new Fraction(1, 2)).getFraction().getDenominator());

		assertEquals(0, myNum.add(-1, new Fraction(1, 2)).getInteger());
		assertEquals(0, myNum.add(-1, new Fraction(1, 2)).getFraction().getNumerator());
		assertEquals(0, myNum.add(-1, new Fraction(1, 2)).getFraction().getDenominator());
	}

	@Test
	public void addTest2() {
		setup2();
		assertEquals(0, myNum.add(1, new Fraction(1, 2)).getInteger());
		assertEquals(0, myNum.add(1, new Fraction(1, 2)).getFraction().getNumerator());
		assertEquals(0, myNum.add(1, new Fraction(1, 2)).getFraction().getDenominator());

		assertEquals(-3, myNum.add(-1, new Fraction(1, 2)).getInteger());
		assertEquals(0, myNum.add(-1, new Fraction(1, 2)).getFraction().getNumerator());
		assertEquals(0, myNum.add(-1, new Fraction(1, 2)).getFraction().getDenominator());
	}

	@Test
	public void addTest3() {
		setup4();
		assertEquals(8, myNum.add(4).getInteger());
		assertEquals(0, myNum.add(4).getFraction().getNumerator());
		assertEquals(0, myNum.add(4).getFraction().getDenominator());
		
		setup5();
		assertEquals(0, myNum.add(new Fraction(4, 7)).getInteger());
		assertEquals(0, myNum.add(new Fraction(4, 7)).getFraction().getNumerator());
		assertEquals(0, myNum.add(new Fraction(4, 7)).getFraction().getDenominator());

		assertEquals(-1, myNum.add(new Fraction(-3, 7)).getInteger());
		assertEquals(0, myNum.add(new Fraction(-3, 7)).getFraction().getNumerator());
		assertEquals(0, myNum.add(new Fraction(-3, 7)).getFraction().getDenominator());
	}

	@Test
	public void subtractTest1() {
		setup1();
		assertEquals(0, myNum.subtract(1, new Fraction(1, 2)).getInteger());
		assertEquals(0, myNum.subtract(1, new Fraction(1, 2)).getFraction().getNumerator());
		assertEquals(0, myNum.subtract(1, new Fraction(1, 2)).getFraction().getDenominator());

		assertEquals(3, myNum.subtract(-1, new Fraction(1, 2)).getInteger());
		assertEquals(0, myNum.subtract(-1, new Fraction(1, 2)).getFraction().getNumerator());
		assertEquals(0, myNum.subtract(-1, new Fraction(1, 2)).getFraction().getDenominator());
	}

	@Test
	public void subtractTest2() {
		setup2();
		assertEquals(-3, myNum.subtract(1, new Fraction(1, 2)).getInteger());
		assertEquals(0, myNum.subtract(1, new Fraction(1, 2)).getFraction().getNumerator());
		assertEquals(0, myNum.subtract(1, new Fraction(1, 2)).getFraction().getDenominator());

		assertEquals(0, myNum.subtract(-1, new Fraction(1, 2)).getInteger());
		assertEquals(0, myNum.subtract(-1, new Fraction(1, 2)).getFraction().getNumerator());
		assertEquals(0, myNum.subtract(-1, new Fraction(1, 2)).getFraction().getDenominator());
	}

	@Test
	public void subtractTest3() {
		setup5();
		assertEquals(-1, myNum.subtract(new Fraction(3, 7)).getInteger());
		assertEquals(0, myNum.subtract(new Fraction(3, 7)).getFraction().getNumerator());
		assertEquals(0, myNum.subtract(new Fraction(3, 7)).getFraction().getDenominator());

		assertEquals(0, myNum.subtract(new Fraction(-4, 7)).getInteger());
		assertEquals(0, myNum.subtract(new Fraction(-4, 7)).getFraction().getNumerator());
		assertEquals(0, myNum.subtract(new Fraction(-4, 7)).getFraction().getDenominator());
	}

	@Test
	public void multiplyTest1() {
		setup1();
		assertEquals(2, myNum.multiply(1, new Fraction(1, 2)).getInteger());
		assertEquals(1, myNum.multiply(1, new Fraction(1, 2)).getFraction().getNumerator());
		assertEquals(4, myNum.multiply(1, new Fraction(1, 2)).getFraction().getDenominator());

		assertEquals(-2, myNum.multiply(-1, new Fraction(1, 2)).getInteger());
		assertEquals(1, myNum.multiply(-1, new Fraction(1, 2)).getFraction().getNumerator());
		assertEquals(4, myNum.multiply(-1, new Fraction(1, 2)).getFraction().getDenominator());
	}

	@Test
	public void multiplyTest2() {
		setup2();
		assertEquals(-2, myNum.multiply(1, new Fraction(1, 2)).getInteger());
		assertEquals(1, myNum.multiply(1, new Fraction(1, 2)).getFraction().getNumerator());
		assertEquals(4, myNum.multiply(1, new Fraction(1, 2)).getFraction().getDenominator());

		assertEquals(2, myNum.multiply(-1, new Fraction(1, 2)).getInteger());
		assertEquals(1, myNum.multiply(-1, new Fraction(1, 2)).getFraction().getNumerator());
		assertEquals(4, myNum.multiply(-1, new Fraction(1, 2)).getFraction().getDenominator());
	}

	@Test
	public void multiplyTest3() {
		setup5();
		assertEquals(0, myNum.multiply(new Fraction(1, 2)).getInteger());
		assertEquals(2, myNum.multiply(new Fraction(1, 2)).getFraction().getNumerator());
		assertEquals(7, myNum.multiply(new Fraction(1, 2)).getFraction().getDenominator());
		assertEquals('-', myNum.multiply(new Fraction(1, 2)).sign);

		assertEquals(0, myNum.multiply(new Fraction(1, -2)).getInteger());
		assertEquals(2, myNum.multiply(new Fraction(1, -2)).getFraction().getNumerator());
		assertEquals(7, myNum.multiply(new Fraction(1, -2)).getFraction().getDenominator());
		assertEquals('+', myNum.multiply(new Fraction(1, -2)).sign);
	}

	@Test
	public void divideTest1() {
		setup1();
		assertEquals(1, myNum.divide(1, new Fraction(1, 2)).getInteger());
		assertEquals(0, myNum.divide(1, new Fraction(1, 2)).getFraction().getNumerator());
		assertEquals(0, myNum.divide(1, new Fraction(1, 2)).getFraction().getDenominator());

		assertEquals(-1, myNum.divide(-1, new Fraction(1, 2)).getInteger());
		assertEquals(0, myNum.divide(-1, new Fraction(1, 2)).getFraction().getNumerator());
		assertEquals(0, myNum.divide(-1, new Fraction(1, 2)).getFraction().getDenominator());
	}

	@Test
	public void divideTest2() {
		setup2();
		assertEquals(-1, myNum.divide(1, new Fraction(1, 2)).getInteger());
		assertEquals(0, myNum.divide(1, new Fraction(1, 2)).getFraction().getNumerator());
		assertEquals(0, myNum.divide(1, new Fraction(1, 2)).getFraction().getDenominator());

		assertEquals(1, myNum.divide(-1, new Fraction(1, 2)).getInteger());
		assertEquals(0, myNum.divide(-1, new Fraction(1, 2)).getFraction().getNumerator());
		assertEquals(0, myNum.divide(-1, new Fraction(1, 2)).getFraction().getDenominator());
	}

	@Test
	public void divideTest3() {
		setup5();
		assertEquals(0, myNum.divide(new Fraction(11, 7)).getInteger());
		assertEquals(4, myNum.divide(1, new Fraction(4, 7)).getFraction().getNumerator());
		assertEquals(11, myNum.divide(1, new Fraction(4, 7)).getFraction().getDenominator());
		assertEquals('-', myNum.divide(1, new Fraction(4, 7)).sign);

		assertEquals(0, myNum.divide(-1, new Fraction(4, 7)).getInteger());
		assertEquals(4, myNum.divide(-1, new Fraction(4, 7)).getFraction().getNumerator());
		assertEquals(11, myNum.divide(-1, new Fraction(4, 7)).getFraction().getDenominator());
		assertEquals('+', myNum.divide(-1, new Fraction(4, 7)).sign);
	}

	@Test
	public void numbersTest() {
		setup1();
		assertTrue(3 / 2 == myNum.intValue());
		assertTrue(3 / 2 == myNum.longValue());
		assertTrue((float) (3.0 / 2) == myNum.floatValue());
		assertTrue(3.0 / 2 == myNum.doubleValue());

		setup2();
		assertTrue(-3 / 2 == myNum.intValue());
		assertTrue(-3 / 2 == myNum.longValue());
		assertTrue((float) (-3.0 / 2) == myNum.floatValue());
		assertTrue(-3.0 / 2 == myNum.doubleValue());

		setup3();
		assertTrue(17 / 2 == myNum.intValue());
		assertTrue(17 / 2 == myNum.longValue());
		assertTrue((float) 17.0 / 2 == myNum.floatValue());
		assertTrue(17.0 / 2 == myNum.doubleValue());

		setup4();
		assertEquals(4, myNum.intValue());
		assertTrue(4 == myNum.longValue());
		assertTrue((float) 4.0 == myNum.floatValue());
		assertTrue(4.0 == myNum.doubleValue());

		setup5();
		assertTrue(-4 / 7 == myNum.intValue());
		assertTrue(-4 / 7 == myNum.longValue());
		assertTrue((float) (-4.0 / 7) == myNum.floatValue());
		assertTrue(-4.0 / 7 == myNum.doubleValue());
	}
	
	@Test
	public void compareToTest() {
		setup1();
		assertEquals(1, myNum.compareTo(new MixedNumber(1)));
		assertEquals(-1, myNum.compareTo(new MixedNumber(2)));
		assertEquals(0, myNum.compareTo(new MixedNumber(1,1,2)));
		
		setup2();
		assertEquals(1, myNum.compareTo(new MixedNumber(-3)));
		assertEquals(-1, myNum.compareTo(new MixedNumber(2)));
		assertEquals(0, myNum.compareTo(new MixedNumber(-1,1,2)));
		
		setup3();
		assertEquals(1, myNum.compareTo(new MixedNumber(-3)));
		assertEquals(-1, myNum.compareTo(new MixedNumber(9)));
		assertEquals(0, myNum.compareTo(new MixedNumber(8,1,2)));
		
		setup4();
		assertEquals(1, myNum.compareTo(new MixedNumber(-3)));
		assertEquals(-1, myNum.compareTo(new MixedNumber(5)));
		assertEquals(0, myNum.compareTo(new MixedNumber(4)));
		
		setup5();
		assertEquals(1, myNum.compareTo(new MixedNumber(-3)));
		assertEquals(-1, myNum.compareTo(new MixedNumber(0,3,7)));
		assertEquals(0, myNum.compareTo(new MixedNumber(0,-4,7)));
	}
}
