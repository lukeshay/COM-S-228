package edu.iastate.summer18.cs228.hw6;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * An entry tree class Add Javadoc comments to each method
 * @author Robert Shay
 */
public class EntryTree<K, V> {
	/**
	 * dummy root node made public for grading
	 */
	protected Node root;
	protected ArrayList<Node> nodes = new ArrayList<>();
	/**
	 * prefixlen is the largest index such that the keys in the subarray keyarr from
	 * index 0 to index prefixlen - 1 are, respectively, with the nodes on the path
	 * from the root to a node. prefixlen is computed by a private method shared by
	 * both search() and prefix() to avoid duplication of code.
	 */
	protected int prefixlen;
	
	protected class Node implements EntryNode<K, V> {
		protected Node child; // link to the first child node
		protected Node parent; // link to the parent node
		protected Node prev; // link to the previous sibling
		protected Node next; // link to the next sibling
		protected K key; // the key for this node
		protected V value; // the value at this node
		
		public Node(K aKey, V aValue) {
			key = aKey;
			value = aValue;
			child = null;
			parent = null;
			prev = null;
			next = null;
		}
		
		@Override
		public EntryNode<K, V> parent() {
			return parent;
		}
		
		@Override
		public EntryNode<K, V> child() {
			return child;
		}
		
		@Override
		public EntryNode<K, V> next() {
			return next;
		}
		
		@Override
		public EntryNode<K, V> prev() {
			return prev;
		}
		
		@Override
		public K key() {
			return key;
		}
		
		@Override
		public V value() {
			return value;
		}
	}
	
	public EntryTree() {
		root = new Node(null, null);
	}
	
	/**
	 * Returns the value of the entry with a specified key sequence, or null if this
	 * tree contains no entry with the key sequence.
	 * 
	 * @param keyarr
	 * @return Returns the value if found, or null if not.
	 */
	public V search(K[] keyarr) {
		Node cur = root;
		updatePrefixlen(keyarr); // Updates the prefixlen variable
		
		if (keyarr == null || keyarr.length == 0 || prefixlen < keyarr.length) return null; // Returns null if any of the conditions apply.
		else for (int i = 0; i < keyarr.length; i++) { // Loops though each key from the arr.
			if (keyarr[i] == null) throw new NullPointerException(); // Throws null pointer exception if there is an empty spot.
			cur = findChild(cur, keyarr[i]); // Updates cur to the node with the next key.
			if (cur == null) return null; // Returns null if the node is null.
		}
		
		return cur.value;
	}
	
	/**
	 * The method returns an array of type K[] with the longest prefix of the key
	 * sequence specified in keyarr such that the keys in the prefix label the nodes
	 * on the path from the root to a node. The length of the returned array is the
	 * length of the longest prefix.
	 * 
	 * @param keyarr
	 * @return Null if keyarr is null or length of 0, otherwise returns the longest
	 *         prefix.
	 */
	public K[] prefix(K[] keyarr) {
		if (keyarr == null || keyarr.length == 0) return null;
		updatePrefixlen(keyarr); // Updates the prefixlen variable.
		return Arrays.copyOf(keyarr, prefixlen); // Returns a copy of the array with the prefixlen length.
	}
	
	/**
	 * The method locates the node P corresponding to the longest prefix of the key
	 * sequence specified in keyarr such that the keys in the prefix label the nodes
	 * on the path from the root to the node. If the length of the prefix is equal
	 * to the length of keyarr, then the method places aValue at the node P and
	 * returns true. Otherwise, the method creates a new path of nodes (starting at
	 * a node S) labelled by the corresponding suffix for the prefix, connects the
	 * prefix path and suffix path together by making the node S a child of the node
	 * P, and returns true.
	 * 
	 * @param keyarr
	 * @param aValue
	 * @return True if executed, false if not.
	 */
	public boolean add(K[] keyarr, V aValue) {
		if (keyarr == null || keyarr.length == 0 || aValue == null) return false;
		Node cur = root;
		for (int i = 0; i < keyarr.length; i++) {
			if (keyarr[i] == null) throw new NullPointerException();
			
			Node child = findChild(cur, keyarr[i]);
			
			if (child == null) {
				child = new Node(keyarr[i], null);
				child.parent = cur;
				
				if (cur.child == null) cur.child = child;
				else {
					Node sibling = cur.child;
					while (sibling.next != null)
						sibling = sibling.next;
					
					sibling.next = child;
					child.prev = sibling;
				}
			}
			cur = child;
		}
		cur.value = aValue;
		return true;
	}
	
	/**
	 * Removes the entry for a key sequence from this tree and returns its value if
	 * it is present. Otherwise, it makes no change to the tree and returns null.
	 * 
	 * @param keyarr
	 * @return The value of the node that was removed.
	 */
	public V remove(K[] keyarr) {
		updatePrefixlen(keyarr);
		if (keyarr == null || keyarr.length == 0 || prefixlen < keyarr.length) return null;
		
		Node cur = root;
		V returned = null;
		
		for (int i = 0; i < keyarr.length && cur != null; i++) {
			if (keyarr[i] == null) throw new NullPointerException();
			
			cur = findChild(cur, keyarr[i]);
		}
		
		if (cur != null) {
			returned = cur.value;
			cur.value = null;
			removeNodes(cur);
		}
		
		return returned;
	}
	
	/**
	 * The method prints the tree on the console in the output format shown in an
	 * example output file.
	 */
	public void showTree() {
		Node start = root;
		printChildren(0, start);
	}
	
	/**
	 * Returns all values in this tree together with their keys. The order of
	 * outputs would be similar to level order traversal, i.e., first you would get
	 * all values together with their keys in first level from left to right, then
	 * second level, and so on. If tree has no values then it would return null. For
	 * example, for the example image given in description, the returned String[][]
	 * would be equal to {{"IA","Grow"},{"ISU","CS228"}};
	 * 
	 * @return All of the values along with their keys in a 2D array.
	 */
	public String[][] getAll() {
		Node temp = root;
		int vals = countNodes(temp);
		String[][] result = new String[vals][2];
		if (vals != 0) {
			@SuppressWarnings("unchecked")
			V[] values = (V[]) new Object[vals];
			String[] keys = new String[vals];
			findNodes(temp.child, values, keys, 0);
			
			for (int i = 0; i < vals; i++) {
				result[i][0] = keys[i];
				result[i][1] = values[i].toString();
			}
		}
		return result;
	}
	
	/**
	 * Method to update the prefixlen variable.
	 * 
	 * @param keyarr
	 */
	private void updatePrefixlen(K[] keyarr) {
		prefixlen = 0;
		
		Node cur = root;
		
		for (int i = 0; i < keyarr.length; i++) { // For to loop through the key array.
			if (keyarr[i] == null) throw new NullPointerException(); // If there is a null spot, a Null Pointer Exception is thrown.
			cur = findChild(cur, keyarr[i]); // Finds next node with the given key.
			
			if (cur == null) break; // Breaks loop if the node is null.
			prefixlen++;
		}
	}
	
	/**
	 * Gets the key of the inputed node and returns it as a string. This is used for
	 * the getAll() method.
	 * 
	 * @param cur
	 * @return Returns the key as a string with no spaces.
	 */
	private String getKey(Node cur) {
		String nodeKey = "";
		boolean yes = true;
		while (cur != root) { // While loop that continues as long as the current node does not equal the
								// parent node.
			if (yes) nodeKey = cur.key() + nodeKey; // Adds the current key to the string if it is a parent node.
			if (cur.parent != null) {
				yes = true;
				cur = cur.parent; // Checks for parent and sets to parent if there is one.
			}
			else {
				yes = false;
				cur = cur.prev; // If there is not a parent it goes to the previous node.
			}
		}
		
		return nodeKey;
	}
	
	/**
	 * Finds all the nodes that have a value and adds their value and key as a
	 * string to the inputed arrays. This works in a very similar way to @see
	 * printChildren().
	 * 
	 * @param cur
	 * @param valArr
	 * @param keyArr
	 * @param curIndex
	 */
	private void findNodes(Node cur, V[] valArr, String[] keyArr, int curIndex) {
		if (cur == null) return;
		int index = curIndex;
		while (cur != null) {
			if (cur.value() != null) {
				while (valArr[index] != null)
					index++;
				valArr[index] = cur.value();
				keyArr[index] = getKey(cur);
			}
			findNodes(cur.next, valArr, keyArr, index);
			cur = cur.child;
		}
	}
	
	/**
	 * Finds a node with the given key.
	 * 
	 * @param cur
	 * @param key
	 * @return Returns the node with the given key. If there is no node with the
	 *         given key, null is returned.
	 */
	private Node findChild(Node cur, K key) {
		if (cur.child == null) return null; // Returns null if there is no node with the given key.
		cur = cur.child; // Sets current node to be equal to it's child.
		
		// Loops through until the correct node is found. If there is not a node with
		// the key, a null node is returned.
		while ((cur != null) && !cur.key.equals(key))
			cur = cur.next;
		
		return cur;
	}
	
	/**
	 * Used during the remove method. Checks to see if there are nodes in the key
	 * sequence that need to be removed.
	 * 
	 * @param cur
	 */
	private void removeNodes(Node cur) {
		// Returns if the node entered is null.
		if (cur == null) return;
		
		// If to check whether the current node has a child node and if the current node
		// does not have a value.
		if (cur.child == null && cur.value == null) {
			// Checks to see if it has a parent node and if it is the correct node in the
			// chain.
			if (cur.parent != null && cur.parent.child.equals(cur)) {
				cur.parent.child = cur.next; // Removes node if it is up to the conditions
				removeNodes(cur.parent); // Recursive call to check the node before.
			}
			
			if (cur.next != null) cur.next.prev = cur.prev; // Moves the nodes to the left if there is a next node.
			if (cur.prev != null) cur.prev.next = cur.next; // Moves the nodes to the right if there is a previous node.
		}
	}
	
	/**
	 * Recursive method that is used to print the tree.
	 * 
	 * @param spaces
	 * @param cur
	 */
	private void printChildren(int spaces, Node cur) {
		if (cur == null) return; // Returns if the cur node is null;
		
		// While loop that loops through the nodes that are next to the current one.
		while (cur != null) {
			// Determines the number of spaces that need to be printed.
			for (int i = 0; i < spaces; i++)
				System.out.print("  ");
			
			System.out.print(cur.key + " -> " + cur.value + "\n");
			printChildren(spaces + 1, cur.child);
			cur = cur.next;
		}
	}
	
	/**
	 * Counts the number of nodes that have a value.
	 * 
	 * @param cur
	 * @return The number of nodes that have a value
	 */
	private int countNodes(Node cur) {
		int value = 0;
		if (cur == null) return 0; // Base case for recursive call.
		if (cur.value != null) value = 1; // Value is set to 1 if the current node has a value.
		
		return value + countNodes(cur.next) + countNodes(cur.child); // Recursive call to this method to traverse all of the nodes.
	}
}
