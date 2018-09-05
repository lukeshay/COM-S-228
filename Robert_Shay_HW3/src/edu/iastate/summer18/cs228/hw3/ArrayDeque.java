/**
 * 
 */
package edu.iastate.summer18.cs228.hw3;

import java.util.Arrays;

/**
 * Implementation of deque interface using an array to store the data.
 * 
 * @author Robert Shay
 */
@SuppressWarnings("unchecked")
public class ArrayDeque<T> implements DequeInterface<T> {

	private int numberOfEntries = 0;
	private T[] deque;
	private static int DEFAULT_CAPACITY = 25;

	/**
	 * Constructs a new ArrayDeque with the default capacity.
	 */
	public ArrayDeque() {
		this(DEFAULT_CAPACITY);
	}

	/**
	 * Constructs a ArrayDeque with the inputed capacity.
	 * 
	 * @param capacity
	 */
	public ArrayDeque(int capacity) {
		deque = (T[]) new Object[capacity];
	}

	@Override
	public void addToFront(T newEntry) {
		moveBackward();
		deque[0] = newEntry;
		numberOfEntries++;
		doubleCapacity();
	}

	@Override
	public void addToBack(T newEntry) {
		deque[numberOfEntries] = newEntry;
		numberOfEntries++;
		doubleCapacity();
	}

	@Override
	public T removeFront() {
		if (numberOfEntries > 0) {
			T temp = deque[0];
			moveForward();
			numberOfEntries--;
			return temp;
		}
		return null;
	}

	@Override
	public T removeBack() {
		if (numberOfEntries > 0) {
			T temp = deque[numberOfEntries];
			deque[numberOfEntries] = null;
			numberOfEntries--;
			return temp;
		}
		return null;
	}

	@Override
	public T getFront() {
		if (numberOfEntries > 0) return deque[0];
		else return null;
	}

	@Override
	public T getBack() {
		if (numberOfEntries > 0) return deque[numberOfEntries - 1];
		else return null;
	}

	@Override
	public boolean isEmpty() {
		return numberOfEntries == 0;
	}

	@Override
	public void clear() {
		deque = (T[]) new Object[DEFAULT_CAPACITY];
		numberOfEntries = 0;
	}

	/**
	 * Doubles the capacity of the array if needed.
	 */
	private void doubleCapacity() {
		if (numberOfEntries == deque.length) {
			deque = Arrays.copyOf(deque, deque.length * 2);
		}
	}

	/**
	 * Moves all of the objects one spot forward in the array.
	 */
	private void moveForward() {
		for (int i = 0; i < numberOfEntries - 1; i++) {
			deque[i] = deque[i + 1];
		}
	}

	/**
	 * Moves all of the objects one spot backward in the array.
	 */
	private void moveBackward() {
		for (int i = numberOfEntries; i > 0; i--) {
			deque[i] = deque[i - 1];
		}
	}

}
