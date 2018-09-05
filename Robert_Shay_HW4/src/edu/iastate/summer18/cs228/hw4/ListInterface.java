package edu.iastate.summer18.cs228.hw4;

/**
 * An interface for the ADT list. Entries in a list have positions that begin
 * with 1.
 * 
 * @author Frank M. Carrano
 * @author Timothy M. Henry
 * @author Robert Shay
 * @version 4.0
 */
public interface ListInterface<T> {
	/**
	 * Adds a new entry to the end of this list. Entries currently in the list are
	 * unaffected. The list's size is increased by 1.
	 * 
	 * @param newEntry
	 *            The object to be added as a new entry.
	 */
	public void add(T newEntry);

	/**
	 * Adds a new entry at a specified position within this list. Entries originally
	 * at and above the specified position are at the next higher position within
	 * the list. The list's size is increased by 1.
	 * 
	 * @param newPosition
	 *            An integer that specifies the desired position of the new entry.
	 * @param newEntry
	 *            The object to be added as a new entry.
	 * @throws IndexOutOfBoundsException
	 *             if either newPosition < 1 or newPosition > getLength() + 1.
	 */
	public void add(int newPosition, T newEntry);

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
	 * Replaces the entry at a given position in this list.
	 * 
	 * @param givenPosition
	 *            An integer that indicates the position of the entry to be
	 *            replaced.
	 * @param newEntry
	 *            The object that will replace the entry at the position
	 *            givenPosition.
	 * @return The original entry that was replaced.
	 * @throws IndexOutOfBoundsException
	 *             if either givenPosition < 1 or givenPosition > getLength().
	 */
	public T replace(int givenPosition, T newEntry);

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

	// NEW METHODS

	/**
	 * Adds a new entry to the beginning of this collection.
	 * 
	 * @param newEntry
	 *            The object to be added as a new entry.
	 */
	public void addFirst(T newEntry);

	/**
	 * Adds a new entry to the end of this collection.
	 * 
	 * @param newEntry
	 *            The object to be added as a new entry.
	 */
	public void addLast(T newEntry);

	/**
	 * Removes and returns the first entry in this collection.
	 * 
	 * @return A reference to the removed entry or null, if the list was empty.
	 */
	public T removeFirst();

	/**
	 * Removes and returns the last entry in this collection.
	 * 
	 * @return A reference to the removed entry or null, if the list was empty.
	 */
	public T removeLast();

	/**
	 * Retrieves the first entry in this collection.
	 * 
	 * @return A reference to the first entry or null, if the list is empty.
	 */
	public T getFirst();

	/**
	 * Retrieves the last entry in this collection.
	 * 
	 * @return A reference to the last entry or null, if the list is empty.
	 */
	public T getLast();

	/**
	 * Moves the entry located at position i in this list, to a new location j in
	 * this list. This operation is possible only if list is not empty and we have
	 * at least max(i,j) entries.
	 * 
	 * @param i
	 *            Current list entry's location.
	 * @param j
	 *            New location of entry previously located at i.
	 * @throws IndexOutOfBoundsException
	 *             if either i or j is <1 or i or j > getLength()+1
	 */
	public void place(int i, int j);

	/**
	 * Swaps the entries located at positions i and j. This operation is possible
	 * only if list is not empty and we have at least max(i,j) entries.
	 * 
	 * @param i
	 *            Previous location of entry to be put at location j.
	 * @param j
	 *            Previous location of entry to be put at location i.
	 * @throws IndexOutOfBoundsException
	 *             if either i or j is <1 or i or j > getLength()+1
	 */
	public void swap(int i, int j);

	/**
	 * All entries located at positions starting from "start" and ending at "end",
	 * inclusive, are copied to a new ListInterface and returned. Our list contents
	 * do not change. Operation is possible only if we have at least "end" entries.
	 * "start" and "end" can be equal, but "end" cannot be less than "start".
	 * 
	 * @param start
	 *            First location of entry to be copied to a new ListInterface.
	 * @param end
	 *            Lost location of entry to be copied to a new ListInterface.
	 * @throws IllegalArgumentException
	 *             if "start">"end"
	 * @throws IndexOutOfBoundsException
	 *             if either "start" or "end" is <1 or "start" or "end" >
	 *             getLength()+1
	 */
	public ListInterface<T> subList(int start, int end);

	/**
	 * If "items" is not null then add all of its entries to our list starting at
	 * the specified "index", i.e., first entry of "items" will be placed at
	 * location "index" in our list and so on. All of our entries in this list will
	 * be shifted starting at that position.
	 * 
	 * @param index
	 *            Location of copied first entry in "items" to be in current list.
	 * @param items
	 *            List of items to be copied to this current list.
	 * @throws IndexOutOfBoundsException
	 *             if either "index" < 1 or "index" > getLength()+1
	 */
	public void addAll(int index, ListInterface<T> items);

	/**
	 * Returns the index/location of the n'th occurrence of the specified entry in
	 * this list, or -1 if this list does not contain the n'th occurrence of this
	 * element. Entry can be null.
	 * 
	 * @param n
	 *            Integer value indicating n'th copy of this object.
	 * @param anObject
	 *            The object which n'th copy needs to be looked up. This object can
	 *            be null.
	 * @throws IndexOutOfBoundsException
	 *             if either n < 1 or n > getLength()+1
	 */
	public int nthIndexOf(int n, T anObject);

	/**
	 * Removes the n'th occurrence of the specified element in this list. Returns
	 * true if entry's n'th occurrence successfully removed, and false if the list
	 * was empty, or specified entry's n'th occurrence did not exist.
	 *
	 * @param n
	 *            Integer value indicating n'th copy of this object in this list.
	 * @param anObject
	 *            The object which n'th copy needs to be looked up, and if exists
	 *            removed.
	 * @throws IndexOutOfBoundsException
	 *             if either n < 1 or n > getLength()+1
	 * @return true if removed, and false otherwise.
	 */
	public boolean removeNth(int n, T anObject);

	/**
	 * Returns how many times anObject occurs in this list.
	 * 
	 * @param anObject
	 *            Object looked up.
	 * @return Number of occurrences of anObject in this list.
	 */
	public int getFrequencyOf(T anObject);

} // end ListInterface
