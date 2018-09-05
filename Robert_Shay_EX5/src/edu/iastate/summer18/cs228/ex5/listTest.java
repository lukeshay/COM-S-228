/**
 * 
 */
package edu.iastate.summer18.cs228.ex5;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import java.util.Arrays;

/**
 * @author Robert Shay
 */
public class listTest {
	ListInterface<Integer> list = new LListWithTail<Integer>();

	@Test
	public void addTest() {
		list.add(3);
		list.add(1);
		list.add(2);
		list.add(5);
		list.add(4);

		assertEquals(1, (int) list.getMin());
		assertEquals(5, (int) list.getMax());
		
		for(int i = 1; i <= 5; i++)
			assertEquals(i, (int) list.getEntry(i));
		
		assertEquals("[1, 2, 3, 4, 5]", Arrays.toString(list.toArray()));
		assertEquals("[1, 2]", Arrays.toString(list.getAllLessThan(3).toArray()));
		assertEquals("[1, 2, 3, 4, 5]", Arrays.toString(list.getAllLessThan(6).toArray()));
	}

}
