/**
 * 
 */
package edu.iastate.summer18.cs228.hw4;

/**
 * Implementation of ListInterface using circularly linked nodes to store the
 * data.
 * 
 * @author Robert Shay
 */
public class CLC<T> implements ListInterface<T> {
	private Node lastNode;
	private int numberOfEntries;

	public CLC() {
		initializeDataFields();
	} // end default constructor

	public void clear() {
		initializeDataFields();
	} // end clear

	// Initializes the class's data fields to indicate an empty list.
	private void initializeDataFields() {
		lastNode = null;
		numberOfEntries = 0;
	} // end initializeDataFields

	public T remove(int givenPosition) {
		T result = null; // Return value
		if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
			assert !isEmpty();
			if (givenPosition == numberOfEntries) {
				Node nodeBefore = getNodeAt(givenPosition - 1);
				result = lastNode.getData();
				nodeBefore.setNextNode(lastNode.getNextNode());
				lastNode = nodeBefore;
			}
			else if (givenPosition == 1) {
				Node nodeAfter = getNodeAt(givenPosition + 1);
				Node nodeToRemove = getNodeAt(givenPosition);
				result = nodeToRemove.getData();
				lastNode.setNextNode(nodeAfter);
			}
			else {
				Node nodeBefore = getNodeAt(givenPosition - 1);
				Node nodeToRemove = nodeBefore.getNextNode();
				result = nodeToRemove.getData(); // Save entry to be removed
				Node nodeAfter = nodeToRemove.getNextNode();
				nodeBefore.setNextNode(nodeAfter); // Remove entry
			} // end if
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
					lastNode = newNode;
					lastNode.setNextNode(lastNode);
				}
				else {
					newNode.setNextNode(lastNode.getNextNode());
					lastNode.setNextNode(newNode);
					lastNode = newNode;
				}
			}
			else if (newPosition == 1) {
				newNode.setNextNode(lastNode.getNextNode().getNextNode());
				lastNode.setNextNode(newNode);
			}
			else // Case 2: list is not empty
			{ // and newPosition > 1
				Node nodeBefore = getNodeAt(newPosition - 1);
				Node nodeAfter = nodeBefore.getNextNode();
				newNode.setNextNode(nodeAfter);
				nodeBefore.setNextNode(newNode);
			} // end if
			numberOfEntries++;
		}
		else throw new IndexOutOfBoundsException("Illegal position given to add operation.");
	} // end add

	public boolean isEmpty() {
		boolean result;

		if (numberOfEntries == 0) // Or getLength() == 0
		{
			assert lastNode == null : "numberOfEntries is 0 but firstNode is not null!";
			result = true;
		}
		else {
			assert lastNode != null : "numberOfEntries is not 0 but firstNode is null!";
			result = false;
		} // end if

		return result;
	} // end isEmpty

	public T[] toArray() {
		// The cast is safe because the new array contains null entries
		@SuppressWarnings("unchecked")
		T[] result = (T[]) new Object[numberOfEntries];

		int index = 0;
		Node currentNode = lastNode.getNextNode();
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
		Node currentNode = lastNode.getNextNode();

		// Traverse the chain to locate the desired node
		// (skipped if givenPosition is 1)
		for (int counter = 1; counter < givenPosition; counter++)
			currentNode = currentNode.getNextNode();
		assert currentNode != null;
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
		Node currentNode = lastNode.getNextNode();
		int c = 0;
		
		while (c < numberOfEntries) {
			if (anEntry.equals(currentNode.getData())) return true;
			if(currentNode.getData() != null) c++;
			currentNode = currentNode.getNextNode();
		} // end while

		return false;
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
		return lastNode.getNextNode().getData();
	}

	@Override
	public T getLast() {
		return lastNode.getData();
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
		ListInterface<T> newList = new CLC<T>();
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
}
