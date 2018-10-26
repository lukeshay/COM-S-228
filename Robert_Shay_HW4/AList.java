package edu.iastate.summer18.cs228.hw4;
/**
 * A class that implements the ADT list by using a resizable array. Entries in a
 * list have positions that begin with 1. Duplicate entries are allowed.
 * 
 * @author Frank M. Carrano
 * @author Timothy M. Henry
 * @author Robert Shay
 * @version 4.0
 */

/**
 * A class that implements the ADT list by using a resizable array. Entries in a
 * list have positions that begin with 1. Duplicate entries are allowed.
 */

import java.util.Arrays;

public class AList<T> implements ListInterface<T> {
	private T[] list; // Array of list entries; ignore list[0]
	private int numberOfEntries;
	private boolean initialized = false;
	private static final int DEFAULT_CAPACITY = 5;
	private static final int MAX_CAPACITY = 10000;

	public AList() {
		this(DEFAULT_CAPACITY);
	} // end default constructor

	public AList(int initialCapacity) {
		// Is initialCapacity too small?
		if (initialCapacity < DEFAULT_CAPACITY) initialCapacity = DEFAULT_CAPACITY;
		else // Is initialCapacity too big?
			checkCapacity(initialCapacity);

		// The cast is safe because the new array contains null entries
		@SuppressWarnings("unchecked")
		T[] tempList = (T[]) new Object[initialCapacity + 1];
		list = tempList;
		numberOfEntries = 0;
		initialized = true;
	} // end constructor

	public int getSize() {
		return list.length;
	}

	public void add(T newEntry) {
		checkInitialization();
		list[numberOfEntries + 1] = newEntry;
		numberOfEntries++;
		ensureCapacity();
		// add(numberOfEntries + 1, newEntry); // ALTERNATE CODE
	} // end add

	public void clear() {
		for (int index = 1; index <= numberOfEntries; index++) {
			list[index] = null;
		} // end for

		numberOfEntries = 0;
	} // end clear

	public T[] toArray() {
		checkInitialization();

		// The cast is safe because the new array contains null entries
		@SuppressWarnings("unchecked")
		T[] result = (T[]) new Object[numberOfEntries]; // Unchecked cast
		for (int index = 0; index < numberOfEntries; index++) {
			result[index] = list[index + 1];
		} // end for
		return result;
	} // end toArray

	public int getLength() {
		return numberOfEntries;
	} // end getLength

	public boolean isEmpty() {
		return numberOfEntries == 0; // Or getLength() == 0
	} // end isEmpty

	// Doubles the capacity of the array list if it is full.
	// Precondition: checkInitialization has been called.
	private void ensureCapacity() {
		int capacity = list.length - 1;
		if (numberOfEntries >= capacity) {
			int newCapacity = 2 * capacity;
			checkCapacity(newCapacity); // Is capacity too big?
			list = Arrays.copyOf(list, newCapacity + 1);
		} // end if
	} // end ensureCapacity

	/*
	 * < This class will define checkCapacity, checkInitialization, and two more
	 * private methods that will be discussed later. >
	 */
	private void checkInitialization() {
		if (!initialized) throw new SecurityException("AList object is not initialized properly!");
	}

	private void checkCapacity(int capacity) {
		if (capacity > MAX_CAPACITY) throw new IllegalStateException(
				"Attempt to create a list whose capacity " + "exceeds allowed maximum of " + MAX_CAPACITY);
	}

	// Precondition: The array list has room for another entry.
	public void add(int newPosition, T newEntry) {
		checkInitialization();
		if ((newPosition >= 1) && (newPosition <= numberOfEntries + 1)) {
			if (newPosition <= numberOfEntries) makeRoom(newPosition);
			list[newPosition] = newEntry;
			numberOfEntries++;
			ensureCapacity(); // Ensure enough room for next add
		}
		else throw new IndexOutOfBoundsException("Given position of add's new entry is out of bounds.");
	} // end add

	// Makes room for a new entry at newPosition.
	// Precondition: 1 <= newPosition <= numberOfEntries + 1;
	// numberOfEntries is list's length before addition;
	// checkInitialization has been called.
	private void makeRoom(int newPosition) {
		assert (newPosition >= 1) && (newPosition <= numberOfEntries + 1);
		int newIndex = newPosition;
		int lastIndex = numberOfEntries;
		// Move each entry to next higher index, starting at end of
		// list and continuing until the entry at newIndex is moved
		for (int index = lastIndex; index >= newIndex; index--)
			list[index + 1] = list[index];
	} // end makeRoom

	public T remove(int givenPosition) {
		checkInitialization();
		if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
			assert !isEmpty();
			T result = list[givenPosition]; // Get entry to be removed
			// Move subsequent entries towards entry to be removed,
			// unless it is last in list
			if (givenPosition < numberOfEntries) removeGap(givenPosition);
			numberOfEntries--;
			return result;
		}
		else throw new IndexOutOfBoundsException("Illegal position given to remove operation.");
	} // end remove

	// Shifts entries that are beyong the entry to be removed to the
	// next lower position.
	// Precondition: 1 <= givenPosition < numberOfEntries;
	// numberOfEntries is list's length before removal;
	// checkInitialization has been called.
	private void removeGap(int givenPosition) {
		assert (givenPosition >= 1) && (givenPosition < numberOfEntries);

		int removedIndex = givenPosition;
		int lastIndex = numberOfEntries;
		for (int index = removedIndex; index < lastIndex; index++)
			list[index] = list[index + 1];
	}

	public T replace(int givenPosition, T newEntry) {
		checkInitialization();
		if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
			assert !isEmpty();
			T originalEntry = list[givenPosition];
			list[givenPosition] = newEntry;
			return originalEntry;
		}
		else throw new IndexOutOfBoundsException("Illegal position given to replace operation.");
	} // end replace

	public T getEntry(int givenPosition) {
		checkInitialization();
		if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
			assert !isEmpty();
			return list[givenPosition];
		}
		else throw new IndexOutOfBoundsException("Illegal position given to getEntry operation.");
	} // end getEntry

	public boolean contains(T anEntry) {
		checkInitialization();
		boolean found = false;
		int index = 1;
		while (!found && (index <= numberOfEntries)) {
			if (anEntry.equals(list[index])) found = true;
			index++;
		} // end while
		return found;
	} // end contains

	@Override
	public void addFirst(T newEntry) {
		add(1, newEntry);
	}

	@Override
	public void addLast(T newEntry) {
		add(numberOfEntries + 1, newEntry);

	}

	@Override
	public T removeFirst() {
		return remove(1);
	}

	@Override
	public T removeLast() {
		return remove(numberOfEntries);
	}

	@Override
	public T getFirst() {
		return getEntry(1);
	}

	@Override
	public T getLast() {
		return getEntry(numberOfEntries);
	}

	@Override
	public void place(int i, int j) {
		if (Math.max(i, j) <= numberOfEntries && !isEmpty() && Math.min(i, j) > 0) {
			add(j, remove(i));
		}
		else throw new IndexOutOfBoundsException("One of the inputed integers is out of bounds. ");
	}

	@Override
	public void swap(int i, int j) {
		if (Math.max(i, j) <= numberOfEntries && !isEmpty() && Math.min(i, j) > 0) {
			if (i == j) ;
			else {
				T tempI = getEntry(i);
				T tempJ = getEntry(j);

				replace(i, tempJ);
				replace(j, tempI);
			}
		}
		else throw new IndexOutOfBoundsException("One of the inputed integers is out of bounds. ");
	}

	@Override
	public ListInterface<T> subList(int start, int end) {
		ListInterface<T> newList = new AList<T>();
		if (start > end) throw new IllegalArgumentException("Start cannot be greater than end.");
		else if (Math.min(start, end) < 1 || Math.max(start, end) > getLength())
			throw new IndexOutOfBoundsException("One of the inputed indexes is out of bounds.");
		else for (int i = start; i <= end; i++)
			newList.add(getEntry(i));

		return newList;
	}

	@Override
	public void addAll(int index, ListInterface<T> items) {
		if (index < 1 || index > numberOfEntries + 1) throw new IndexOutOfBoundsException("Index is out of bounds.");
		else if (items != null) {
			for (int i = 1; i <= items.getLength(); i++) {
				add(i + index - 1, items.getEntry(i));
			}
		}
	}

	@Override
	public int nthIndexOf(int n, T anObject) {
		int c = 0;
		int i = 1;
		int r = -1;

		if (n < 1 || n > getLength()) throw new IndexOutOfBoundsException("Index is out of bounds.");
		else {
			while (i <= numberOfEntries) {
				if (getEntry(i).equals(anObject)) c++;
				if (c == n) {
					r = i;
					break;
				}
				i++;
			}
		}
		return r;
	}

	@Override
	public boolean removeNth(int n, T anObject) {
		int c = 0;
		int i = 0;

		if (n < 1 || n > getLength()) throw new IndexOutOfBoundsException("Index is out of bounds.");
		else {
			while (i < numberOfEntries) {
				i++;
				if (getEntry(i).equals(anObject)) c++;
				if (c == n) {
					remove(i);
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public int getFrequencyOf(T anObject) {
		int c = 0;
		for (int i = 1; i <= numberOfEntries; i++)
			if (getEntry(i).equals(anObject)) c++;
		return c;
	}

	@Override
	public String toString() {
		String str = "[";
		if (numberOfEntries == 0) str += " ]";
		else {
			str += getEntry(1);
			for (int i = 2; i <= numberOfEntries; i++) {
				str += ", " + getEntry(i);
			}
			str += "]";
		}
		return str;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object o) {
		try {
			ListInterface<T> compared = (ListInterface<T>) o;
			if (getLength() != compared.getLength()) return false;
			else {
				for (int i = 1; i <= numberOfEntries; i++)
					if (!getEntry(i).equals(compared.getEntry(i))) return false;
				return true;
			}
		}
		catch (ClassCastException ex) { // If the inputed object is not a list, it will return false.
			return false;
		}
	}
} // end AList