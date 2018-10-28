/**
 * 
 */
package edu.iastate.summer18.cs228.hw3;

/**
 * Implementation of the deque interface using a circular doubly linked node.
 * @author Robert Shay
 */
public class CircularDoublyLinkedDeque<T> implements DequeInterface<T> {
	DoublyLinkedNode firstNode; // Reference to the front of the deque.
	int numberOfEntries = 0; // Keeps track of the number of entries;

	/** Contructor for CircularDoublyLinkedDeque */
	public CircularDoublyLinkedDeque() {
	}

	@Override
	public void addToFront(T newEntry) {
		DoublyLinkedNode newNode = new DoublyLinkedNode(newEntry);
		if (numberOfEntries == 0) ;
		else if (numberOfEntries == 1) {
			newNode.next = firstNode;
			newNode.prev = firstNode.prev;
		}
		else if (firstNode != null) {
			newNode.next = firstNode;
			newNode.prev = getLast();
		}
		firstNode = newNode;

		numberOfEntries++;
	}

	@Override
	public void addToBack(T newEntry) {
		DoublyLinkedNode newNode = new DoublyLinkedNode(newEntry);
		DoublyLinkedNode last = getLast();

		if (firstNode == null) {
			numberOfEntries--;
			addToFront(newEntry);
		}
		else {
			newNode.prev = last;
			firstNode.prev = newNode;
			newNode.next = last.next;
			last.next = newNode;
		}
		numberOfEntries++;
	}

	@Override
	public T removeFront() {
		if (isEmpty()) throw new EmptyQueueException();
		T temp = firstNode.data;

		if (firstNode.next == null) {
			clear();
			return temp;
		}
		else {
			DoublyLinkedNode newNode = firstNode.next;
			DoublyLinkedNode last = getLast();

			newNode.prev = last;
			firstNode = newNode;
			last.next = firstNode;

			numberOfEntries--;
			return temp;
		}
	}

	@Override
	public T removeBack() {
		if (isEmpty()) throw new EmptyQueueException();
		T temp;
		if (numberOfEntries == 1) {
			return removeFront();
		}
		else {
			DoublyLinkedNode lastNode = getSecondLast();
			temp = lastNode.next.data;

			firstNode.prev = lastNode;
			lastNode.next = firstNode;

			numberOfEntries--;
		}

		return temp;
	}

	@Override
	public T getFront() {
		return firstNode.data;
	}

	@Override
	public T getBack() {
		return firstNode.prev.data;
	}

	@Override
	public boolean isEmpty() {
		return numberOfEntries == 0;
	}

	@Override
	public void clear() {
		firstNode = null;
		firstNode.data = null;
		firstNode.next = null;
		firstNode.prev = null;
		numberOfEntries = 0;
	}

	/**
	 * Gets the last node in the chain.
	 * 
	 * @return The last node.
	 */
	private DoublyLinkedNode getLast() {
		DoublyLinkedNode last = firstNode;

		for (int i = 0; i < numberOfEntries - 1; i++) {
			last = last.next;
		}

		return last;
	}

	/**
	 * Gets the second to last node in the chain.
	 * 
	 * @return The second to last node.
	 */
	private DoublyLinkedNode getSecondLast() {
		DoublyLinkedNode last = firstNode;

		for (int i = 0; i < numberOfEntries - 2; i++) {
			last = last.next;
		}

		return last;
	}

	private class DoublyLinkedNode {
		private T data; // Entry in bag
		private DoublyLinkedNode next; // Link to next node
		private DoublyLinkedNode prev; // Link to previous node

		private DoublyLinkedNode(T dataPortion) {
			this(dataPortion, null, null);
		} // end constructor

		private DoublyLinkedNode(T dataPortion, DoublyLinkedNode nextNode, DoublyLinkedNode prevNode) {
			data = dataPortion;
			next = nextNode;
			prev = prevNode;
		} // end constructor

	}

}
