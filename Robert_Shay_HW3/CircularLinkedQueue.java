/**
 * 
 */
package edu.iastate.summer18.cs228.hw3;

/**
 * Implementation of queue interface using a circular linked node.
 * 
 * @author Robert Shay
 */
public class CircularLinkedQueue<T> implements QueueInterface<T> {
	private Node lastNode; // references last node in queue

	/**
	 * Constructs a new circular linked queue.
	 */
	public CircularLinkedQueue() {
		lastNode = null;
	}

	@Override
	public void enqueue(T newEntry) {
		Node newNode = new Node(newEntry);

		if (lastNode != null) {
			if (lastNode.getNextNode() == null) {
				lastNode.setNextNode(newNode);
				newNode.setNextNode(lastNode);
			}
			else {
				newNode.setNextNode(lastNode.getNextNode());
			}
		}
		lastNode = newNode;
	}

	@Override
	public T dequeue() {
		T returned;

		if (isEmpty()) {
			return null;
		}
		else if (lastNode.getNextNode().equals(lastNode)) {
			returned = lastNode.getData();
			lastNode = null;
		}
		else {
			returned = lastNode.getNextNode().getData();
			lastNode.setNextNode(lastNode.getNextNode().getNextNode());
		}
		return returned;
	}

	@Override
	public T getFront() {
		return lastNode.getNextNode().getData();
	}

	@Override
	public boolean isEmpty() {
		if (lastNode == null || lastNode.next == null) return true;
		else return false;
	}

	@Override
	public void clear() {
		lastNode = null;
	}

	private class Node {
		private T data; // Queue entry
		private Node next; // Link to next node

		private Node(T dataPortion) {
			data = dataPortion;
			next = null;
		} // end constructor

		private Node(T dataPortion, Node linkPortion) {
			data = dataPortion;
			next = linkPortion;
		} // end constructor

		private T getData() {
			return data;
		} // end getData

		private void setData(T newData) {
			data = newData;
		} // end setData

		private Node getNextNode() {
			return next;
		} // end getNextNode

		private void setNextNode(Node nextNode) {
			next = nextNode;
		} // end setNextNode
	} // end Node
}
