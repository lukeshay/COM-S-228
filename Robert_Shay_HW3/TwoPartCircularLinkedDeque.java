/**
 * 
 */
package edu.iastate.summer18.cs228.hw3;

/**
 * Implementation of the deque interface using two part circular linked node.
 * 
 * @author Robert Shay
 */
public class TwoPartCircularLinkedDeque<T> implements DequeInterface<T> {
	DoublyLinkedNode firstNode;
	DoublyLinkedNode lastNode;

	/** Constructor for a two part circular linked deque */
	public TwoPartCircularLinkedDeque() {
	}

	@Override
	public void addToFront(T newEntry) {
		DoublyLinkedNode newNode = new DoublyLinkedNode(newEntry);

		if (firstNode != null) {
			newNode.next = firstNode;
			firstNode.prev = newNode;
		}
		firstNode = newNode;
		firstNode.prev = lastNode;

		if (lastNode == null) lastNode = firstNode;

	}

	@Override
	public void addToBack(T newEntry) {
		DoublyLinkedNode newNode = new DoublyLinkedNode(newEntry);

		if (lastNode != null) {
			newNode.prev = lastNode;
			lastNode.next = newNode;
		}

		lastNode = newNode;
		lastNode.next = firstNode;

		if (firstNode == null) firstNode = lastNode;

	}

	@Override
	public T removeFront() {
		if (isEmpty()) throw new EmptyQueueException();

		T removed = firstNode.data;
		firstNode = firstNode.next;

		if (firstNode == null) lastNode = null;
		else firstNode.prev = null;

		return removed;
	}

	@Override
	public T removeBack() {
		if (isEmpty()) throw new EmptyQueueException();

		T removed = lastNode.data;
		lastNode = lastNode.prev;

		if (lastNode == null) firstNode = null;
		else lastNode.next = null;

		return removed;
	}

	@Override
	public T getFront() {
		return firstNode.data;
	}

	@Override
	public T getBack() {
		return lastNode.data;
	}

	@Override
	public boolean isEmpty() {
		return firstNode == null;
	}

	@Override
	public void clear() {
		firstNode = null;
		lastNode = null;
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
