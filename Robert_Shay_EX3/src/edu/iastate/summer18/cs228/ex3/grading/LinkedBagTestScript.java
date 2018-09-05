/**
 * 
 */
package edu.iastate.summer18.cs228.ex3.grading;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.iastate.summer18.cs228.ex3.LinkedBag;
import edu.iastate.summer18.cs228.ex3.grading.Points;

/**
 * @author menglu Yu
 *
 */
@Total(value=38)
public class LinkedBagTestScript {
	private LinkedBag<String> rbag1, rbag2, rbag3;
	/**
	 * @throws java.lang.Exception
	 */
	
	@Before
	public void setUp() {
		rbag1 = new LinkedBag<String>();
		rbag2 = new LinkedBag<String>();
		rbag3 = new LinkedBag<String>();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown(){
		rbag1 = null;
		rbag2 = null;
		rbag3 = null;
	}

	@Test
	@Points(value=2)
	public void getMin1() {
		rbag1.add("1");
		rbag1.add("2");
		rbag1.add("3");
		
		assertEquals("bag with [1,2,3] should return the minmum 1", rbag1.getMin(),"1");
		assertNull("if the bag is empty, getMin should return null",rbag2.getMin());		
	}
	
	@Test
	@Points(value=3)
	public void getMin2() {
		rbag1.add("7");
		rbag1.add("-1");
		rbag1.add("5");
		
		assertEquals("bag with [7,-1,5] should return the minmum -1", rbag1.getMin(),"-1");
		assertNull("if the bag is empty, getMin should return null",rbag2.getMin());		
	}
	
	@Test
	@Points(value=2)
	public void getMax1() {
		rbag1.add("1");
		rbag1.add("2");
		rbag1.add("3");
		
		assertEquals("bag with [1,2,3] should return the maximum 3", rbag1.getMax(),"3");
		assertNull("if the bag is empty, getMax should return null",rbag2.getMax());
		
	}
	
	@Test
	@Points(value=3)
	public void getMax2() {
		rbag1.add("7");
		rbag1.add("-1");
		rbag1.add("5");
		
		assertEquals("bag with [7,-1,5] should return the minmum 7", rbag1.getMax(),"7");
		assertNull("if the bag is empty, getMin should return null",rbag2.getMax());		
	}
	
	
	@Test
	@Points(value=2)
	public void removeMin1() {
		rbag1.add("3");
		rbag1.add("2");
		rbag1.add("1");		
		assertEquals("bag with [3,2,1] call removeMin should return the minmum 1", rbag1.removeMin(),"1");		
		assertNull("if the bag is empty, removeMin should return null",rbag3.removeMin());
		
	}
	
	@Test
	@Points(value=3)
	public void removeMin2() {
		rbag1.add("1");
		rbag1.add("2");
		rbag1.add("3");		
		assertEquals("bag with [1,2,3] call removeMin should return the minmum 1", rbag1.removeMin(),"1");		
		assertNull("if the bag is empty, removeMin should return null",rbag3.removeMin());
		
	}
	
	@Test
	@Points(value=2)
	public void removeMax1() {
		rbag1.add("1");
		rbag1.add("2");
		rbag1.add("3");
		
		
		assertEquals("bag with [1,2,3] call removeMax should return the maximal 3", rbag1.removeMax(),"3");
		assertNull("if the bag is empty, removeMax should return null",rbag3.removeMax());		
	}
	
	@Test
	@Points(value=3)
	public void removeMax2() {
		rbag1.add("3");
		rbag1.add("2");
		rbag1.add("1");
		
		
		assertEquals("bag with [3,2,1] call removeMax should return the maximal 3", rbag1.removeMax(),"3");
		assertNull("if the bag is empty, removeMax should return null",rbag3.removeMax());		
	}
		
	@Test
	@Points(value=6)
	public void getAllLessThan1() {
		rbag1.add("1");
		rbag1.add("1");
		rbag1.add("2");
		rbag1.add("3");
		rbag2.add("3");
		rbag2.add("2");
		rbag2.add("1");
		rbag2.add("1");			
		assertEquals("bag with [1,1,2,3] call getAllLessThan(3) the size should be 3", rbag1.getAllLessThan("3").getCurrentSize(),3);
		assertEquals("bag with [1,1,2,3] call getAllLessThan(1) the size should be 0", rbag1.getAllLessThan("1").getCurrentSize(),0);
		assertEquals("bag with [3,2,1,1] call getAllLessThan(3) the size should be 3", rbag2.getAllLessThan("3").getCurrentSize(),3);
		assertEquals("bag with [3,2,1,1] call getAllLessThan(1) the size should be 0", rbag2.getAllLessThan("1").getCurrentSize(),0);
		assertArrayEquals("bag with[1,1,2,3] getAlllessthan(4) should be equal to [1,1,2,3]", (rbag1.getAllLessThan("4")).toArray(), new String[]{"1","1","2","3"});
		assertArrayEquals("bag with[3,2,1,1] getAlllessthan(3).toArray should be equal to [1,1,2]", (rbag1.getAllLessThan("3")).toArray(), new String[]{"1","1","2"});
	}
	
	@Test
	@Points(value=6)
	public void duplicate() {
		rbag1.add("1");
		rbag1.add("1");
		rbag1.add("2");
		rbag2.add("2");
		rbag2.add("1");
		rbag2.add("1");		
		assertEquals("bag with [1,1,2] call duplicate should return [1,1,2], which the size should be 3", rbag3.duplicate(rbag1).getCurrentSize(),rbag2.getCurrentSize());
		assertEquals("empty bag call duplicate should return the size of 0", rbag3.duplicate(rbag3).getCurrentSize(),0);
		assertEquals("bag with [2,1,1] call duplicate should return [2,1,1], which the size should be 3", rbag2.duplicate(rbag1).getCurrentSize(),rbag2.getCurrentSize());
		
		assertArrayEquals("bag with[1,1,2] duplicate should be equal to [1,1,2]", (rbag3.duplicate(rbag1)).toArray(), new String[]{"1","1","2"});
		assertArrayEquals("bag with[2,1,1] duplicate should be equal to [2,1,1]", (rbag3.duplicate(rbag2)).toArray(), new String[]{"2","1","1"});
	}
	
	@Test
	@Points(value=6)
	public void toArray(){
		rbag1.add("1");
		rbag1.add("1");
		rbag1.add("2");
		rbag1.add("3");
		assertArrayEquals("bag with[1,1,2,3] getAlllessthan(4) toArray should be equal to [1,1,2,3]", (rbag1.getAllLessThan("4")).toArray(), new String[]{"1","1","2","3"});
		assertArrayEquals("bag with[1,1,2,3] getAlllessthan(3).toArray should be equal to [1,1,2]", (rbag1.getAllLessThan("3")).toArray(), new String[]{"1","1","2"});
	}

}
