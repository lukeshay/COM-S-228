package edu.iastate.summer18.cs228.hw2;

import org.junit.Test;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.Arrays;

/**
 * Test cases to check the methods that were added to linked bag for errors.
 * 
 * @author Robert Shay
 */
public class LinkedBagTest {
	LinkedBag<Integer> bag1 = new LinkedBag<>();
	LinkedBag<Integer> bag2 = new LinkedBag<>();

	public void setup1_1() {
		bag1.add(1);
		bag1.add(2);
		bag1.add(30);
		bag1.add(1);
		bag1.add(7);
	}

	public void setup2_1() {
		bag2.add(1);
		bag2.add(2);
		bag2.add(25);
	}

	public void setup2_2() {
		bag2.add(1);
		bag2.add(2);
		bag2.add(30);
		bag2.add(1);
		bag2.add(7);
	}

	@Test
	public void testUnion1() {
		setup1_1();
		setup2_1();
		Integer[] union = { 1, 2, 30, 1, 7, 1, 2, 25 };
		Integer[] union2 = { 1, 2, 25, 1, 2, 30, 1, 7 };
		Object[] bagArr = bag1.union(bag2).toArray();
		Object[] bagArr2 = bag2.union(bag1).toArray();

		assertEquals(Arrays.toString(union), Arrays.toString(bagArr));
		assertEquals(Arrays.toString(union2), Arrays.toString(bagArr2));
	}

	@Test
	public void testUnion2() {
		assertEquals("[]", Arrays.toString(bag1.union(bag2).toArray()));
	}

	@Test
	public void testIntersection1() {
		setup1_1();
		setup2_1();
		Object[] intersection = { 2, 1 };
		Object[] intersection2 = { 1, 2 };
		Object[] bagArr = bag1.intersection(bag2).toArray();
		Object[] bagArr2 = bag2.intersection(bag1).toArray();

		assertEquals(Arrays.toString(intersection), Arrays.toString(bagArr));
		assertEquals(Arrays.toString(intersection2), Arrays.toString(bagArr2));
	}

	@Test
	public void testIntersection2() {
		assertEquals("[]", Arrays.toString(bag1.intersection(bag2).toArray()));
	}

	@Test
	public void testIntersection3() {
		setup1_1();
		setup2_2();
		Object[] intersection = { 1, 2, 30, 1, 7 };

		assertEquals(Arrays.toString(intersection), Arrays.toString(bag1.intersection(bag2).toArray()));
	}

	@Test
	public void testDifference1() {
		setup1_1();
		setup2_1();
		Object[] difference = { 1, 30, 7 };
		Object[] difference2 = { 25 };
		Object[] bagArr = bag1.difference(bag2).toArray();
		Object[] bagArr2 = bag2.difference(bag1).toArray();

		assertEquals(Arrays.toString(difference), Arrays.toString(bagArr));
		assertEquals(Arrays.toString(difference2), Arrays.toString(bagArr2));
	}

	@Test
	public void testDifference2() {
		assertEquals("[]", Arrays.toString(bag1.difference(bag2).toArray()));
	}

	@Test
	public void testDifference3() {
		setup1_1();
		setup2_2();

		assertEquals("[]", Arrays.toString(bag1.difference(bag2).toArray()));
	}

	@Test
	public void testDifference4() {
		setup1_1();

		assertEquals("[1, 2, 30, 1, 7]", Arrays.toString(bag1.difference(bag2).toArray()));
	}

	@Test
	public void testReplace() {
		setup1_1();
		Object[] replace = { 4, 1, 30, 2, 1 };
		bag1.replace(4);

		assertEquals(Arrays.toString(replace), Arrays.toString(bag1.toArray()));
	}

	@Test
	public void testRemoveEntry() {
		setup1_1();
		Object[] remove = { 30, 2, 7 };
		bag1.removeEntry(1);

		assertEquals(Arrays.toString(remove), Arrays.toString(bag1.toArray()));
	}

	@Test
	public void testEquals() {
		setup2_1();
		setup1_1();
		LinkedBag<Integer> temp = new LinkedBag<>();
		temp.add(25);
		temp.add(2);
		temp.add(1);

		assertTrue(bag2.equals(temp));
		assertFalse(bag1.equals(temp));
	}
	
	@Test
	public void clearTest() {
		setup1_1();
		bag1.clear();
		assertTrue(bag1.isEmpty());
	}
}
