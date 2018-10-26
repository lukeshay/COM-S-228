package edu.iastate.summer18.cs228.hw5;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.ListIterator;

public class TestAdaptiveListIterator 
{
	private AdaptiveList<Integer> intList;
	ListIterator<Integer> iter;
	
	@Before
	public void setup()
	{
		intList = new AdaptiveList<>();
		
		for(int i = 0; i < 5; i++) // 0 1 2 3 4 
			intList.add(i);
	}
	
	@Test
	public void NextAndPreviousTest()
	{
		iter = intList.listIterator();
		assertEquals(-1, iter.previousIndex());
		assertEquals(0, iter.nextIndex());
//		prevList(iter.previousIndex(), iter.nextIndex());
		
		assertEquals(0, (int)iter.next());
		assertEquals(0, iter.previousIndex());
		assertEquals(1, iter.nextIndex());
//		prevList(iter.previousIndex(), iter.nextIndex());
		
		assertEquals(1, (int)iter.next());
		assertEquals(1, iter.previousIndex());
		assertEquals(2, iter.nextIndex());
//		prevList(iter.previousIndex(), iter.nextIndex());
		
		assertEquals(2, (int)iter.next());
		assertEquals(2, iter.previousIndex());
		assertEquals(3, iter.nextIndex());
//		prevList(iter.previousIndex(), iter.nextIndex());
		
		assertEquals(3, (int)iter.next());
		assertEquals(3, iter.previousIndex());
		assertEquals(4, iter.nextIndex());
//		prevList(iter.previousIndex(), iter.nextIndex());
		
		assertEquals(4, (int)iter.next());
		assertEquals(4, iter.previousIndex());
		assertEquals(5, iter.nextIndex());
//		prevList(iter.previousIndex(), iter.nextIndex());
//		iter.next(); // throws NoSuchElementException
		
		
		assertEquals(4, (int)iter.previous());
		assertEquals(3, iter.previousIndex());
		assertEquals(4, iter.nextIndex());
//		prevList(iter.previousIndex(), iter.nextIndex());
		
		assertEquals(3, (int)iter.previous());
		assertEquals(2, iter.previousIndex());
		assertEquals(3, iter.nextIndex());
//		prevList(iter.previousIndex(), iter.nextIndex());
		
		assertEquals(2, (int)iter.previous());
		assertEquals(1, iter.previousIndex());
		assertEquals(2, iter.nextIndex());
//		prevList(iter.previousIndex(), iter.nextIndex());
		
		assertEquals(1, (int)iter.previous());
		assertEquals(0, iter.previousIndex());
		assertEquals(1, iter.nextIndex());
//		prevList(iter.previousIndex(), iter.nextIndex());
		
		assertEquals(0, (int)iter.previous());
		assertEquals(-1, iter.previousIndex());
		assertEquals(0, iter.nextIndex());
//		prevList(iter.previousIndex(), iter.nextIndex());
		
//		assertEquals(-1, (int)iter.previous()); // throws NoSuchElementException
	}
	
	@Test
	public void RemoveAndSetTest()
	{
		iter = intList.listIterator(intList.size());
		assertEquals(4, iter.previousIndex());
		assertEquals(5, iter.nextIndex());
		
		iter.previous();
		iter.remove();
		assertEquals(4, intList.size());
		assertEquals("A sequence of items from the most recent linked list:\n(0, 1, 2, 3)", intList.toStringLinked());
//		prevList(iter.previousIndex(), iter.nextIndex());
		assertEquals(3, (int)intList.get(intList.size()-1));
		assertEquals(3, iter.previousIndex());
		assertEquals(4, iter.nextIndex());
		
		iter.previous();
		iter.previous();
		iter.next();
		iter.remove();
		assertEquals(3, intList.size());
//		prevList(iter.previousIndex(), iter.nextIndex());
		assertEquals("A sequence of items from the most recent linked list:\n(0, 1, 3)", intList.toStringLinked());
		assertEquals(3, (int)intList.get(intList.size()-1));
		assertEquals(1, iter.previousIndex());
		assertEquals(2, iter.nextIndex());
		
		iter.add(2);
		assertEquals("A sequence of items from the most recent linked list:\n(0, 1, 2, 3)", intList.toStringLinked());
		assertEquals(4, intList.size());
//		prevList(iter.previousIndex(), iter.nextIndex());
		assertEquals(3, (int)intList.get(intList.size()-1));
		assertEquals(2, iter.previousIndex());
		assertEquals(3, iter.nextIndex());
//		iter.remove(); // throws IllegalStateException
		
		iter.next();
		iter.set(1);
		assertEquals("A sequence of items from the most recent linked list:\n(0, 1, 2, 1)", intList.toStringLinked());
		iter.remove();
		assertEquals("A sequence of items from the most recent linked list:\n(0, 1, 2)", intList.toStringLinked());
		assertEquals(3, intList.size());
//		prevList(iter.previousIndex(), iter.nextIndex());
		assertEquals(2, (int)intList.get(intList.size()-1));
		assertEquals(2, iter.previousIndex());
		assertEquals(3, iter.nextIndex());
		
		iter.previous();
		iter.previous();
		iter.previous();
		iter.set(2);
		assertEquals(3, intList.size());
//		prevList(iter.previousIndex(), iter.nextIndex());
		assertEquals(2, (int)intList.get(intList.size()-1));
		assertEquals(-1, iter.previousIndex());
		assertEquals(0, iter.nextIndex());
		
	}
	
	@Test
	public void AddTest()
	{
		iter = intList.listIterator(5);
		assertEquals(4, iter.previousIndex());
		assertEquals(5, iter.nextIndex());
		
		iter.add(5);
		assertEquals(5, iter.previousIndex());
		assertEquals(6, iter.nextIndex());
		assertEquals(5, (int)intList.get(intList.size()-1));
//		prevList(iter.previousIndex(), iter.nextIndex());
		
		iter.previous();
		iter.previous();
		iter.previous();
		iter.add(0);
		assertEquals(3, iter.previousIndex());
		assertEquals(4, iter.nextIndex());
		assertEquals(0, (int)intList.get(3));
//		prevList(iter.previousIndex(), iter.nextIndex());
		
		iter.next();
		iter.remove();
		iter.add(null);
		assertEquals(4, iter.previousIndex());
		assertEquals(5, iter.nextIndex());
		assertEquals(null, intList.get(4));
//		prevList(iter.previousIndex(), iter.nextIndex());
	}
	
	// "previousIndex | nextIndex"
	// "updated doubly linked list"
	private void prevList(int prev, int next)
	{
		System.out.println(prev + " | " + next + "\n" + intList.toStringLinked());
	}
}
