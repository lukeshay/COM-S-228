package edu.iastate.summer18.cs228.hw7;

import java.awt.Point;

/**
 * A demo to test the methods addPoint and contains of the class Tree2D.
 */
public class DemoTest
{
	public static void main(String[] args)
	{
		Tree2D<Point> pointTree = new Tree2D<>();

		pointTree.addPoint(new Point(50, 40));
		pointTree.addPoint(new Point(40, 70));
		pointTree.addPoint(new Point(80, 20));
		pointTree.addPoint(new Point(90, 10));
		pointTree.addPoint(new Point(60, 30));

		System.out.println("Tree contains " + pointTree.getNumberOfNodes() + " nodes. (should be 5)");
		System.out
				.println("Is the point (80, 20) in the tree?\t" + pointTree.contains(new Point(80, 20)) + "\t(should be true)");
		System.out
				.println("Is the point (120, 20) in the tree?\t" + pointTree.contains(new Point(120, 20)) + "\t(should be false)");
	} // end main
} // end Driver

/*
 * Tree contains 5 nodes. (should be 5) Is the point (80, 20) in the tree? true
 * (should be true) Is the point (120, 20) in the tree? false (should be false)
 */
