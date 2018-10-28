package edu.iastate.summer18.cs228.hw7;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Before;
import java.awt.Point;
import org.junit.Test;

/**
 * Test cases
 * 
 * @author Alyssa Howe
 */
public class Tree2DTest3 {
	
	Tree2D<Point> pointTree = new Tree2D<>();
	Tree2D<Point> points = new Tree2D<>();
	
	Point[] arr = { new Point(0, 0), new Point(5, -5), new Point(3, -7), new Point(4, -10) };
	
	Tree2D<Point> arrTree = new Tree2D<>(arr);
	
	@Test
	public void testClass() {
		assertEquals(null, pointTree.inorderX());
		assertEquals(null, points.inorderX());
		assertEquals(null, pointTree.inorderY());
		assertEquals(null, points.inorderY());
		
		assertFalse(pointTree.contains(new Point(20, 30)));
		assertFalse(pointTree.contains(new Point(30, 20)));
		assertFalse(points.contains(new Point(20, 30)));
		assertFalse(points.contains(new Point(30, 20)));
		
		double[] res = arrTree.inorderX(); // 4, 3, 5, 0
		double[] res2 = arrTree.inorderY();
		
		assertEquals(0, res[0], .0001);
		assertEquals(3, res[1], .0001);
		assertEquals(4, res[2], .0001);
		assertEquals(5, res[3], .0001);
		
		assertEquals(0, res2[0], .0001);
		assertEquals(-7, res2[1], .0001);
		assertEquals(-10, res2[2], .0001);
		assertEquals(-5, res2[3], .0001);
		
		assertTrue(arrTree.contains(new Point(0, 0)));
		assertTrue(arrTree.contains(new Point(3, -7)));
		assertTrue(arrTree.contains(new Point(4, -10)));
		assertTrue(arrTree.contains(new Point(5, -5)));
		assertFalse(arrTree.contains(new Point(-10, 1)));
		assertFalse(arrTree.contains(new Point(0, 1)));
		assertFalse(arrTree.contains(new Point(3, 3)));
		assertFalse(arrTree.contains(new Point(5, 5)));
		assertFalse(arrTree.contains(new Point(0, -1)));
		
		pointTree.addPoint(new Point(11, 22));
		res = pointTree.inorderX();
		res2 = pointTree.inorderY();
		
		assertEquals(11, res[0], .0001);
		assertEquals(22, res2[0], .0001);
		
		assertTrue(pointTree.contains(new Point(11, 22)));
		assertFalse(pointTree.contains(new Point(11, -22)));
		assertFalse(pointTree.contains(new Point(-11, 22)));
		assertFalse(pointTree.contains(new Point(-11, -22)));
		assertTrue(pointTree.contains(new Point(11, 22)));
		
		pointTree.addPoint(new Point(8, 8));
		
		assertTrue(pointTree.contains(new Point(11, 22)));
		assertFalse(pointTree.contains(new Point(11, -22)));
		assertFalse(pointTree.contains(new Point(-11, 22)));
		assertFalse(pointTree.contains(new Point(-11, -22)));
		assertTrue(pointTree.contains(new Point(11, 22)));
		assertTrue(pointTree.contains(new Point(8, 8)));
		
		res = pointTree.inorderX();
		res2 = pointTree.inorderY();
		
		assertEquals(8, res[0], .0001);
		assertEquals(11, res[1], .0001);
		assertEquals(8, res2[0], .0001);
		assertEquals(22, res2[1], .0001);
		
		pointTree.addPoint(new Point(12, 12));
		
		assertTrue(pointTree.contains(new Point(11, 22)));
		assertFalse(pointTree.contains(new Point(11, -22)));
		assertFalse(pointTree.contains(new Point(-11, 22)));
		assertFalse(pointTree.contains(new Point(-11, -22)));
		assertTrue(pointTree.contains(new Point(11, 22)));
		assertTrue(pointTree.contains(new Point(8, 8)));
		assertTrue(pointTree.contains(new Point(12, 12)));
		
		res = pointTree.inorderX();
		res2 = pointTree.inorderY();
		
		assertEquals(8, res[0], .0001);
		assertEquals(11, res[1], .0001);
		assertEquals(12, res[2], .0001);
		assertEquals(8, res2[0], .0001);
		assertEquals(22, res2[1], .0001);
		assertEquals(12, res2[2], .0001);
		
		points.addPoint(new Point(11, 22));
		points.addPoint(new Point(5, 100));
		
		assertTrue(points.contains(new Point(11, 22)));
		assertTrue(points.contains(new Point(5, 100)));
		assertFalse(points.contains(new Point(22, 11)));
		assertFalse(points.contains(new Point(100, 5)));
		
		res = points.inorderX();
		res2 = points.inorderY();
		
		assertEquals(5, res[0], .0001);
		assertEquals(11, res[1], .0001);
		assertEquals(100, res2[0], .0001);
		assertEquals(22, res2[1], .0001);
		
		points.addPoint(new Point(99, 2));
		
		assertTrue(points.contains(new Point(11, 22)));
		assertTrue(points.contains(new Point(5, 100)));
		assertTrue(points.contains(new Point(99, 2)));
		assertFalse(points.contains(new Point(22, 11)));
		assertFalse(points.contains(new Point(100, 5)));
		assertFalse(points.contains(new Point(2, 99)));
		
		res = points.inorderX();
		res2 = points.inorderY();
		
		assertEquals(5, res[0], .0001);
		assertEquals(11, res[1], .0001);
		assertEquals(100, res2[0], .0001);
		assertEquals(22, res2[1], .0001);
		assertEquals(99, res[2], .0001);
		assertEquals(2, res2[2], .0001);
		
		points.addPoint(new Point(7, 60));
		points.addPoint(new Point(12, 0));
		points.addPoint(new Point(1, 101));
		points.addPoint(new Point(12, 3));
		
		assertTrue(points.contains(new Point(11, 22)));
		assertTrue(points.contains(new Point(5, 100)));
		assertTrue(points.contains(new Point(99, 2)));
		assertTrue(points.contains(new Point(7, 60)));
		assertTrue(points.contains(new Point(12, 0)));
		assertTrue(points.contains(new Point(1, 101)));
		assertTrue(points.contains(new Point(12, 3)));
		
		assertFalse(points.contains(new Point(22, 11)));
		assertFalse(points.contains(new Point(100, 5)));
		assertFalse(points.contains(new Point(2, 99)));
		assertFalse(points.contains(new Point(3, 12)));
		assertFalse(points.contains(new Point(101, 1)));
		assertFalse(points.contains(new Point(0, 12)));
		assertFalse(points.contains(new Point(3, 90)));
		
		res = points.inorderX(); // 7, 5, 1, 11, 8, 99, 8
		res2 = points.inorderY(); // 60, 100, 101, 22, 0, 2, 3
		
		assertEquals(7, res[0], .0001);
		assertEquals(5, res[1], .0001);
		assertEquals(1, res[2], .0001);
		assertEquals(11, res[3], .0001);
		assertEquals(12, res[4], .0001);
		assertEquals(99, res[5], .0001);
		assertEquals(12, res[6], .0001);
		
		assertEquals(60, res2[0], .0001);
		assertEquals(100, res2[1], .0001);
		assertEquals(101, res2[2], .0001);
		assertEquals(22, res2[3], .0001);
		assertEquals(0, res2[4], .0001);
		assertEquals(2, res2[5], .0001);
		assertEquals(3, res2[6], .0001);
		
		Point[] arrStuff = { new Point(11, 22), new Point(12, 12), new Point(2, 3), new Point(4, -500), new Point(14, -10), new Point(13, 70),
				new Point(1, 500), new Point(55, 90) };
		
		arrTree.clear();
		
		arrTree = new Tree2D<>(arrStuff);
		
		assertTrue(arrTree.contains(new Point(11, 22)));
		assertTrue(arrTree.contains(new Point(12, 12)));
		assertTrue(arrTree.contains(new Point(2, 3)));
		assertTrue(arrTree.contains(new Point(4, -500)));
		assertTrue(arrTree.contains(new Point(14, -10)));
		assertTrue(arrTree.contains(new Point(13, 70)));
		assertTrue(arrTree.contains(new Point(1, 500)));
		assertTrue(arrTree.contains(new Point(55, 90)));
		
		assertFalse(arrTree.contains(new Point(5, 1000)));
		assertFalse(arrTree.contains(new Point(13, 2)));
		assertFalse(arrTree.contains(new Point(60, -1)));
		assertFalse(arrTree.contains(new Point(600, 9)));
		
		arrTree.addPoint(new Point(5, 1000));
		arrTree.addPoint(new Point(13, 2));
		arrTree.addPoint(new Point(60, -1));
		arrTree.addPoint(new Point(600, 9));
		
		assertTrue(arrTree.contains(new Point(11, 22)));
		assertTrue(arrTree.contains(new Point(12, 12)));
		assertTrue(arrTree.contains(new Point(2, 3)));
		assertTrue(arrTree.contains(new Point(4, -500)));
		assertTrue(arrTree.contains(new Point(14, -10)));
		assertTrue(arrTree.contains(new Point(13, 70)));
		assertTrue(arrTree.contains(new Point(1, 500)));
		assertTrue(arrTree.contains(new Point(55, 90)));
		
		assertTrue(arrTree.contains(new Point(5, 1000)));
		assertTrue(arrTree.contains(new Point(13, 2)));
		assertTrue(arrTree.contains(new Point(60, -1)));
		assertTrue(arrTree.contains(new Point(600, 9)));
		
		assertFalse(arrTree.contains(new Point(5, 100)));
		assertFalse(arrTree.contains(new Point(13, -22)));
		assertFalse(arrTree.contains(new Point(60, 1)));
		assertFalse(arrTree.contains(new Point(600, -9)));
		
		res = arrTree.inorderX(); // 4, 2, 1, 5, 11, 13, 14, 60, 600, 12, 13, 55
		res2 = arrTree.inorderY(); // -500, 3, 500, -1, 22, 2, -10, -1, 9, 12, 70, -55
		
		assertEquals(4, res[0], .0001);
		assertEquals(2, res[1], .0001);
		assertEquals(1, res[2], .0001);
		assertEquals(5, res[3], .0001);
		assertEquals(11, res[4], .0001);
		assertEquals(13, res[5], .0001);
		assertEquals(14, res[6], .0001);
		assertEquals(60, res[7], .0001);
		assertEquals(600, res[8], .0001);
		assertEquals(12, res[9], .0001);
		assertEquals(13, res[10], .0001);
		assertEquals(55, res[11], .0001);
		
		assertEquals(-500, res2[0], .0001);
		assertEquals(3, res2[1], .0001);
		assertEquals(500, res2[2], .0001);
		assertEquals(1000, res2[3], .0001);
		assertEquals(22, res2[4], .0001);
		assertEquals(2, res2[5], .0001);
		assertEquals(-10, res2[6], .0001);
		assertEquals(-1, res2[7], .0001);
		assertEquals(9, res2[8], .0001);
		assertEquals(12, res2[9], .0001);
		assertEquals(70, res2[10], .0001);
		assertEquals(90, res2[11], .0001);
		
		points.clear();
		
		points.addPoint(new Point(10, 7));
		points.addPoint(new Point(9, 12));
		points.addPoint(new Point(7, 5));
		points.addPoint(new Point(2, 17));
		points.addPoint(new Point(2, -15000));
		
		assertTrue(points.contains(new Point(10, 7)));
		assertTrue(points.contains(new Point(9, 12)));
		assertTrue(points.contains(new Point(7, 5)));
		assertTrue(points.contains(new Point(2, 17)));
		assertTrue(points.contains(new Point(2, -15000)));
		
		assertFalse(points.contains(new Point(-10, 7)));
		assertFalse(points.contains(new Point(9, -12)));
		assertFalse(points.contains(new Point(-7, 5)));
		assertFalse(points.contains(new Point(-2, -17)));
		assertFalse(points.contains(new Point(2, 15000)));
		
		res = points.inorderX(); // 2, 7, 9, 2, 10
		res2 = points.inorderY(); // -15,000, 5, 12, 17, 7
		
		assertEquals(2, res[0], .0001);
		assertEquals(7, res[1], .0001);
		assertEquals(9, res[2], .0001);
		assertEquals(2, res[3], .0001);
		assertEquals(10, res[4], .0001);
		
		assertEquals(-15000, res2[0], .0001);
		assertEquals(5, res2[1], .0001);
		assertEquals(12, res2[2], .0001);
		assertEquals(17, res2[3], .0001);
		assertEquals(7, res2[4], .0001);
		
		points.clear();
		
		points.addPoint(new Point(0, 0));
		points.addPoint(new Point(2, 3));
		points.addPoint(new Point(5, 6));
		
		assertTrue(points.contains(new Point(0, 0)));
		assertTrue(points.contains(new Point(2, 3)));
		assertTrue(points.contains(new Point(5, 6)));
		
		assertFalse(points.contains(new Point(-10, 7)));
		assertFalse(points.contains(new Point(9, -12)));
		assertFalse(points.contains(new Point(-7, 5)));
		assertFalse(points.contains(new Point(-2, -17)));
		assertFalse(points.contains(new Point(2, 15000)));
		
		res = points.inorderX(); // 0, 2, 5
		res2 = points.inorderY(); // 0, 3, 6
		
		assertEquals(0, res[0], .0001);
		assertEquals(2, res[1], .0001);
		assertEquals(5, res[2], .0001);
		
		assertEquals(0, res2[0], .0001);
		assertEquals(3, res2[1], .0001);
		assertEquals(6, res2[2], .0001);
		
		pointTree.clear();
		
		pointTree.addPoint(new Point(57, 58));
		pointTree.addPoint(new Point(60, 7));
		pointTree.addPoint(new Point(40, 20));
		pointTree.addPoint(new Point(30, 22));
		pointTree.addPoint(new Point(80, 60));
		pointTree.addPoint(new Point(47, 15));
		
		assertTrue(pointTree.contains(new Point(57, 58)));
		assertTrue(pointTree.contains(new Point(60, 7)));
		assertTrue(pointTree.contains(new Point(40, 20)));
		assertTrue(pointTree.contains(new Point(30, 22)));
		assertTrue(pointTree.contains(new Point(80, 60)));
		assertTrue(pointTree.contains(new Point(47, 15)));
		
		assertFalse(pointTree.contains(new Point(400, 2)));
		assertFalse(pointTree.contains(new Point(-5, 200)));
		assertFalse(pointTree.contains(new Point(60, -7)));
		assertFalse(pointTree.contains(new Point(0, 2)));
		assertFalse(pointTree.contains(new Point(40, -20)));
		
		res = pointTree.inorderX(); // 47, 40, 30, 57, 60, 80
		res2 = pointTree.inorderY(); //
		
		assertEquals(47, res[0], .0001);
		assertEquals(40, res[1], .0001);
		assertEquals(30, res[2], .0001);
		assertEquals(57, res[3], .0001);
		assertEquals(60, res[4], .0001);
		assertEquals(80, res[5], .0001);
		
		assertEquals(15, res2[0], .0001);
		assertEquals(20, res2[1], .0001);
		assertEquals(22, res2[2], .0001);
		assertEquals(58, res2[3], .0001);
		assertEquals(7, res2[4], .0001);
		assertEquals(60, res2[5], .0001);
		
		pointTree.clear();
		
		pointTree.addPoint(new Point(3, 2));
		pointTree.addPoint(new Point(1, 4));
		pointTree.addPoint(new Point(4, 1));
		pointTree.addPoint(new Point(5, 0));
		pointTree.addPoint(new Point(-1, 7));
		pointTree.addPoint(new Point(-5, -5));
		pointTree.addPoint(new Point(5, 6));
		pointTree.addPoint(new Point(2, 8));
		pointTree.addPoint(new Point(-6, 9));
		
		assertTrue(pointTree.contains(new Point(3, 2)));
		assertTrue(pointTree.contains(new Point(1, 4)));
		assertTrue(pointTree.contains(new Point(4, 1)));
		assertTrue(pointTree.contains(new Point(5, 0)));
		assertTrue(pointTree.contains(new Point(-1, 7)));
		assertTrue(pointTree.contains(new Point(-5, -5)));
		assertTrue(pointTree.contains(new Point(5, 6)));
		assertTrue(pointTree.contains(new Point(2, 8)));
		assertTrue(pointTree.contains(new Point(-6, 9)));
		
		assertFalse(pointTree.contains(new Point(400, 2)));
		assertFalse(pointTree.contains(new Point(-5, 200)));
		assertFalse(pointTree.contains(new Point(60, -7)));
		assertFalse(pointTree.contains(new Point(0, 2)));
		assertFalse(pointTree.contains(new Point(40, -20)));
		
		res = pointTree.inorderX(); // -5, 1, -6, -1, 2, 3, 5, 4, 5
		res2 = pointTree.inorderY(); // -5, 4, 9, 7, 8, 2, 0, 1, 6
		
		assertEquals(-5, res[0], .0001);
		assertEquals(1, res[1], .0001);
		assertEquals(-6, res[2], .0001);
		assertEquals(-1, res[3], .0001);
		assertEquals(2, res[4], .0001);
		assertEquals(3, res[5], .0001);
		assertEquals(5, res[6], .0001);
		assertEquals(4, res[7], .0001);
		assertEquals(5, res[8], .0001);
		
		assertEquals(-5, res2[0], .0001);
		assertEquals(4, res2[1], .0001);
		assertEquals(9, res2[2], .0001);
		assertEquals(7, res2[3], .0001);
		assertEquals(8, res2[4], .0001);
		assertEquals(2, res2[5], .0001);
		assertEquals(0, res2[6], .0001);
		assertEquals(1, res2[7], .0001);
		assertEquals(6, res2[8], .0001);
		
		pointTree.clear();
		
		assertEquals(null, pointTree.inorderX()); // -5, 1, -6, -1, 2, 3, 5, 4, 5
		assertEquals(null, pointTree.inorderY());
		
		pointTree.addPoint(new Point(50, 50));
		pointTree.addPoint(new Point(40, 60));
		pointTree.addPoint(new Point(30, 70));
		pointTree.addPoint(new Point(45, 55));
		pointTree.addPoint(new Point(43, 57));
		pointTree.addPoint(new Point(47, 53));
		pointTree.addPoint(new Point(35, 65));
		pointTree.addPoint(new Point(20, 80));
		pointTree.addPoint(new Point(60, 40));
		pointTree.addPoint(new Point(70, 30));
		pointTree.addPoint(new Point(55, 45));
		pointTree.addPoint(new Point(57, 43));
		pointTree.addPoint(new Point(53, 47));
		pointTree.addPoint(new Point(65, 35));
		pointTree.addPoint(new Point(80, 20));
		
		double[] testTree = pointTree.inorderX(); // 43, 45, 47, 40, 20, 30, 35, 50, 65, 70, 80, 60, 53, 55, 57
		double[] testTree2 = pointTree.inorderY(); // 57, 55, 43, 60, 80, 70, 65, 50, 35, 30, 20, 40, 47, 45, 43
		
		assertEquals(43, testTree[0], .0001);
		assertEquals(45, testTree[1], .0001);
		assertEquals(47, testTree[2], .0001);
		assertEquals(40, testTree[3], .0001);
		assertEquals(20, testTree[4], .0001);
		assertEquals(30, testTree[5], .0001);
		assertEquals(35, testTree[6], .0001);
		assertEquals(50, testTree[7], .0001);
		assertEquals(65, testTree[8], .0001);
		assertEquals(70, testTree[9], .0001);
		assertEquals(80, testTree[10], .0001);
		assertEquals(60, testTree[11], .0001);
		assertEquals(53, testTree[12], .0001);
		assertEquals(55, testTree[13], .0001);
		assertEquals(57, testTree[14], .0001);
		
		assertEquals(57, testTree2[0], .0001);
		assertEquals(55, testTree2[1], .0001);
		assertEquals(53, testTree2[2], .0001);
		assertEquals(60, testTree2[3], .0001);
		assertEquals(80, testTree2[4], .0001);
		assertEquals(70, testTree2[5], .0001);
		assertEquals(65, testTree2[6], .0001);
		assertEquals(50, testTree2[7], .0001);
		assertEquals(35, testTree2[8], .0001);
		assertEquals(30, testTree2[9], .0001);
		assertEquals(20, testTree2[10], .0001);
		assertEquals(40, testTree2[11], .0001);
		assertEquals(47, testTree2[12], .0001);
		assertEquals(45, testTree2[13], .0001);
		assertEquals(43, testTree2[14], .0001);
		
		assertTrue(pointTree.contains(new Point(43, 57)));
		assertTrue(pointTree.contains(new Point(57, 43)));
		assertFalse(pointTree.contains(new Point(27, 73)));
		assertFalse(pointTree.contains(new Point(73, 27)));
		
		pointTree.clear();
		
		pointTree.addPoint(new Point(50, 50));
		pointTree.addPoint(new Point(60, 40));
		pointTree.addPoint(new Point(70, 30));
		pointTree.addPoint(new Point(55, 45));
		pointTree.addPoint(new Point(57, 43));
		pointTree.addPoint(new Point(53, 47));
		pointTree.addPoint(new Point(65, 35));
		pointTree.addPoint(new Point(80, 20));
		pointTree.addPoint(new Point(40, 60));
		pointTree.addPoint(new Point(30, 70));
		pointTree.addPoint(new Point(45, 55));
		pointTree.addPoint(new Point(43, 57));
		pointTree.addPoint(new Point(47, 53));
		pointTree.addPoint(new Point(35, 65));
		pointTree.addPoint(new Point(20, 80));
		
		testTree = pointTree.inorderX(); // 43, 45, 47, 40, 20, 30, 35, 50, 65, 70, 80, 60, 53, 55, 57
		testTree2 = pointTree.inorderY(); // 57, 55, 43, 60, 80, 70, 65, 50, 35, 30, 20, 40, 47, 45, 43
		
		assertEquals(43, testTree[0], .0001);
		assertEquals(45, testTree[1], .0001);
		assertEquals(47, testTree[2], .0001);
		assertEquals(40, testTree[3], .0001);
		assertEquals(20, testTree[4], .0001);
		assertEquals(30, testTree[5], .0001);
		assertEquals(35, testTree[6], .0001);
		assertEquals(50, testTree[7], .0001);
		assertEquals(65, testTree[8], .0001);
		assertEquals(70, testTree[9], .0001);
		assertEquals(80, testTree[10], .0001);
		assertEquals(60, testTree[11], .0001);
		assertEquals(53, testTree[12], .0001);
		assertEquals(55, testTree[13], .0001);
		assertEquals(57, testTree[14], .0001);
		
		assertEquals(57, testTree2[0], .0001);
		assertEquals(55, testTree2[1], .0001);
		assertEquals(53, testTree2[2], .0001);
		assertEquals(60, testTree2[3], .0001);
		assertEquals(80, testTree2[4], .0001);
		assertEquals(70, testTree2[5], .0001);
		assertEquals(65, testTree2[6], .0001);
		assertEquals(50, testTree2[7], .0001);
		assertEquals(35, testTree2[8], .0001);
		assertEquals(30, testTree2[9], .0001);
		assertEquals(20, testTree2[10], .0001);
		assertEquals(40, testTree2[11], .0001);
		assertEquals(47, testTree2[12], .0001);
		assertEquals(45, testTree2[13], .0001);
		assertEquals(43, testTree2[14], .0001);
		
		assertTrue(pointTree.contains(new Point(43, 57)));
		assertTrue(pointTree.contains(new Point(57, 43)));
		assertTrue(pointTree.contains(new Point(50, 50)));
		assertTrue(pointTree.contains(new Point(45, 55)));
		assertTrue(pointTree.contains(new Point(80, 20)));
		assertTrue(pointTree.contains(new Point(20, 80)));
		
		assertFalse(pointTree.contains(new Point(27, 73)));
		assertFalse(pointTree.contains(new Point(73, 27)));
		assertFalse(pointTree.contains(new Point(34, 67)));
		assertFalse(pointTree.contains(new Point(80, 21)));
		
	}
}
