package edu.iastate.summer18.cs228.hw2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

/**
 * Test cases to check doubly linked bag for errors.
 * 
 * @author Robert Shay
 */
public class DoublyLinkedBagTest {
	DoublyLinkedBag<Integer> bag1 = new DoublyLinkedBag<>();
	DoublyLinkedBag<Integer> bag2 = new DoublyLinkedBag<>();

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
	public void testClear() {
		setup1_1();
		bag1.clear();

		assertEquals("[]", Arrays.toString(bag1.toArray()));
	}

	@Test
	public void testContains() {
		setup1_1();

		assertTrue(bag1.contains(1));
		assertFalse(bag1.contains(100));
	}

	@Test
	public void testGetCurrentSize() {
		setup1_1();

		assertEquals(5, bag1.getCurrentSize());
	}

	@Test
	public void testGetFrequencyOf() {
		setup1_1();

		assertEquals(2, bag1.getFrequencyOf(1));
		assertEquals(0, bag1.getFrequencyOf(100));
	}

	@Test
	public void testIsEmpty() {
		assertTrue(bag1.isEmpty());

		setup1_1();

		assertFalse(bag1.isEmpty());
	}

	@Test
	public void testRemove() {
		setup1_1();

		Integer i = bag1.remove();

		assertEquals((int) 7, (int) i);
		assertEquals(4, bag1.getCurrentSize());

		bag1.clear();
		setup1_1();
		bag1.remove(1);

		assertEquals(4, bag1.getCurrentSize());

		bag1.clear();
		setup1_1();
		bag1.removeEntry(1);

		assertEquals(3, bag1.getCurrentSize());
	}

	@Test
	public void testToArray() {
		setup1_1();

		assertEquals("[7, 1, 30, 2, 1]", Arrays.toString(bag1.toArray()));
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
		DoublyLinkedBag<Integer> temp = new DoublyLinkedBag<>();
		temp.add(25);
		temp.add(2);
		temp.add(1);

		assertTrue(bag2.equals(temp));
		assertFalse(bag1.equals(temp));
	}
}
