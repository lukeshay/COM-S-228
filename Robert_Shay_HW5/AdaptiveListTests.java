package edu.iastate.summer18.cs228.hw5;

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.ListIterator;

public class AdaptiveListTests {
	AdaptiveList<Integer> list = new AdaptiveList<Integer>();

	@Before
	public void setup() {
		for (int i = 1; i < 6; i++)
			list.add(i);
	}

	@Test
	public void addTest1() {
		list.clear();
		assertTrue(list.add(1));
		assertFalse(list.getarrayUTD());
		assertEquals(1, (int) list.get(0));
		assertTrue(list.getarrayUTD());
		assertTrue(list.getlinkedUTD());

		assertTrue(list.add(2));
		assertEquals(1, (int) list.get(0));
		assertEquals(2, (int) list.get(1));

		assertTrue(list.add(3));
		assertEquals(1, (int) list.get(0));
		assertEquals(3, (int) list.get(2));

		assertTrue(list.add(4));
		assertEquals(1, (int) list.get(0));
		assertEquals(4, (int) list.get(3));

		assertTrue(list.add(5));
		assertEquals(1, (int) list.get(0));
		assertEquals(5, (int) list.get(4));

		list.add(1, 8);
		assertEquals(8, (int) list.get(1));
		assertEquals(5, (int) list.get(5));

		list.add(3, 23);
		assertEquals(23, (int) list.get(3));

		list.add(null);
		assertEquals(null, list.get(7));
	}

	@Test
	public void removeTest1() {
		assertEquals(5, (int) list.remove(4));
		assertEquals(4, (int) list.get(list.size() - 1));
		assertEquals(4, list.size());
		assertEquals(4, (int) list.remove(3));
		assertEquals(3, (int) list.remove(2));
		// assertEquals(new IndexOutOfBoundsException("Illegal position given to remove
		// operation."), list.remove(3));

		list.clear();
		setup();
		assertEquals(4, (int) list.remove(3));
		assertEquals(5, (int) list.remove(3));
	}

	@Test
	public void addAllTest() {
		AdaptiveList<Integer> l = new AdaptiveList<Integer>();
		l.addAll(0, list);
		assertEquals(
				"A sequence of items from the most recent array:\n[]\nA sequence of items from the most recent linked list:\n(1, 2, 3, 4, 5)",
				l.toString());

		list.addAll(list);
		assertEquals(
				"A sequence of items from the most recent array:\n[]\nA sequence of items from the most recent linked list:\n(1, 2, 3, 4, 5, 1, 2, 3, 4, 5)",
				list.toString());
	}

	@Test
	public void removeThenAdd() {
		assertEquals(4, (int) list.remove(3));
		list.add(3, 45);
		assertEquals(45, (int) list.get(3));
	}

	@Test
	public void indexOfTest() {
		list.add(3);
		assertEquals(2, list.indexOf(3));
		assertEquals(5, list.lastIndexOf(3));
		assertEquals(1, list.indexOf(2));
		assertEquals(1, list.lastIndexOf(2));

		list.add(0, null);
		list.add(null);
		assertEquals(0, list.indexOf(null));
		assertEquals(7, list.lastIndexOf(null));
	}

	@Test
	public void removeAndRetainAllTest() {
		AdaptiveList<Integer> list2 = new AdaptiveList<>();
		list2.add(1);
		list2.add(3);
		list2.add(2);

		list.removeAll(list2);
		assertEquals(
				"A sequence of items from the most recent array:\n[]\nA sequence of items from the most recent linked list:\n(4, 5)",
				list.toString());

		list.clear();
		setup();
		list.retainAll(list2);
		assertEquals(
				"A sequence of items from the most recent array:\n[]\nA sequence of items from the most recent linked list:\n(1, 2, 3)",
				list.toString());
	}

	@Test
	public void reverseTest() {
		list.reverse();
		assertEquals(
				"A sequence of items from the most recent array:\n[5, 4, 3, 2, 1]\nA sequence of items from the most recent linked list:\n(1, 2, 3, 4, 5)",
				list.toString());
	}

	@Test
	public void setTest() {
		assertEquals(3, (int) list.set(2, 7));
		assertEquals(
				"A sequence of items from the most recent array:\n[1, 2, 7, 4, 5]\nA sequence of items from the most recent linked list:\n(1, 2, 3, 4, 5)",
				list.toString());

		boolean pass = false;
		try {
			list.set(8, 1);
		}
		catch (IndexOutOfBoundsException ex) {
			pass = true;
		}
		assertTrue(pass);

		assertEquals(7, (int) list.set(2, null));
		assertEquals(null, list.get(2));
	}

	@Test
	public void containsTest() {
		assertFalse(list.contains(null));
		assertTrue(list.contains(3));

		AdaptiveList<Integer> l = new AdaptiveList<>();
		l.add(1);
		l.add(2);

		assertTrue(list.containsAll(l));
	}

	@Test
	public void iteratorTest1() {
		ListIterator<Integer> itr = list.listIterator();

		assertTrue(itr.hasNext());
		assertEquals(1, (int) itr.next());
		itr.remove();
		assertEquals(
				"A sequence of items from the most recent array:\n[]\nA sequence of items from the most recent linked list:\n(2, 3, 4, 5)",
				list.toString());

		// itr.previous();
		assertEquals(-1, itr.previousIndex());
		assertEquals(0, itr.nextIndex());

		// itr.set(1);
		assertEquals(2, (int) itr.next());
		assertEquals(3, (int) itr.next());
		assertEquals(3, (int) itr.previous());
		itr.set(8);
		assertEquals(8, (int) list.get(1));
	}
}
