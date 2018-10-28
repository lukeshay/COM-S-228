/**
 * 
 */
package edu.iastate.summer18.cs228.hw4;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * @author Robert Shay
 */
public class Snippet {

	@Test
	public void AListadd() {
		AList<Integer> list = new AList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		assertEquals(3, list.getLength());
	}

	@Test
	public void Alistempty() {
		AList<Integer> list = new AList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		assertEquals(false, list.isEmpty());
	}

	@Test
	public void AListaddatEntry() {
		AList<Integer> list = new AList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(2, 5);
		assertEquals(5, (int) list.getEntry(2));
		assertEquals(2, (int) list.getEntry(3));
		assertEquals(3, (int) list.getEntry(4));
	}

	@Test
	public void AListRemove() {
		AList<Integer> list = new AList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		assertEquals(2, (int) list.remove(2));
		assertEquals(2, (int) list.getLength());
	}

	@Test
	public void AListReplace() {
		AList<Integer> list = new AList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		assertEquals(3, (int) list.replace(3, 4));
		assertEquals(4, (int) list.getEntry(3));
	}

	@Test
	public void AListContains() {
		AList<Integer> list = new AList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		assertEquals(false, list.contains(5));
	}

	@Test
	public void AListAddFirst() {
		AList<Integer> list = new AList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.addFirst(5);
		assertEquals(5, (int) list.getEntry(1));
		assertEquals(4, (int) list.getLength());
	}

	@Test
	public void AListAddLast() {
		AList<Integer> list = new AList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.addLast(-1);
		assertEquals(-1, (int) list.getEntry(4));
		assertEquals(4, list.getLength());
	}

	@Test
	public void AListRemoveFirst() {
		AList<Integer> list = new AList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		assertEquals(1, (int) list.removeFirst());
		assertEquals(2, (int) list.getLength());
	}

	@Test
	public void AListRemoveLast() {
		AList<Integer> list = new AList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		assertEquals(3, (int) list.removeLast());
		assertEquals(2, list.getLength());
	}

	@Test
	public void AListGetFirst() {
		AList<Integer> list = new AList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		assertEquals(1, (int) list.getFirst());
	}

	@Test
	public void AListGetLast() {
		AList<Integer> list = new AList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		assertEquals(3, (int) list.getLast());
	}

	@Test
	public void AListPlace() {
		AList<Integer> list = new AList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.place(1, 3);
		assertEquals(1, (int) list.getEntry(3));
		assertEquals(1, (int) list.getEntry(1));
		assertEquals(3, list.getLength());
	}

	@Test
	public void AListSwap() {
		AList<Integer> list = new AList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.swap(1, 3);
		assertEquals(3, (int) list.getEntry(1));
		assertEquals(1, (int) list.getEntry(3));
	}

	@Test
	public void AListSublist() {
		AList<Integer> list = new AList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		list.add(7);
		AList<Integer> temp = (AList<Integer>) list.subList(3, 7);
		assertEquals(5, temp.getLength());
		assertEquals(3, (int) temp.getEntry(1));
		assertEquals(4, (int) temp.getEntry(2));
		assertEquals(5, (int) temp.getEntry(3));
		assertEquals(6, (int) temp.getEntry(4));
		assertEquals(7, (int) temp.getEntry(5));
	}

	@Test
	public void AistAddAll() {
		AList<Integer> list = new AList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		AList<Integer> temp = new AList<Integer>();
		temp.add(6);
		temp.add(7);
		temp.add(8);
		list.addAll(3, temp);
		assertEquals(8, list.getLength());
		assertEquals(6, (int) list.getEntry(3));
		assertEquals(8, (int) list.getEntry(5));
		assertEquals(3, (int) list.getEntry(6));
	}

	@Test
	public void AListNth() {
		AList<Integer> list = new AList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		AList<Integer> second = new AList<Integer>();
		second.add(1);
		second.add(2);
		second.add(3);
		second.add(2);
		assertEquals(-1, list.nthIndexOf(2, 2));
		assertEquals(4, second.nthIndexOf(2, 2));
	}

	@Test
	public void AListNthrem() {
		AList<Integer> second = new AList<Integer>();
		second.add(1);
		second.add(2);
		second.add(3);
		second.add(2);
		second.removeNth(2, 2);
		assertEquals(3, second.getLength());
		AList<Integer> temp = new AList<Integer>();
		temp.add(1);
		temp.add(2);
		temp.add(3);
		temp.add(2);
		assertEquals(false, temp.removeNth(2, 3));
	}

	@Test
	public void AlistFreq() {
		AList<Integer> second = new AList<Integer>();
		second.add(1);
		second.add(2);
		second.add(3);
		second.add(2);
		assertEquals(2, second.getFrequencyOf(2));
	}

	@Test
	public void AListToString() {
		AList<Integer> second = new AList<Integer>();
		second.add(1);
		second.add(2);
		second.add(3);
		second.add(2);
		AList<Integer> temp = new AList<Integer>();
		assertEquals("[1, 2, 3, 2]", second.toString());
		assertEquals("[ ]", temp.toString());
	}

	@Test
	public void AListequals() {
		AList<Integer> second = new AList<Integer>();
		second.add(1);
		second.add(2);
		AList<Integer> temp = new AList<Integer>();
		temp.add(1);
		temp.add(2);
		assertEquals(true, second.equals(temp));
	}

	@Test
	public void LListAdd() {
		LList<Integer> list = new LList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		assertEquals(3, list.getLength());
	}

	@Test
	public void LlistEmpty() {
		LList<Integer> list = new LList<Integer>();
		assertEquals(true, list.isEmpty());
	}

	@Test
	public void LListAddEntry() {
		LList<Integer> list = new LList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(2, 5);
		assertEquals(5, (int) list.getEntry(2));
		assertEquals(2, (int) list.getEntry(3));
		assertEquals(3, (int) list.getEntry(4));
	}

	@Test
	public void LListRemove() {
		LList<Integer> list = new LList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		assertEquals(2, (int) list.remove(2));
		assertEquals(2, (int) list.getLength());
	}

	@Test
	public void LListReplace() {
		LList<Integer> list = new LList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		assertEquals(3, (int) list.replace(3, 4));
		assertEquals(4, (int) list.getEntry(3));
	}

	@Test
	public void LListContains() {
		LList<Integer> list = new LList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		assertEquals(false, list.contains(5));
	}
}
