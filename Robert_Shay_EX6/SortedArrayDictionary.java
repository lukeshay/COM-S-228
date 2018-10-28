package edu.iastate.summer18.cs228.ex6;
/**
 * A class that implements the ADT dictionary by using a resizable sorted array.

 * The dictionary has distinct search keys.
 * 
 * @author Frank M. Carrano
 * @author Timothy M. Henry
 * @author Robert Shay
 * @version 4.0
 */

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SortedArrayDictionary<K extends Comparable<? super K>, V> implements DictionaryInterface<K, V> {
	public static void main(String[] args) {
		SortedArrayDictionary<Integer, String> dict = new SortedArrayDictionary<>();
		dict.add(1, "One");
		dict.add(2, "Two");
		dict.add(0, "Zero");
		dict.add(4, "Four");
		dict.add(3, "Three");
		for (int i = 0; i < dict.numberOfEntries; i++)
			System.out.println(dict.locateIndex(i));
	}

	private Entry<K, V>[] dictionary; // Array of entries sorted by search key
	private int numberOfEntries;
	private boolean initialized = false;
	private final static int DEFAULT_CAPACITY = 25;
	private static final int MAX_CAPACITY = 10000;

	public SortedArrayDictionary() {
		this(DEFAULT_CAPACITY); // Call next constructor
	} // end default constructor

	public SortedArrayDictionary(int initialCapacity) {
		checkCapacity(initialCapacity);
		// The cast is safe because the new array contains null entries
		@SuppressWarnings("unchecked")
		Entry<K, V>[] tempDictionary = (Entry<K, V>[]) new Entry[initialCapacity];
		dictionary = tempDictionary;
		numberOfEntries = 0;
		initialized = true;
	} // end constructor

	private void checkInitialization() {
		if (!initialized) throw new RuntimeException("ArrayDictionary is not initialized.");
	}

	private void checkCapacity(int givenCap) {
		if (givenCap > MAX_CAPACITY || givenCap < 0) throw new RuntimeException("Inputted capacity is invalid.");
	}

	private void ensureCapacity() {
		if (dictionary.length <= numberOfEntries) throw new RuntimeException("Not enough space.");
	}

	/** Precondition: key and value are not null. */
	public V add(K key, V value) {
		checkInitialization();
		if ((key == null) || (value == null)) throw new IllegalArgumentException("Cannot add null to a dictionary.");
		else {
			V result = null;
			int keyIndex = locateIndex(key);

			if ((keyIndex < numberOfEntries) && key.equals(dictionary[keyIndex].getKey())) {
				// Key found, return and replace entry's value
				result = dictionary[keyIndex].getValue(); // Old value
				dictionary[keyIndex].setValue(value); // Replace value
			}
			else // Key not found; add new entry to dictionary
			{
				makeRoom(keyIndex); // Make room for new entry
				dictionary[keyIndex] = new Entry<>(key, value); // Insert new entry in array
				numberOfEntries++;
				ensureCapacity(); // Ensure enough room for next add
			} // end if

			return result;
		} // end if
	} // end add

	// Returns the index of either the entry that contains key or
	// the location that should contain key, if no such entry exists.
	private int locateIndex(K key) {
		return binarySearch(dictionary, 0, numberOfEntries - 1, key);
	} // end locateIndex

	private int binarySearch(Entry<K, V>[] arr, int l, int r, K x) {
		if (r >= l) {
			int mid = l + (r - l) / 2;

			// If the element is present at the
			// middle itself
			if (arr[mid].getKey().compareTo(x) == 0) return mid;

			// If element is smaller than mid, then
			// it can only be present in left subarray
			if (arr[mid].getKey().compareTo(x) == 1) return binarySearch(arr, l, mid - 1, x);

			// Else the element can only be present
			// in right subarray
			return binarySearch(arr, mid + 1, r, x);
		}

		// We reach here when element is not present
		// in array
		return l;
	}

	// Makes room for a new entry at a given index by shifting
	// array entries towards the end of the array.
	// Precondition: keyIndex is valid; list length is old length.
	private void makeRoom(int keyIndex) {
		if (keyIndex == numberOfEntries) ;
		else for (int i = numberOfEntries - 1; i >= keyIndex; i--)
			dictionary[i + 1] = dictionary[i];
	} // end makeRoom

	// Removes an entry at a given index by shifting array
	// entries toward the entry to be removed.
	private void removeArrayEntry(int keyIndex) {
		if (keyIndex == numberOfEntries - 1) dictionary[keyIndex] = null;
		else for (int i = keyIndex; i < numberOfEntries; i++) {
			dictionary[i] = dictionary[i + 1];

			dictionary[numberOfEntries] = null;
		}
	} // end removeArrayEntry

	private class Entry<S, T> {
		private S key;
		private T value;

		private Entry(S searchKey, T dataValue) {
			key = searchKey;
			value = dataValue;
		} // end constructor

		private S getKey() {
			return key;
		} // end getKey

		private T getValue() {
			return value;
		} // end getValue

		private void setValue(T dataValue) {
			value = dataValue;
		} // end setValue
	} // end Entry

	/*
	 * (non-Javadoc)
	 * @see
	 * edu.iastate.summer18.cs228.ex6.DictionaryInterface#remove(java.lang.Object)
	 */
	@Override
	public V remove(K key) {
		int index = locateIndex(key);
		V returned = dictionary[index].getValue();
		removeArrayEntry(index);
		return returned;
	}

	@Override
	public V getValue(K key) {
		return dictionary[locateIndex(key)].getValue();
	}

	@Override
	public boolean contains(K key) {
		return locateIndex(key) < numberOfEntries;
	}

	@Override
	public Iterator<K> getKeyIterator() {
		@SuppressWarnings("unchecked")
		K[] keys = (K[]) new Object[numberOfEntries];
		int i = 0;
		for (Entry<K, V> e : dictionary) {
			keys[i] = e.getKey();
			i++;
		}

		Iterator<K> itr = Arrays.asList(keys).iterator();

		return itr;
	}

	@Override
	public Iterator<V> getValueIterator() {
		@SuppressWarnings("unchecked")
		V[] values = (V[]) new Object[numberOfEntries];
		int i = 0;
		for (Entry<K, V> e : dictionary) {
			values[i] = e.getValue();
			i++;
		}

		Iterator<V> itr = Arrays.asList(values).iterator();

		return itr;
	}

	@Override
	public boolean isEmpty() {
		return dictionary[0] == null;
	}

	@Override
	public int getSize() {
		return numberOfEntries;
	}

	@Override
	public void clear() {
		@SuppressWarnings("unchecked")
		Entry<K, V>[] tempDictionary = (Entry<K, V>[]) new Entry[DEFAULT_CAPACITY];
		dictionary = tempDictionary;
		numberOfEntries = 0;
		initialized = true;

	}
} // end SortedArrayDictionary