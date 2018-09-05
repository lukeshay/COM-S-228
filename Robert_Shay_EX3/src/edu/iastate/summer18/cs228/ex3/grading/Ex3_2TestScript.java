package edu.iastate.summer18.cs228.ex3.grading;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.iastate.summer18.cs228.ex3.Ex3_2;
import edu.iastate.summer18.cs228.ex3.LinkedStack;
import edu.iastate.summer18.cs228.ex3.grading.Points;
import edu.iastate.summer18.cs228.ex3.grading.Total;

/**
 * 
 * @author Menglu Yu
 *
 */
@Total(value=24)

public class Ex3_2TestScript {
	
	private Ex3_2 testStr1, testStr2, testStr3;
	LinkedStack<Character> s;

	@Before
	public void setUp() {
		testStr1 = new Ex3_2();
		testStr2 = new Ex3_2();
		testStr3 = new Ex3_2();
		s=new LinkedStack<>();
		
	}

	@After
	public void tearDown() {
		testStr1 = null;
		testStr2 = null;
		testStr3 = null;
		s=null;
	}

	@Test
	@Points(value=8)
	public void palindrome() {
		assertTrue("Anna should be a palindrome word",Ex3_2.palindrome("Anna"));
		assertFalse("length should not be a palindrome word",Ex3_2.palindrome("length"));
	}
	
	@Test
	@Points(value=8)
	public void equals01() {
		assertTrue("0011 equals01 should return true",Ex3_2.equals01("0011"));
		assertFalse("001 equals01 should return false", Ex3_2.equals01("001"));
	}
	
	@Test
	@Points(value=8)
	public void display() {
		s.push('a');
		s.push('a');
		s.push('b');
		System.out.println("the display of stack(a,a,b): ");
		Ex3_2.display(s);
		assertTrue("stack<a,a,b> should pop b",s.pop()=='b');
		assertTrue("peek of stack <a,a> should return a", s.peek()=='a');
	}
	
	

}
