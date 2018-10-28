
package edu.iastate.summer18.cs228.hw6;

import org.junit.Before;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Robert Shay
 */
public class EntryTreeTests {
	EntryTree<Integer, String> tree;
	Integer[] path1 = { 1, 2, 4 };
	Integer[] path2 = { 1, 2, 3 };
	Integer[] path3 = { 1, 2, 5 };
	Integer[] path4 = { 1, 2, 5, 7, 8 };
	Integer[] path5 = { 1, 2, 5, 7, 9 };
	Integer[] path6 = { 1, 2, 5, 7, 9, 3, 4, 5, 2, 13, 3, 4, 5, 63, 2, 2 };
	Integer[] path7 = { 2 };
	
	@Before
	public void setup() {
		tree = new EntryTree<>();
		assertTrue(tree.add(path1, "Test1"));
		tree.add(path2, "Test2");
		tree.add(path3, "Test3");
		tree.add(path4, "Test4");
		tree.add(path5, "Test5");
		tree.add(path6, "Test6");
		tree.add(path7, "Test7");
		assertFalse(tree.add(path2, null));
	}
	
	/**
	 * This test must pass for the other tests to be accurate.
	 */
	@Test
	public void addTest() {
		assertEquals(tree.root.child.child.child.value, "Test1");
		assertEquals(tree.root.child.child.child.next.value, "Test2");
		assertEquals(tree.root.child.child.child.next.next.value, "Test3");
		assertEquals(tree.root.child.child.child.next.next.child.child.value, "Test4");
		assertEquals(tree.root.child.child.child.next.next.child.child.next.value, "Test5");
		assertEquals(tree.root.child.child.child.next.next.child.child.next.child.child.child.child.child.child.child.child.child.child.child.value, "Test6");
		assertEquals(tree.root.child.next.value, "Test7");
	}
	
	@Test
	public void searchTest() {
		Integer[] arr = { 1, 2, 3 };
		assertEquals("Test2", tree.search(arr));
		arr = new Integer[] { 1, 2, 3, 4 };
		assertEquals(null, tree.search(arr));
		arr = new Integer[] { 1, 2, 5, 7, 9 };
		assertEquals("Test5", tree.search(arr));
	}
	
	@Test
	public void prefixTest() {
		Integer[] arr = { 1, 2, 3, 0, 5, 3, 1 };
		Integer[] result = tree.prefix(arr);
		
		assertEquals(1, (int) result[0]);
		assertEquals(2, (int) result[1]);
		assertEquals(3, (int) result[2]);
		
		arr = new Integer[] { 3, 4, 5, 6 };
		assertEquals(0, tree.prefix(arr).length);
		
		arr = new Integer[] { 1, 2, 4 };
		result = tree.prefix(arr);
		assertEquals(1, (int) result[0]);
		assertEquals(2, (int) result[1]);
		assertEquals(4, (int) result[2]);
	}
	
	@Test
	public void getAllTest() {
		String[][] arr = tree.getAll();
		String[][] expected = { { "2", "Test7" }, { "124", "Test1" }, { "123", "Test2" }, { "125", "Test3" }, { "12578", "Test4" }, { "12579", "Test5" },
				{ "125793452133456322", "Test6" } };
		
		for (int i = 0; i < 7; i++) {
			assertEquals(expected[i][0], arr[i][0]);
			assertEquals(expected[i][1], arr[i][1]);
		}
		
		tree = new EntryTree<>();
		
		assertEquals(0, tree.getAll().length);
	}
	
	@Test
	public void removeTest() {
		assertEquals("Test1", tree.remove(path1));
		
		String[][] arr = tree.getAll();
		String[][] expected = { { "2", "Test7" }, { "123", "Test2" }, { "125", "Test3" }, { "12578", "Test4" }, { "12579", "Test5" },
				{ "125793452133456322", "Test6" } };
		
		for (int i = 0; i < 6; i++) {
			assertEquals(expected[i][0], arr[i][0]);
			assertEquals(expected[i][1], arr[i][1]);
		}
		
		assertEquals(null, tree.remove(path1));
	}
	
	@Test
	public void showTreeTest() {
		tree.showTree();
		/*
		 * null -> null 1 -> null 2 -> null 4 -> Test1 3 -> Test2 5 -> Test3 7 -> null 8
		 * -> Test4 9 -> Test5 3 -> null 4 -> null 5 -> null 2 -> null 13 -> null 3 ->
		 * null 4 -> null 5 -> null 63 -> null 2 -> null 2 -> Test6 2 -> Test7
		 */
		
		tree = new EntryTree<>();
		tree.showTree();
		/*null -> null*/
	}
}
