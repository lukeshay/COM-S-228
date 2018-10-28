package edu.iastate.summer18.cs228.hw5;

/*
 * An implementation of List<E> based on a doubly-linked list with an array for
 * indexed reads/writes
 * @author Robert Shay
 */
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class AdaptiveList<E> implements List<E> {
	public class ListNode // private member of outer class
	{
		public E data; // public members:
		public ListNode link; // used outside the inner class
		public ListNode prev; // used outside the inner class

		public ListNode(E item) {
			data = item;
			link = prev = null;
		}
	}

	public ListNode head; // dummy node made public for testing.
	public ListNode tail; // dummy node made public for testing.
	private int numItems; // number of data items
	private boolean linkedUTD; // true if the linked list is up-to-date.

	public E[] theArray; // the array for storing elements
	private boolean arrayUTD; // true if the array is up-to-date.

	public AdaptiveList() {
		clear();
	}

	@Override
	public void clear() {
		head = new ListNode(null);
		tail = new ListNode(null);
		head.link = tail;
		tail.prev = head;
		numItems = 0;
		linkedUTD = true;
		arrayUTD = false;
		theArray = null;
	}

	/**
	 * Returns whether the linked list is up to date.
	 * 
	 * @return True if up to date.
	 */
	public boolean getlinkedUTD() {
		return linkedUTD;
	}

	/**
	 * Returns whether the array is up to date.
	 * 
	 * @return True if up to date.
	 */
	public boolean getarrayUTD() {
		return arrayUTD;
	}

	/**
	 * Constructs a new Adaptive List and adds all the items from the inputed
	 * collection.
	 * 
	 * @param c
	 */
	public AdaptiveList(Collection<? extends E> c) {
		clear();
		addAll(c);
	}

	// Removes the node from the linked list.
	// This method should be used to remove a node from the linked list.
	private void unlink(ListNode toRemove) {
		if (!linkedUTD) updateLinked();
		if (toRemove == head || toRemove == tail) throw new RuntimeException("An attempt to remove head or tail");
		toRemove.prev.link = toRemove.link;
		toRemove.link.prev = toRemove.prev;
	}

	// Inserts new node toAdd right after old node current.
	// This method should be used to add a node to the linked list.
	private void link(ListNode current, ListNode toAdd) {
		if (!linkedUTD) updateLinked();
		if (current == tail) throw new RuntimeException("An attempt to link after tail");
		if (toAdd == head || toAdd == tail) throw new RuntimeException("An attempt to add head/tail as a new node");
		toAdd.link = current.link;
		toAdd.link.prev = toAdd;
		toAdd.prev = current;
		current.link = toAdd;
	}

	@SuppressWarnings("unchecked")
	private void updateArray() // makes theArray up-to-date.
	{
		if (numItems < 0) throw new RuntimeException("numItems is negative: " + numItems);
		if (!linkedUTD) throw new RuntimeException("linkedUTD is false");

		theArray = (E[]) new Object[numItems];
		int i = 0;
		for (ListNode cur = head.link; cur != null && i < numItems; cur = cur.link) {
			theArray[i] = cur.data;
			i++;
		}
		arrayUTD = true;
	}

	private void updateLinked() // makes the linked list up-to-date.
	{
		if (numItems < 0) throw new RuntimeException("numItems is negative: " + numItems);
		if (!arrayUTD) throw new RuntimeException("arrayUTD is false");
		if (theArray == null || theArray.length < numItems) throw new RuntimeException("theArray is null or shorter");

		int i = 0;
		for (ListNode cur = head.link; cur != null && cur != tail; cur = cur.link) {
			cur.data = theArray[i];
			i++;
		}
		linkedUTD = true;
	}

	@Override
	public int size() {
		return numItems;
	}

	@Override
	public boolean isEmpty() {
		if (!linkedUTD) updateLinked();
		return head.link == tail;
	}

	@Override
	public boolean add(E obj) {
		if (!linkedUTD) updateLinked();
		ListNode newNode = new ListNode(obj);

		link(tail.prev, newNode);

		numItems++;
		arrayUTD = false;
		return true;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		if (!linkedUTD) updateLinked();
		if (c.isEmpty()) return false;

		int size = c.size();
		int i = 0;

		for (E e : c) {
			add(e);
			i++;
			if (i >= size) break;
		}
		return true;
	} // addAll 1

	@Override
	public boolean remove(Object obj) {
		if (!linkedUTD) updateLinked();
		for (ListNode node = head.link; node != tail && node != null; node = node.link)
			if (node.data.equals(obj)) {
				unlink(node);
				return true;
			}
		return false;
	}

	private void removeFirst() {
		if (head == null) throw new NoSuchElementException("empty list");
		unlink(head.link);
		numItems--;
	}

	private void checkIndex(int pos) // a helper method
	{
		if (pos >= numItems || pos < 0) throw new IndexOutOfBoundsException("Index: " + pos + ", Size: " + numItems);
	}

	private void checkIndex2(int pos) // a helper method
	{
		if (pos > numItems || pos < 0) throw new IndexOutOfBoundsException("Index: " + pos + ", Size: " + numItems);
	}

	private void checkNode(ListNode cur) // a helper method
	{
		if (cur == null || cur == tail) throw new RuntimeException("numItems: " + numItems + " is too large");
	}

	private ListNode findNode(int pos) // a helper method
	{
		checkIndex(pos);
		if (!linkedUTD) updateLinked();
		ListNode cur = head.link;
		for (int i = 0; i < pos; i++) {
			checkNode(cur);
			cur = cur.link;
		}
		checkNode(cur);
		return cur;
	}

	@Override
	public void add(int pos, E obj) {
		if (pos != 0) checkIndex2(pos);
		if (pos == size()) add(obj);
		else {
			if (!linkedUTD) updateLinked();

			if (pos == 0) link(head, new ListNode(obj));
			else link(findNode(pos - 1), new ListNode(obj));

			numItems++;
		}
		arrayUTD = false;
	}

	@Override
	public boolean addAll(int pos, Collection<? extends E> c) {
		if (!linkedUTD) updateLinked();
		if (size() != 0) checkIndex2(pos);
		if (c.isEmpty()) return false;

		int size = c.size();
		int i = 0;

		for (E e : c) {
			add(i + pos, e);
			i++;
			if (i >= size) break;
		}
		return true;
	} // addAll 2

	private void addFirst(E obj) {
		link(head, new ListNode(obj));
	} // constant time vs. O(n) time for an array

	@Override
	public E remove(int pos) {
		if (!linkedUTD) updateLinked();
		checkIndex(pos);

		if (pos == 0) {
			if (head == null) throw new NoSuchElementException("Index 0");
			E returnVal = head.link.data;
			removeFirst();
			return returnVal;
		}

		ListNode toRemove = findNode(pos);
		E returned = toRemove.data;
		unlink(toRemove);

		numItems--;
		return returned;
	}

	@Override
	public E get(int pos) {
		if (!arrayUTD) updateArray();
		checkIndex(pos);
		return theArray[pos];
	}

	@Override
	public E set(int pos, E obj) {
		checkIndex(pos);
		if (!arrayUTD) updateArray();
		assert arrayUTD;

		E returned = theArray[pos];
		theArray[pos] = obj;
		linkedUTD = false;

		return returned;
	}

	/**
	 * If the number of elements is at most 1, the method returns false. Otherwise,
	 * it reverses the order of the elements in the array without using any
	 * additional array, and returns true. Note that if the array is modified, then
	 * linkedUTD needs to be set to false.
	 */
	public boolean reverse() {
		if (numItems <= 1) return false;
		if (!arrayUTD) updateArray();
		assert arrayUTD;
		reverseHelper(0);
		linkedUTD = false;
		return true; // may need to be revised.
	}

	/**
	 * Used to reverse the elements recursively.
	 * 
	 * @param n
	 */
	private void reverseHelper(int n) {
		int n1 = theArray.length - 1 - n;
		E temp = theArray[n1];
		theArray[n1] = theArray[n];
		theArray[n] = temp;

		if (n != numItems / 2) reverseHelper(n + 1);
	}

	@Override
	public boolean contains(Object obj) {
		return indexOf(obj) != -1;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		if (!linkedUTD) updateLinked();

		Object[] arr = c.toArray();
		for (Object obj : arr) {
			if (!contains(obj)) return false;
		}
		return true;
	} // containsAll

	@Override
	public int indexOf(Object obj) {
		if (!linkedUTD) updateLinked();
		ListNode cur;
		int pos = 0;
		if (obj == null) {
			for (cur = head.link; cur != null && cur != tail; cur = cur.link, pos++)
				if (cur.data == null) return pos;
		}
		else {
			for (cur = head.link; cur != null && cur != tail; cur = cur.link, pos++)
				if (obj == cur.data || obj.equals(cur.data)) return pos;
		}
		return -1;
	}

	@Override
	public int lastIndexOf(Object obj) {
		if (!linkedUTD) updateLinked();
		ListNode cur;
		int pos = numItems - 1;
		if (obj == null) {
			for (cur = tail.prev; cur != null && cur != head; cur = cur.prev, pos--)
				if (cur.data == null) return pos;
		}
		else {
			for (cur = tail.prev; cur != null && cur != head; cur = cur.prev, pos--)
				if (obj == cur.data || obj != null && obj.equals(cur.data)) return pos;
		}
		return -1;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		boolean changed = false;

		for (ListNode cur = head.link; cur.link != tail && cur != null; cur = cur.link) {
			if (c.contains(cur.data)) {
				unlink(cur);
				changed = true;
			}
		}
		return changed;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		boolean changed = false;

		for (ListNode cur = head.link; cur != tail && cur != null; cur = cur.link) {
			if (!c.contains(cur.data)) {
				unlink(cur);
				changed = true;
			}
		}
		return changed;
	}

	@Override
	public Object[] toArray() {
		if (!arrayUTD) updateArray();
		Object[] arr = Arrays.copyOf(theArray, numItems);
		return arr; // may need to be revised.
	}

	@Override
	public <T> T[] toArray(T[] arr) {
		if (arr.length < numItems) arr = Arrays.copyOf(arr, numItems);
		System.arraycopy(toArray(), 0, arr, 0, numItems);
		if (arr.length > numItems) arr[numItems] = null;
		return arr;
	}

	@Override
	public List<E> subList(int fromPos, int toPos) {
		throw new UnsupportedOperationException();
	}

	private class AdaptiveListIterator implements ListIterator<E> {
		private int index; // index of next node;
		private ListNode cur; // node at index - 1
		private ListNode last; // node last visited by next() or previous()
		private boolean nextCall = false;

		public AdaptiveListIterator() {
			if (!linkedUTD) updateLinked();
			cur = head;
			last = null;
			index = 0;
		}

		public AdaptiveListIterator(int pos) {
			checkIndex2(pos);
			if (!linkedUTD) updateLinked();
			index = pos;
			last = null;
			if (pos == numItems) cur = tail.prev;
			else if (pos == 0) cur = head;
			else cur = findNode(pos);
		}

		@Override
		public boolean hasNext() {
			return cur.link != null;
		}

		@Override
		public E next() {
			if (!hasNext()) throw new NoSuchElementException();
			if (index >= numItems) throw new RuntimeException("index 1");
			index++;
			last = cur;
			cur = cur.link;
			nextCall = true;
			return cur.data; // cur != null
		}

		@Override
		public boolean hasPrevious() {
			return cur != null; // may need to be revised.
		}

		// Returns the element right before the the cursor position and moves it
		// backwards.
		@Override
		public E previous() {
			if (!hasPrevious()) throw new NoSuchElementException();
			if (index <= 0) throw new RuntimeException("index 2");
			index--;
			last = cur;
			cur = cur.prev;
			nextCall = false;
			return last.data;
		}

		@Override
		public int nextIndex() {
			return index;
		}

		@Override
		public int previousIndex() {
			return index - 1;
		}

		@Override
		public void remove() {
			if (last == null) // no previous call
				throw new IllegalStateException();
			if (cur == last) {
				if (index <= 0) throw new RuntimeException("index 3");
				index--;
			} // update index if cur is the last node

			if (nextCall) {
				ListNode newCur = cur.prev;
				unlink(cur);
				cur = newCur;
				index--;
			}
			else {
				unlink(last);
			}
			numItems--;
			last = null;
			arrayUTD = false;
		}

		@Override
		public void add(E obj) {
			ListNode toAdd = new ListNode(obj);

			link(cur, toAdd);
			cur = cur.link;
			

			index++;
			numItems++;
			arrayUTD = false;
		} // add

		@Override
		public void set(E obj) {
			if (last == null) throw new IllegalStateException();
			if (nextCall) cur.data = obj;
			else last.data = obj;
			arrayUTD = false;
		} // set
	} // AdaptiveListIterator

	@Override
	public boolean equals(Object obj) {
		if (!linkedUTD) updateLinked();
		if ((obj == null) || !(obj instanceof List<?>)) return false;
		List<?> list = (List<?>) obj;
		if (list.size() != numItems) return false;
		Iterator<?> iter = list.iterator();
		for (ListNode tmp = head.link; tmp != tail; tmp = tmp.link) {
			if (!iter.hasNext()) return false;
			Object t = iter.next();
			if (!(t == tmp.data || t != null && t.equals(tmp.data))) return false;
		}
		if (iter.hasNext()) return false;
		return true;
	} // equals

	@Override
	public Iterator<E> iterator() {
		return new AdaptiveListIterator();
	}

	@Override
	public ListIterator<E> listIterator() {
		return new AdaptiveListIterator();
	}

	@Override
	public ListIterator<E> listIterator(int pos) {
		checkIndex2(pos);
		return new AdaptiveListIterator(pos);
	}

	// Adopted from the List<E> interface.
	@Override
	public int hashCode() {
		if (!linkedUTD) updateLinked();
		int hashCode = 1;
		for (E e : this)
			hashCode = 31 * hashCode + (e == null ? 0 : e.hashCode());
		return hashCode;
	}

	// You should use the toString*() methods to see if your code works as expected.
	@Override
	public String toString() {
		String eol = System.getProperty("line.separator");
		return toStringArray() + eol + toStringLinked();
	}

	public String toStringArray() {
		String eol = System.getProperty("line.separator");
		StringBuilder strb = new StringBuilder();
		strb.append("A sequence of items from the most recent array:" + eol);
		strb.append('[');
		if (theArray != null) for (int j = 0; j < theArray.length;) {
			if (theArray[j] != null) strb.append(theArray[j].toString());
			else strb.append("-");
			j++;
			if (j < theArray.length) strb.append(", ");
		}
		strb.append(']');
		return strb.toString();
	}

	public String toStringLinked() {
		return toStringLinked(null);
	}

	// iter can be null.
	public String toStringLinked(ListIterator<E> iter) {
		int cnt = 0;
		int loc = iter == null ? -1 : iter.nextIndex();

		String eol = System.getProperty("line.separator");
		StringBuilder strb = new StringBuilder();
		strb.append("A sequence of items from the most recent linked list:" + eol);
		strb.append('(');
		for (ListNode cur = head.link; cur != tail;) {
			if (cur.data != null) {
				if (loc == cnt) {
					strb.append("| ");
					loc = -1;
				}
				strb.append(cur.data.toString());
				cnt++;

				if (loc == numItems && cnt == numItems) {
					strb.append(" |");
					loc = -1;
				}
			}
			else strb.append("-");

			cur = cur.link;
			if (cur != tail) strb.append(", ");
		}
		strb.append(')');
		return strb.toString();
	}
}