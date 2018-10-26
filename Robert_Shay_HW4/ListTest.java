package edu.iastate.summer18.cs228.hw4;

import org.junit.Test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class ListTest {
	ListInterface<Integer> list = new LList<Integer>();

	public void setup() {
		for (int i = 1; i < 6; i++)
			list.add(i);
	}

	@Test
	public void addTest1() {
		list.add(1);
		assertEquals(1, (int) list.getFirst());
		assertEquals(1, (int) list.getLast());

		list.add(2);
		assertEquals(1, (int) list.getFirst());
		assertEquals(2, (int) list.getLast());

		list.add(3);
		assertEquals(1, (int) list.getFirst());
		assertEquals(3, (int) list.getLast());

		list.add(4);
		assertEquals(1, (int) list.getFirst());
		assertEquals(4, (int) list.getLast());

		list.add(5);
		assertEquals(1, (int) list.getFirst());
		assertEquals(5, (int) list.getLast());

		list.add(1, 8);
		assertEquals(8, (int) list.getFirst());
		assertEquals(5, (int) list.getLast());

		list.add(3, 23);
		assertEquals(23, (int) list.getEntry(3));
	}

	@Test
	public void removeTest1() {
		setup();
		assertEquals(5, (int) list.remove(5));
		assertEquals(4, (int) list.getLast());
		assertEquals(4, list.getLength());
		assertEquals(4, (int) list.remove(4));
		assertEquals(3, (int) list.remove(3));
		// assertEquals(new IndexOutOfBoundsException("Illegal position given to remove
		// operation."), list.remove(3));

		list.clear();
		setup();
		assertEquals(3, (int) list.remove(3));
		assertEquals(4, (int) list.remove(3));
	}

	@Test
	public void newAddTest() {
		setup();
		list.addFirst(100);
		assertEquals(100, (int) list.getFirst());

		list.addLast(101);
		assertEquals(101, (int) list.getLast());
	}

	@Test
	public void newRemoveTest() {
		setup();
		assertEquals(5, (int) list.removeLast());
		assertEquals(1, (int) list.removeFirst());
	}

	@Test
	public void placeSwapTest() {
		setup();
		list.place(1, 3);
		assertEquals(1, (int) list.getEntry(3));

		list.clear();
		setup();
		list.swap(2, 4);
		assertEquals(4, (int) list.getEntry(2));
		assertEquals(2, (int) list.getEntry(4));
	}

	@Test
	public void subListTest() {
		setup();
		assertEquals("[2, 3, 4]", list.subList(2, 4).toString());
	}

	@Test
	public void addAllTest() {
		setup();
		ListInterface<Integer> l = new CLC<Integer>();
		l.addAll(1, list);
		assertEquals("[1, 2, 3, 4, 5]", l.toString());
	}

	@Test
	public void nthIndexOfTest() {
		setup();
		assertEquals(-1, (int) list.nthIndexOf(2, 1));
		assertEquals(1, (int) list.nthIndexOf(1, 1));
		list.add(3);
		assertEquals(6, (int) list.nthIndexOf(2, 3));
	}

	@Test
	public void removeNthTest() {
		setup();
		assertFalse(list.removeNth(2, 1));
		assertTrue(list.removeNth(1, 1));
		list.add(1);
		list.add(1);
		assertTrue(list.removeNth(2, 1));
	}

	@Test
	public void removeThenAdd() {
		setup();
		assertEquals(3, (int) list.remove(3));
		list.add(3, 45);
		assertEquals(45, (int) list.getEntry(3));
	}

	@Test
	public void multiMethodTest() {
		setup();
		list.swap(3, 5);
		assertEquals("[1, 2, 5, 4, 3]", list.toString());
		assertTrue(list.contains(3));
		assertFalse(list.contains(6));

		ListInterface<Integer> added = new LList<>();
		for (int i = 6; i <= 10; i++)
			added.add(i);
		list.addAll(3, added);
		assertEquals("[1, 2, 6, 7, 8, 9, 10, 5, 4, 3]", list.toString());

		list.add(4);
		list.add(4);
		list.add(4);
		assertEquals(4, list.getFrequencyOf(4));
		assertTrue(list.removeNth(1, 4));
		assertEquals("[1, 2, 6, 7, 8, 9, 10, 5, 3, 4, 4, 4]", list.toString());

	}
}
