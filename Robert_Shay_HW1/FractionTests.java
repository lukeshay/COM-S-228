package edu.iastate.summer18.cs228.hw1;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Test cases to test Fraction class. This tests many cases that could occur
 * when using the Fraction class.
 * 
 * @author Robert Shay
 */
public class FractionTests {
	Fraction frac = new Fraction();
	FractionInterface fracI;

	public void setup() {
		frac = new Fraction();
	}

	public void setup1() {
		frac = new Fraction(3, 4);
	}

	public void setup2() {
		frac = new Fraction(-4, 6);
	}

	public void setup3() {
		frac = new Fraction(4, -6);
	}

	public void setup4() {
		frac = new Fraction(0, 8);
	}

	public void setupInterface() {
		fracI = new Fraction(7, 8);
	}

	@Test
	public void testSetFraction1() {
		boolean yee = false;
		try {
			frac.getNumerator();
		}
		catch (AssertionError ex) {
			yee = true;
		}
		assertTrue(yee);

		frac.setFraction(4, 5);
		assertEquals(4, frac.getNumerator());
		assertEquals(5, frac.getDenominator());
	}

	@Test
	public void testSetFraction2() {
		frac.setFraction(-3, 7);
		assertEquals(-3, frac.getNumerator());
		assertEquals(7, frac.getDenominator());
	}

	@Test
	public void testSetFraction3() {
		frac.setFraction(3, -7);
		assertEquals(-3, frac.getNumerator());
		assertEquals(7, frac.getDenominator());
	}

	@Test
	public void testSetFraction4() {
		frac.setFraction(new Fraction(-3, -7));
		assertEquals(3, frac.getNumerator());
		assertEquals(7, frac.getDenominator());
	}

	@Test
	public void testAdd1() {
		setup1();
		assertEquals("3 / 4 + 6 / 4", 9, frac.add(6, 4).getNumerator());
		assertEquals("3 / 4 + 6 / 4", 4, frac.add(new Fraction(6, 4)).getDenominator());

		assertEquals("3 / 4 + 3 / 4", 3, frac.add(new Fraction(3, 4)).getNumerator());
		assertEquals("3 / 4 + 3 / 4", 2, frac.add(new Fraction(3, 4)).getDenominator());

		assertEquals("3 / 4 + -3 / 4", 0, frac.add(-3, 4).getNumerator());
		assertEquals("3 / 4 + -3 / 4", 0, frac.add(new Fraction(-3, 4)).getDenominator());

		assertEquals("3 / 4 + -3 / 4", 0, frac.add(3, -4).getNumerator());
		assertEquals("3 / 4 + -3 / 4", 0, frac.add(new Fraction(3, -4)).getDenominator());
	}

	@Test
	public void testAdd2() {
		setup2();
		assertEquals(0, frac.add(new Fraction(2, 3)).getNumerator());
		assertEquals(0, frac.add(new Fraction(2, 3)).getDenominator());

		assertEquals(-4, frac.add(new Fraction(-2, 3)).getNumerator());
		assertEquals(3, frac.add(new Fraction(-2, 3)).getDenominator());

		assertEquals(-4, frac.add(new Fraction(2, -3)).getNumerator());
		assertEquals(3, frac.add(new Fraction(2, -3)).getDenominator());

		setup3();
		assertEquals(0, frac.add(new Fraction(2, 3)).getNumerator());
		assertEquals(0, frac.add(new Fraction(2, 3)).getDenominator());

		assertEquals(-4, frac.add(new Fraction(-2, 3)).getNumerator());
		assertEquals(3, frac.add(new Fraction(-2, 3)).getDenominator());

		assertEquals(-4, frac.add(new Fraction(2, -3)).getNumerator());
		assertEquals(3, frac.add(new Fraction(2, -3)).getDenominator());
	}

	@Test
	public void testAdd3() {
		setup1();
		assertEquals(3, frac.add(new Fraction(0, 5)).getNumerator());
		assertEquals(4, frac.add(new Fraction(0, 5)).getDenominator());

		assertEquals(3, frac.add(new Fraction(5, 0)).getNumerator());
		assertEquals(4, frac.add(new Fraction(5, 0)).getDenominator());

		setup4();
		assertEquals(3, frac.add(new Fraction(3, 4)).getNumerator());
		assertEquals(4, frac.add(new Fraction(3, 4)).getDenominator());

		assertEquals(0, frac.add(new Fraction(0, 4)).getNumerator());
		assertEquals(0, frac.add(new Fraction(0, 4)).getDenominator());

	}

	@Test
	public void testSubtract1() {
		setup1();
		assertEquals("3 / 4 + 6 / 4", 17, frac.subtract(1, 7).getNumerator());
		assertEquals("3 / 4 + 6 / 4", 28, frac.subtract(new Fraction(1, 7)).getDenominator());

		assertEquals("3 / 4 + 3 / 4", 0, frac.subtract(new Fraction(3, 4)).getNumerator());
		assertEquals("3 / 4 + 3 / 4", 0, frac.subtract(new Fraction(3, 4)).getDenominator());

		assertEquals("3 / 4 + -3 / 4", 3, frac.subtract(-3, 4).getNumerator());
		assertEquals("3 / 4 + -3 / 4", 2, frac.subtract(new Fraction(-3, 4)).getDenominator());

		assertEquals("3 / 4 + -3 / 4", 3, frac.subtract(3, -4).getNumerator());
		assertEquals("3 / 4 + -3 / 4", 2, frac.subtract(new Fraction(3, -4)).getDenominator());
	}

	@Test
	public void testSubtract2() {
		setup2();
		assertEquals(-3, frac.subtract(new Fraction(5, 6)).getNumerator());
		assertEquals(2, frac.subtract(new Fraction(5, 6)).getDenominator());

		assertEquals(0, frac.subtract(new Fraction(-2, 3)).getNumerator());
		assertEquals(0, frac.subtract(new Fraction(-2, 3)).getDenominator());

		assertEquals(-1, frac.subtract(new Fraction(1, -3)).getNumerator());
		assertEquals(3, frac.subtract(new Fraction(1, -3)).getDenominator());

		setup3();
		assertEquals(-3, frac.subtract(new Fraction(5, 6)).getNumerator());
		assertEquals(2, frac.subtract(new Fraction(5, 6)).getDenominator());

		assertEquals(0, frac.subtract(new Fraction(-2, 3)).getNumerator());
		assertEquals(0, frac.subtract(new Fraction(-2, 3)).getDenominator());

		assertEquals(-1, frac.subtract(new Fraction(1, -3)).getNumerator());
		assertEquals(3, frac.subtract(new Fraction(1, -3)).getDenominator());
	}

	@Test
	public void testSubtract3() {
		setup1();
		assertEquals(3, frac.subtract(new Fraction(0, 5)).getNumerator());
		assertEquals(4, frac.subtract(new Fraction(0, 5)).getDenominator());

		assertEquals(3, frac.subtract(new Fraction(5, 0)).getNumerator());
		assertEquals(4, frac.subtract(new Fraction(5, 0)).getDenominator());

		setup4();
		assertEquals(-3, frac.subtract(new Fraction(3, 4)).getNumerator());
		assertEquals(4, frac.subtract(new Fraction(3, 4)).getDenominator());

		assertEquals(0, frac.subtract(new Fraction(0, 4)).getNumerator());
		assertEquals(0, frac.subtract(new Fraction(0, 4)).getDenominator());
	}

	@Test
	public void testMultiply1() {
		setup1();
		assertEquals("3 / 4 * 5 / 2", 15, frac.multiply(5, 2).getNumerator());
		assertEquals("3 / 4 * 5 / 2", 8, frac.multiply(new Fraction(5, 2)).getDenominator());

		assertEquals("3 / 4 * 0 / 2", 0, frac.multiply(0, 2).getNumerator());
		assertEquals("3 / 4 * 0 / 2", 0, frac.multiply(new Fraction(0, 2)).getDenominator());

		assertEquals("3 / 4 * 3 / 0", 0, frac.multiply(3, 0).getNumerator());
		assertEquals("3 / 4 * 3 / 0", 0, frac.multiply(new Fraction(3, 0)).getDenominator());

		assertEquals("3 / 4 * 0 / 0", 0, frac.multiply(0, 0).getNumerator());
		assertEquals("3 / 4 * 0 / 0", 0, frac.multiply(new Fraction(0, 0)).getDenominator());
	}

	@Test
	public void testMultiply2() {
		setup2();
		assertEquals(-1, frac.multiply(new Fraction(1, 4)).getNumerator());
		assertEquals(6, frac.multiply(new Fraction(1, 4)).getDenominator());

		assertEquals(4, frac.multiply(new Fraction(-2, 3)).getNumerator());
		assertEquals(9, frac.multiply(new Fraction(-2, 3)).getDenominator());

		assertEquals(4, frac.multiply(new Fraction(2, -3)).getNumerator());
		assertEquals(9, frac.multiply(new Fraction(2, -3)).getDenominator());

		setup3();
		assertEquals(-1, frac.multiply(new Fraction(1, 4)).getNumerator());
		assertEquals(6, frac.multiply(new Fraction(1, 4)).getDenominator());

		assertEquals(4, frac.multiply(new Fraction(-2, 3)).getNumerator());
		assertEquals(9, frac.multiply(new Fraction(-2, 3)).getDenominator());

		assertEquals(4, frac.multiply(new Fraction(2, -3)).getNumerator());
		assertEquals(9, frac.multiply(new Fraction(2, -3)).getDenominator());
	}

	@Test
	public void testMultiply3() {
		setup1();
		assertEquals(0, frac.multiply(new Fraction(0, 5)).getNumerator());
		assertEquals(0, frac.multiply(new Fraction(0, 5)).getDenominator());

		assertEquals(0, frac.multiply(new Fraction(5, 0)).getNumerator());
		assertEquals(0, frac.multiply(new Fraction(5, 0)).getDenominator());

		setup4();
		assertEquals(0, frac.multiply(new Fraction(3, 4)).getNumerator());
		assertEquals(0, frac.multiply(new Fraction(3, 4)).getDenominator());

		assertEquals(0, frac.multiply(new Fraction(0, 4)).getNumerator());
		assertEquals(0, frac.multiply(new Fraction(0, 4)).getDenominator());
	}

	@Test
	public void testDivide1() {
		setup1();
		assertEquals("3 / 4  /  5 / 2", 3, frac.divide(5, 2).getNumerator());
		assertEquals("3 / 4  /  5 / 2", 10, frac.divide(new Fraction(5, 2)).getDenominator());

		assertEquals("3 / 4  /  0 / 2", 0, frac.divide(0, 2).getNumerator());
		assertEquals("3 / 4  /  0 / 2", 0, frac.divide(new Fraction(0, 2)).getDenominator());

		assertEquals("3 / 4  /  3 / 0", 0, frac.divide(3, 0).getNumerator());
		assertEquals("3 / 4  /  3 / 0", 0, frac.divide(new Fraction(3, 0)).getDenominator());

		assertEquals("3 / 4  /  0 / 0", 0, frac.divide(0, 0).getNumerator());
		assertEquals("3 / 4  /  0 / 0", 0, frac.divide(new Fraction(0, 0)).getDenominator());

		assertEquals("3 / 4  /  3 / 4", 1, frac.divide(new Fraction(3, 4)).getNumerator());
		assertEquals("3 / 4  /  3 / 4", 1, frac.divide(new Fraction(3, 4)).getDenominator());
	}

	@Test
	public void testDivide2() {
		setup2();
		assertEquals(-8, frac.divide(new Fraction(1, 4)).getNumerator());
		assertEquals(3, frac.divide(new Fraction(1, 4)).getDenominator());

		assertEquals(1, frac.divide(new Fraction(-2, 3)).getNumerator());
		assertEquals(1, frac.divide(new Fraction(-2, 3)).getDenominator());

		assertEquals(1, frac.divide(new Fraction(2, -3)).getNumerator());
		assertEquals(1, frac.divide(new Fraction(2, -3)).getDenominator());

		setup3();
		assertEquals(-8, frac.divide(new Fraction(1, 4)).getNumerator());
		assertEquals(3, frac.divide(new Fraction(1, 4)).getDenominator());

		assertEquals(1, frac.divide(new Fraction(-2, 3)).getNumerator());
		assertEquals(1, frac.divide(new Fraction(-2, 3)).getDenominator());

		assertEquals(1, frac.divide(new Fraction(2, -3)).getNumerator());
		assertEquals(1, frac.divide(new Fraction(2, -3)).getDenominator());
	}

	@Test
	public void testDivide3() {
		setup1();
		assertEquals(0, frac.divide(new Fraction(0, 5)).getNumerator());
		assertEquals(0, frac.divide(new Fraction(0, 5)).getDenominator());

		assertEquals(0, frac.divide(new Fraction(5, 0)).getNumerator());
		assertEquals(0, frac.divide(new Fraction(5, 0)).getDenominator());

		setup4();
		assertEquals(0, frac.divide(new Fraction(3, 4)).getNumerator());
		assertEquals(0, frac.divide(new Fraction(3, 4)).getDenominator());

		assertEquals(0, frac.divide(new Fraction(0, 4)).getNumerator());
		assertEquals(0, frac.divide(new Fraction(0, 4)).getDenominator());
	}

	@Test
	public void interfaceObjectTest() {
		setupInterface();
		assertEquals(7, fracI.getNumerator());
		assertEquals(8, fracI.getDenominator());

		assertEquals(8, fracI.getReciprocal().getNumerator());
		assertEquals(7, fracI.getReciprocal().getDenominator());

		assertEquals(15, fracI.add(new Fraction(1, 16)).getNumerator());
		assertEquals(16, fracI.add(new Fraction(1, 16)).getDenominator());

		assertEquals(13, fracI.subtract(new Fraction(1, 16)).getNumerator());
		assertEquals(16, fracI.subtract(new Fraction(1, 16)).getDenominator());

		assertEquals(7, fracI.multiply(new Fraction(1, 16)).getNumerator());
		assertEquals(8 * 16, fracI.multiply(new Fraction(1, 16)).getDenominator());

		assertEquals(14, fracI.divide(new Fraction(1, 16)).getNumerator());
		assertEquals(1, fracI.divide(new Fraction(1, 16)).getDenominator());
	}

	@Test
	public void reciprocalTest1() {
		setup1();
		assertEquals(4, frac.getReciprocal().getNumerator());
		assertEquals(3, frac.getReciprocal().getDenominator());
	}

	@Test
	public void reciprocalTest2() {
		setup2();
		assertEquals(-3, frac.getReciprocal().getNumerator());
		assertEquals(2, frac.getReciprocal().getDenominator());
	}

	@Test
	public void reciprocalTest3() {
		setup4();
		assertEquals(0, frac.getReciprocal().getNumerator());
		assertEquals(0, frac.getReciprocal().getDenominator());
	}

	@Test
	public void numbersTest() {
		setup1();
		assertTrue(0 == frac.intValue());
		assertTrue(3.0 / 4 == frac.doubleValue());
		assertTrue(3.0 / 4 == frac.floatValue());
		assertTrue(0 == frac.longValue());

	}

	@Test
	public void compareToTest() {
		setup1();
		assertEquals(1, frac.compareTo(new Fraction(1, 4)));
		assertEquals(1, frac.compareTo(new Fraction(-3, 4)));
		assertEquals(-1, frac.compareTo(new Fraction(4, 4)));
		assertEquals(-1, frac.compareTo(new Fraction(-5, -4)));
		assertEquals(0, frac.compareTo(new Fraction(3, 4)));
		assertEquals(0, frac.compareTo(new Fraction(-6, -8)));
	}

	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void equalsTest() {
		setup1();
		assertTrue(frac.equals(new Fraction(3, 4)));
		assertTrue(frac.equals(new Fraction(-3, -4)));
		assertFalse(frac.equals(new Fraction(-3, 4)));
		assertFalse(frac.equals(new Fraction(2, 4)));

		setup4();
		assertTrue(frac.equals(new Fraction(0, 0)));
		assertFalse(frac.equals(5));
		assertFalse(frac.equals(new Fraction(3, 2)));
	}
}