/**
 * 
 */
package edu.iastate.summer18.cs228.hw4;

/**
 * Implementation of ListInterface using a two part circularly linked chain.
 * 
 * @author Robert Shay
 */
public class TPCLC<T> implements ListInterface<T> {
	private Node queueNode;
	private Node freeNode;
	private int numberOfEntries;

	public TPCLC() {
		initializeDataFields();
	} // end default constructor

	public void clear() {
		initializeDataFields();
	} // end clear

	// Initializes the class's data fields to indicate an empty list.
	private void initializeDataFields() {
		queueNode = null;
		freeNode = null;
		numberOfEntries = 0;
	} // end initializeDataFields

	public T remove(int givenPosition) {
		T result = null; // Return value
		if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
			assert !isEmpty();
			if (givenPosition == 1) {
				result = queueNode.getData();
				queueNode.setData(null);
				queueNode = getNodeAt(1);
			}
			else {
				Node nodeToRemove = getNodeAt(givenPosition);
				result = nodeToRemove.getData();
				nodeToRemove.setData(null);
			}
			numberOfEntries--; // Update count
			return result; // Return removed entry
		}
		else throw new IndexOutOfBoundsException("Illegal position given to remove operation.");
	} // end remove

	public void add(T newEntry) {
		add(numberOfEntries + 1, newEntry);
	} // end add

	public void add(int newPosition, T newEntry) {
		if ((newPosition >= 1) && (newPosition <= numberOfEntries + 1)) {
			Node newNode = new Node(newEntry);
			if (newPosition == numberOfEntries + 1) // Case 1
			{
				if (isEmpty()) {
					Node emptyNode = new Node(null);
					newNode.setNextNode(emptyNode);
					emptyNode.setNextNode(newNode);
					queueNode = newNode;
					freeNode = emptyNode;
				}
				else {
					Node nodeBefore = getNodeAt(numberOfEntries);
					if (nodeBefore.getNextNode() != freeNode && nodeBefore.getNextNode().getData() == null)
						nodeBefore.getNextNode().setData(newEntry);
					else {
						newNode.setNextNode(nodeBefore.getNextNode());
						nodeBefore.setNextNode(newNode);
					}
				}
			}
			else if (newPosition == 1) {
				if (isEmpty()) {
					Node emptyNode = new Node(null);
					newNode.setNextNode(emptyNode);
					emptyNode.setNextNode(newNode);
					queueNode = newNode;
					freeNode = emptyNode;
				}
				else {
					freeNode.setNextNode(newNode);
					newNode.setNextNode(queueNode);
					queueNode = newNode;
				}
			}
			else // Case 2: list is not empty
			{ // and newPosition > 1
				Node nodeBefore = getNodeAt(newPosition - 1);
				Node nodeAfter = nodeBefore.getNextNode();
				if (nodeAfter.getData() == null) {
					nodeAfter.setData(newEntry);
				}
				else {
					newNode.setNextNode(nodeAfter);
					nodeBefore.setNextNode(newNode);
				}
			} // end if
			numberOfEntries++;
		}
		else throw new IndexOutOfBoundsException("Illegal position given to add operation.");
	} // end add

	public boolean isEmpty() {
		boolean result;

		if (numberOfEntries == 0) // Or getLength() == 0
		{
			assert queueNode == null : "numberOfEntries is 0 but firstNode is not null!";
			result = true;
		}
		else {
			assert queueNode != null : "numberOfEntries is not 0 but firstNode is null!";
			result = false;
		} // end if

		return result;
	} // end isEmpty

	public T[] toArray() {
		// The cast is safe because the new array contains null entries
		@SuppressWarnings("unchecked")
		T[] result = (T[]) new Object[numberOfEntries];

		int index = 0;
		Node currentNode = queueNode.getNextNode();
		while ((index < numberOfEntries) && (currentNode != null)) {
			result[index] = currentNode.getData();
			currentNode = currentNode.getNextNode();
			index++;
		} // end while

		return result;
	} // end toArray

	// Returns a reference to the node at a given position.
	// Precondition: The chain is not empty;
	// 1 <= givenPosition <= numberOfEntries.
	private Node getNodeAt(int givenPosition) {
		assert !isEmpty() && (1 <= givenPosition) && (givenPosition <= numberOfEntries);
		Node currentNode = queueNode;

		// Traverse the chain to locate the desired node
		// (skipped if givenPosition is 1)
		int counter = 1;
		while (counter < givenPosition) {
			currentNode = currentNode.getNextNode();
			if (currentNode.getData() != null) counter++;
		}
		assert currentNode.getData() != null;
		return currentNode;
	} // end getNodeAt

	private class Node {
		private T data; // Entry in list
		private Node next; // Link to next node

		private Node(T dataPortion) {
			data = dataPortion;
			next = null;
		} // end constructor

		private Node(T dataPortion, Node nextNode) {
			data = dataPortion;
			next = nextNode;
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

	public T replace(int givenPosition, T newEntry) {
		if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
			assert !isEmpty();
			Node desiredNode = getNodeAt(givenPosition);
			T originalEntry = desiredNode.getData();
			desiredNode.setData(newEntry);
			return originalEntry;
		}
		else throw new IndexOutOfBoundsException("Illegal position given to replace operation.");
	} // end replace

	public T getEntry(int givenPosition) {
		if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
			assert !isEmpty();
			return getNodeAt(givenPosition).getData();
		}
		else throw new IndexOutOfBoundsException("Illegal position given to getEntry operation.");
	} // end getEntry

	public boolean contains(T anEntry) {
		boolean found = false;
		Node currentNode = queueNode.getNextNode();
		int c = 0;

		while (!found && c < numberOfEntries) {
			if (anEntry.equals(currentNode.getData())) found = true;
			else {
				if (currentNode.getData() != null) c++;
				currentNode = currentNode.getNextNode();
			}
		} // end while
		return found;
	} // end contains

	public int getLength() {
		return numberOfEntries;
	}

	@Override
	public void addFirst(T newEntry) {
		add(1, newEntry);
	}

	@Override
	public void addLast(T newEntry) {
		add(newEntry);

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
		return queueNode.getData();
	}

	@Override
	public T getLast() {
		return getNodeAt(numberOfEntries).getData();
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
		ListInterface<T> newList = new TPCLC<T>();
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
		if (n < 1 || n > getLength()) throw new IndexOutOfBoundsException("Index is out of bounds.");
		else {
			int index = nthIndexOf(n, anObject);
			if (index != -1) {
				remove(index);
				return true;
			}
			else return false;
		}
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
}
