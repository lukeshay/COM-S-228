package edu.iastate.summer18.cs228.hw7;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Before;
import java.awt.Point;
import org.junit.Test;

public class Tree2DTest2 {
	Tree2D<Point> tree = new Tree2D<>();
	BinaryNode<Point> root;
	Point p1 = new Point(1, 2);
	Point p2 = new Point(3, 4);
	Point p3 = new Point(-1, 2);
	Point p4 = new Point(5, 2);
	
	@Before
	public void setup() {
		tree.addPoint(p1);
		tree.addPoint(p2);
		tree.addPoint(p3);
		tree.addPoint(p4);
		root = tree.getRootNode();
	}
	
	@Test
	public void addTest() { // This must pass in order for the other tests to be accurate.
		assertEquals(p1, root.getData());
		assertEquals(p2, root.getRightChild().getData());
		assertEquals(p3, root.getLeftChild().getData());
		assertEquals(p4, root.getRightChild().getLeftChild().getData());
	}
	
	@Test
	public void inorderTest() {
		double[] xCoor = tree.inorderX(); // -1, 1, 5, 3,
		double[] yCoor = tree.inorderY(); // 2, 2, 2, 4
		
		assertEquals(p3.x, (int) xCoor[0]);
		assertEquals(p1.x, (int) xCoor[1]);
		assertEquals(p4.x, (int) xCoor[2]);
		assertEquals(p2.x, (int) xCoor[3]);
		
		/*
		 * assertEquals(p2.x, (int) xCoor[2]); assertEquals(p4.x, (int) xCoor[3]);
		 */
		
		assertEquals(p3.y, (int) yCoor[0]);
		assertEquals(p1.y, (int) yCoor[1]);
		assertEquals(p4.y, (int) yCoor[2]);
		assertEquals(p2.y, (int) yCoor[3]);
		
		tree.clear();
		boolean inorderXReturnsNull = false;
		try {
			int temp = tree.inorderX().length;
		}
		catch (NullPointerException ex) {
			inorderXReturnsNull = true;
		}
		
		boolean inorderYReturnsNull = false;
		try {
			int temp = tree.inorderY().length;
		}
		catch (NullPointerException ex) {
			inorderYReturnsNull = true;
		}
		
		assertTrue(inorderXReturnsNull);
		assertTrue(inorderYReturnsNull);
	}
	
	@Test
	public void arrayConstructorTest() {
		Point[] points = { p1, p2, p3, p4 };
		tree.clear();
		tree = new Tree2D<>(points);
		assertEquals(p1, root.getData());
		assertEquals(p2, root.getRightChild().getData());
		assertEquals(p3, root.getLeftChild().getData());
		assertEquals(p4, root.getRightChild().getLeftChild().getData());
	}
	
	@Test
	public void biggerTreeTests() {
		Point p5 = new Point(4, 7);
		Point p6 = new Point(-3, 5);
		Point p7 = new Point(-2, -1);
		Point p8 = new Point(10, -8);
		
		tree.addPoint(p5);
		tree.addPoint(p6);
		tree.addPoint(p7);
		tree.addPoint(p8);
		
		root = tree.getRootNode();
		
		assertEquals(p1, root.getData());
		assertEquals(p2, root.getRightChild().getData());
		assertEquals(p3, root.getLeftChild().getData());
		assertEquals(p4, root.getRightChild().getLeftChild().getData());
		assertEquals(p5, root.getRightChild().getRightChild().getData());
		assertEquals(p6, root.getLeftChild().getRightChild().getData());
		assertEquals(p7, root.getLeftChild().getLeftChild().getData());
		assertEquals(p8, root.getRightChild().getLeftChild().getRightChild().getData());
		
		Point[] arr = { p1, p2, p3, p4, p5, p6, p7, p8 };
		tree.clear();
		tree = new Tree2D<>(arr);
		assertEquals(p1, root.getData());
		assertEquals(p2, root.getRightChild().getData());
		assertEquals(p3, root.getLeftChild().getData());
		assertEquals(p4, root.getRightChild().getLeftChild().getData());
		assertEquals(p5, root.getRightChild().getRightChild().getData());
		assertEquals(p6, root.getLeftChild().getRightChild().getData());
		assertEquals(p7, root.getLeftChild().getLeftChild().getData());
		assertEquals(p8, root.getRightChild().getLeftChild().getRightChild().getData());
		
		for (Point p : arr)
			assertTrue(tree.contains(p));
		
		/* assertFalse(tree.contains(null)); */
		assertFalse(tree.contains(new Point(100, 100)));
	}
	
	@Test
	public void containsTest() {
		Point p5 = new Point(4, 3);
		tree.addPoint(p5);
		assertTrue(tree.contains(p1));
		assertTrue(tree.contains(p2));
		assertTrue(tree.contains(p3));
		assertTrue(tree.contains(p4));
		assertTrue(tree.contains(p5));
		assertTrue(tree.contains(new Point(1, 2)));
		assertFalse(tree.contains(new Point(1, 100)));
	}
	
}
