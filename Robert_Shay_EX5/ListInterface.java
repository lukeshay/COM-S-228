package edu.iastate.summer18.cs228.ex5;

/**
 * An interface for the ADT list. Entries in a list have positions that begin
 * with 1.
 * 
 * @author
 */
public interface ListInterface<T extends Comparable<? super T>> {
	/**
	 * Adds a new entry to the correct position in this list, so that entries in
	 * this list are in nondecreasing order. The list's size is increased by 1.
	 * 
	 * @param newEntry
	 *            The object to be added as a new entry.
	 */
	public void add(T newEntry);

	/**
	 * Removes the entry at a given position from this list. Entries originally at
	 * positions higher than the given position are at the next lower position
	 * within the list, and the list's size is decreased by 1.
	 * 
	 * @param givenPosition
	 *            An integer that indicates the position of the entry to be removed.
	 * @return A reference to the removed entry.
	 * @throws IndexOutOfBoundsException
	 *             if either givenPosition < 1 or givenPosition > getLength().
	 */
	public T remove(int givenPosition);

	/** Removes all entries from this list. */
	public void clear();

	/**
	 * Retrieves the entry at a given position in this list.
	 * 
	 * @param givenPosition
	 *            An integer that indicates the position of the desired entry.
	 * @return A reference to the indicated entry.
	 * @throws IndexOutOfBoundsException
	 *             if either givenPosition < 1 or givenPosition > getLength().
	 */
	public T getEntry(int givenPosition);

	/**
	 * Retrieves all entries that are in this list in the order in which they occur
	 * in the list.
	 * 
	 * @return A newly allocated array of all the entries in the list. Note: If the
	 *         list is empty, the returned array is empty.
	 */
	public T[] toArray();

	/**
	 * Sees whether this list contains a given entry.
	 * 
	 * @param anEntry
	 *            The object that is the desired entry.
	 * @return True if the list contains anEntry, or false if not.
	 */
	public boolean contains(T anEntry);

	/**
	 * Gets the length of this list.
	 * 
	 * @return The integer number of entries currently in the list.
	 */
	public int getLength();

	/**
	 * Sees whether this list is empty.
	 * 
	 * @return True if the list is empty, or false if not.
	 */
	public boolean isEmpty();

	/**
	 * Returns the smallest object in this list.
	 */
	public T getMin();

	/**
	 * Returns the largest object in this list.
	 */
	public T getMax();

	/**
	 * Removes and returns the smallest object in this list.
	 */
	public T removeMin();

	/**
	 * Removes and returns the largest object in this list.
	 */
	public T removeMax();

	/**
	 * Returns a new list that are less than some given item.
	 */
	public ListInterface<T> getAllLessThan(T anObject);

} // end ListInterface
