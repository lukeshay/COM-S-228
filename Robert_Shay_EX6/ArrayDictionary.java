package edu.iastate.summer18.cs228.ex6;
/**
 * A class that implements the ADT dictionary by using a resizable array. The
 * dictionary is unsorted and has distinct search keys.
 * 
 * @author Frank M. Carrano
 * @author Timothy M. Henry
 * @author Robert Shay
 * @version 4.0
 */

import java.util.Arrays;
import java.util.Iterator;

public class ArrayDictionary<K, V> implements DictionaryInterface<K, V> {
	private Entry<K, V>[] dictionary; // Array of unsorted entries
	private int numberOfEntries;
	private boolean initialized = false;
	private final static int DEFAULT_CAPACITY = 25;
	private static final int MAX_CAPACITY = 10000;

	public ArrayDictionary() {
		this(DEFAULT_CAPACITY); // Call next constructor
	} // end default constructor

	public ArrayDictionary(int initialCapacity) {
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

	public V add(K key, V value) {
		checkInitialization();
		if ((key == null) || (value == null)) throw new IllegalArgumentException("Cannot add null to a dictionary.");
		else {
			V result = null;
			int keyIndex = locateIndex(key);
			if (keyIndex < numberOfEntries) {
				// Key found, return and replace entry's value
				result = dictionary[keyIndex].getValue(); // Get old value
				dictionary[keyIndex].setValue(value); // Replace value
			}
			else // Key not found; add new entry to dictionary
			{
				// Add at end of array
				dictionary[numberOfEntries] = new Entry<>(key, value);
				numberOfEntries++;
				ensureCapacity(); // Ensure enough room for next add
			} // end if
			return result;
		} // end if
	} // end add

	// Returns the array index of the entry that contains key, or
	// returns numberOfEntries if no such entry exists.
	private int locateIndex(K key) {
		// Sequential search
		int index = 0;
		while ((index < numberOfEntries) && !key.equals(dictionary[index].getKey()))
			index++;
		return index;
	} // end locateIndex

	public V remove(K key) {
		checkInitialization();
		V result = null;
		int keyIndex = locateIndex(key);
		if (keyIndex < numberOfEntries) {
			// Key found; remove entry and return its value
			result = dictionary[keyIndex].getValue();
			dictionary[keyIndex] = dictionary[numberOfEntries - 1];
			dictionary[numberOfEntries - 1] = null;
			numberOfEntries--;
		} // end if
			// Else result is null
		return result;
	} // end remove

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

	@Override
	public V getValue(K key) {
		for (Entry<K, V> e : dictionary) {
			if (e.getKey().equals(key)) return e.getValue();
		}
		return null;
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
} // end ArrayDictionary
