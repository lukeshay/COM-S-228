package edu.iastate.summer18.cs228.ex6;

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * @author Robert Shay
 */
public class DictionaryTests {
	ArrayDictionary<Integer, String> dict = new ArrayDictionary<Integer, String>();
	
	@Before
	public void setup() {
		dict.add(1, "One");
		dict.add(2,  "Two");
		dict.add(3,  "Three");
		dict.add(4, "Four");
	}
	
	@Test
	public void containsTest() {
		assertTrue(dict.contains(2));
		assertFalse(dict.contains(7));
	}
	
	@Test
	public void getSizeTest() {
		assertEquals(4, dict.getSize());
	}
	
	@Test
	public void getValueTest() {
		assertEquals("One", dict.getValue(1));
	}
	
	@Test
	public void removeTest() {
		dict.remove(1);
		dict.getValue(1);
	}
}
