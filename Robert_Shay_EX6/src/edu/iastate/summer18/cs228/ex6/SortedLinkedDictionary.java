package edu.iastate.summer18.cs228.ex6;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A class that implements a dictionary by using a sorted linked chain. The
 * dictionary has distinct search keys.
 * 
 * @author Frank M. Carrano
 * @author Timothy M. Henry
 * @author Robert Shay
 * @version 4.0
 */
public class SortedLinkedDictionary<K extends Comparable<? super K>, V> implements DictionaryInterface<K, V> {
	private Node firstNode; // Reference to first node of chain
	private int numberOfEntries;

	public SortedLinkedDictionary() {
		initializeDataFields();
	} // end default constructor

	private void initializeDataFields() {
		firstNode = null;
		numberOfEntries = 0;
	}

	public V add(K key, V value) {
		V result = null;

		// Search chain until you either find a node containing key
		// or locate where it should be
		Node currentNode = firstNode;
		Node nodeBefore = null;

		while ((currentNode != null) && (key.compareTo(currentNode.getKey()) > 0)) {
			nodeBefore = currentNode;
			currentNode = currentNode.getNextNode();
		} // end while

		if ((currentNode != null) && key.equals(currentNode.getKey())) {
			// Key in dictionary; replace corresponding value
			result = currentNode.getValue(); // Get old value
			currentNode.setValue(value); // Replace value
		}
		else // Key not in dictionary; add new node in proper order
		{
			// Assumes key and value are not null
			Node newNode = new Node(key, value); // Create new node

			if (nodeBefore == null) { // Add at beginning (includes empty dictionary)
				newNode.setNextNode(firstNode);
				firstNode = newNode;
			}
			else // Add elsewhere in non-empty list
			{
				newNode.setNextNode(currentNode); // currentNode is after new node
				nodeBefore.setNextNode(newNode); // nodeBefore is before new node
			} // end if

			numberOfEntries++; // Increase length for both cases
		} // end if

		return result;
	} // end add

	private Node getNodeAt(int index) {
		Node cur = firstNode;
		for (int i = 0; i < index; i++)
			cur = cur.getNextNode();

		return cur;
	}

	private int locateIndex(K key) {
		int i = 0;
		for (Node cur = firstNode; cur.getKey() != null; cur = cur.getNextNode()) {
			if (cur.getKey().equals(key)) return i;
			i++;
		}
		return -1;
	}

	private Node getNode(K key) {
		for (Node cur = firstNode; cur.getKey() != null; cur = cur.getNextNode())
			if (cur.getKey().equals(key)) return cur;

		return null;
	}

	private class Node {
		private K key;
		private V value;
		private Node next;

		private Node() {
		}

		private Node(K k, V v) {
			key = k;
			value = v;
		}

		private void setValue(V val) {
			value = val;
		}

		private void setKey(K k) {
			key = k;
		}

		private void setNextNode(Node next) {
			this.next = next;
		}

		private K getKey() {
			return key;
		}

		private V getValue() {
			return value;
		}

		private Node getNextNode() {
			return next;
		}

	} // end Node

	// Since iterators implement Iterator, methods must be public

	private class KeyIterator implements Iterator<K> {
		private Node nextNode;

		private KeyIterator() {
			nextNode = firstNode;
		} // end default constructor

		public boolean hasNext() {
			return nextNode != null;
		} // end hasNext

		public K next() {
			K result;

			if (hasNext()) {
				result = nextNode.getKey();
				nextNode = nextNode.getNextNode();
			}
			else {
				throw new NoSuchElementException();
			} // end if

			return result;
		} // end next

		public void remove() {
			throw new UnsupportedOperationException();
		} // end remove
	} // end KeyIterator

	private class ValueIterator implements Iterator<V> {
		private Node nextNode;

		private ValueIterator() {
			nextNode = firstNode;
		} // end default constructor

		public boolean hasNext() {
			return nextNode != null;
		} // end hasNext

		public V next() {
			V result;

			if (hasNext()) {
				result = nextNode.getValue();
				nextNode = nextNode.getNextNode();
			}
			else {
				throw new NoSuchElementException();
			} // end if

			return result;
		} // end next

		public void remove() {
			throw new UnsupportedOperationException();
		} // end remove
	} // end ValueIterator

	@Override
	public V remove(K key) {
		int index = locateIndex(key);
		Node toRemove = getNodeAt(index);
		Node nodeBefore = getNodeAt(index - 1);

		V returned = toRemove.getValue();
		nodeBefore.setNextNode(toRemove.getNextNode());
		return returned;
	}

	@Override
	public V getValue(K key) {
		return getNode(key).getValue();
	}

	@Override
	public boolean contains(K key) {
		return locateIndex(key) != -1;
	}

	@Override
	public Iterator<K> getKeyIterator() {
		return new KeyIterator();
	}

	@Override
	public Iterator<V> getValueIterator() {
		return new ValueIterator();
	}

	@Override
	public boolean isEmpty() {
		return firstNode != null;
	}

	@Override
	public int getSize() {
		return numberOfEntries;
	}

	@Override
	public void clear() {
		firstNode = null;
		numberOfEntries = 0;
	}
} // end SortedLinkedDictionary
