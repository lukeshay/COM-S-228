package edu.iastate.summer18.cs228.hw2;

/**
 * Implementation of the BagInterface that uses doubly linked nodes to store the
 * data.
 * 
 * @author Robert Shay
 */
public class DoublyLinkedBag<T> implements BagInterface<T> {

	/**
	 * Holds the node that is first in the bag.
	 */
	private DoublyLinkedNode firstNode;

	/**
	 * Keeps track of the number of entries.
	 */
	private int numberOfEntries;

	/**
	 * Constructor for a doubly linked bag. Sets first node equals to null and
	 * number of entries equal to 0.
	 */
	public DoublyLinkedBag() {
		firstNode = null;
		numberOfEntries = 0;
	} // End of Doubly Linked Bag

	@Override
	public int getCurrentSize() {
		return numberOfEntries;
	} // End of get current size.

	@Override
	public boolean isEmpty() {
		return numberOfEntries <= 0;
	} // End of is empty.

	@Override
	public boolean add(T newEntry) {
		// Creates a new node that has the data given.
		DoublyLinkedNode newNode = new DoublyLinkedNode(newEntry);
		// If the number of entries is greater than one, firstNode.prev is set equal to
		// firstNode because this node will become firstNode.next.
		if (numberOfEntries > 1) firstNode.prev = firstNode;

		newNode.next = firstNode; // Sets newNode.next equal to firstNode. This creates a chain of nodes that
									// includes all of the inputed data.

		firstNode = newNode; // First node is set equal to the newNode.
		numberOfEntries++; // Number of entries is increased by one.

		return true;
	} // End of add.

	@Override
	public T remove() {
		T result = null;
		if (firstNode != null) {
			result = firstNode.data;
			firstNode = firstNode.next; // Remove first node from chain.
			numberOfEntries--;
		} // end if.

		return result;
	}// End of remove

	@Override
	public boolean remove(T anEntry) {
		boolean result = false;
		DoublyLinkedNode nodeN = find(anEntry); // Finds the node with the given entry

		if (nodeN != null) {
			nodeN.data = firstNode.data; // Replace located entry with entry in first node

			firstNode = firstNode.next; // Remove first node
			numberOfEntries--;

			result = true;
		} // end if

		return result;
	}// End of remove.

	@Override
	public void clear() {
		while (!isEmpty())
			remove();
	}// End of clear.

	@Override
	public int getFrequencyOf(T anEntry) {
		int frequency = 0;

		DoublyLinkedNode currentNode = firstNode;
		if (currentNode != null) for (int i = 0; i < numberOfEntries; i++) {
			if (anEntry.equals(currentNode.data)) frequency++;

			currentNode = currentNode.next;
		} // end for

		return frequency;
	}// End of get frequency of.

	@Override
	public boolean contains(T anEntry) {
		DoublyLinkedNode currentNode = firstNode;

		while (currentNode != null) {
			if (anEntry.equals(currentNode.data)) return true;
			else currentNode = currentNode.next;
		} // end while

		return false;
	}// End of contaions

	@Override
	public T[] toArray() {
		@SuppressWarnings("unchecked")
		T[] result = (T[]) new Object[numberOfEntries]; // Unchecked cast

		DoublyLinkedNode currentNode = firstNode;
		for (int i = 0; i < numberOfEntries; i++) {
			result[i] = currentNode.data;
			currentNode = currentNode.next;
		} // end while

		return result;
	}// End of toArray.

	@Override
	public BagInterface<T> union(BagInterface<T> anotherBag) {
		DoublyLinkedNode currentNode = firstNode;
		BagInterface<T> returned = copy(anotherBag); // Creates a copy of the inputed bag.

		for (int i = 0; i < numberOfEntries; i++) {
			returned.add(currentNode.data);
			currentNode = currentNode.next;
		} // end for

		return returned;
	}// End of union.

	@Override
	public BagInterface<T> intersection(BagInterface<T> anotherBag) {
		DoublyLinkedNode tempNode = firstNode;
		LinkedBag<T> copy = copy(anotherBag); // Creates a copy of the inputed bag.
		LinkedBag<T> returned = new LinkedBag<>();

		for (int i = 0; i < numberOfEntries; i++) {
			if (copy.contains(tempNode.data)) {
				returned.add(tempNode.data);
				copy.remove(tempNode.data);
			} // end if
			tempNode = tempNode.next;
		} // end for
		return returned;
	}// End of intersection.

	@Override
	public BagInterface<T> difference(BagInterface<T> anotherBag) {
		DoublyLinkedNode tempNode = firstNode;
		LinkedBag<T> copy = copy(anotherBag); // Creates a copy of the inputed bag.
		BagInterface<T> returned = new LinkedBag<>();

		for (int i = 0; i < numberOfEntries; i++) {
			if (!copy.contains(tempNode.data)) {
				returned.add(tempNode.data);
			} // end if
			copy.remove(tempNode.data);
			tempNode = tempNode.next;
		} // end for
		return returned;
	}// End of difference.

	/**
	 * Replaces a object in the LinkedBag with the inputed object. The object can be
	 * anything of the same type.
	 * 
	 * @param replacement
	 *            Object that is replacing.
	 * @return Returns the object that is removed.
	 */
	public T replace(T replacement) {
		T temp = remove();
		add(replacement);

		return temp;
	}// End of replace.

	/**
	 * Removes all occurences of the inputed object.
	 * 
	 * @param anEntry
	 *            Object type that is being removed from the LinkedBag.
	 */
	public void removeEntry(T anEntry) {
		while (contains(anEntry)) {
			remove(anEntry);
		} // end while
	}// End of removeEntry.

	/**
	 * This method works the same as the equals method from the Object class. If the
	 * inputed BagInterface contains the same frequency of each object as the
	 * current one does, it returns true. The order does not matter.
	 * 
	 * @param o
	 *            Inputed BagInterface that is being compared.
	 * @return True if equals, false if not.
	 */
	public boolean equals(BagInterface<T> o) {
		if (o.getCurrentSize() != getCurrentSize()) return false;

		boolean yee = true;
		DoublyLinkedNode currentNode = firstNode;

		for (int i = 0; i < numberOfEntries; i++) {
			if (getFrequencyOf(currentNode.data) != o.getFrequencyOf(currentNode.data)) yee = false;
			currentNode = currentNode.next;
		} // end if
		return yee;
	}// End of equals.

	/**
	 * Creates a copy of the inputed BagInterface to a new LinkedBag.
	 * 
	 * @param aBag
	 *            Bag that is being copied.
	 * @return A new LinkedBag.
	 */
	private LinkedBag<T> copy(BagInterface<T> aBag) {
		T[] arr = aBag.toArray();
		LinkedBag<T> returned = new LinkedBag<>();

		for (int i = 0; i < aBag.getCurrentSize(); i++)
			returned.add(arr[i]);

		return returned;
	}// End of copy.

	/**
	 * Finds the a DoublyLinkedNode that has the same data as the entry.
	 * 
	 * @param anEntry
	 *            The data that is being searched for.
	 * @return The DoublyLinkedNode that has the data that is being searched for.
	 */
	private DoublyLinkedNode find(T anEntry) {
		boolean found = false;
		DoublyLinkedNode currentNode = firstNode;

		while (!found && (currentNode != null)) {
			if (anEntry.equals(currentNode.data)) found = true;
			else currentNode = currentNode.next;
		} // end while

		return currentNode;
	}// End find

	private class DoublyLinkedNode {
		private T data; // Entry in bag
		private DoublyLinkedNode next; // Link to next node
		@SuppressWarnings("unused")
		private DoublyLinkedNode prev; // Link to the previous node

		private DoublyLinkedNode(T dataPortion) {
			this(dataPortion, null, null);
		} // end constructor

		private DoublyLinkedNode(T dataPortion, DoublyLinkedNode nextNode, DoublyLinkedNode prevNode) {
			data = dataPortion;
			next = nextNode;
			prev = prevNode;
		} // end constructor
	} // end DoublyLinkedNode
}// end DoublyLinkedBag
