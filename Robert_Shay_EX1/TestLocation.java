package edu.iastate.summer18.cs228.ex1;
/**
 * @author Robert Shay
 */
import java.util.Scanner;

public class TestLocation {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		System.out.print("Enter the number of rows and columns in the array: ");
		double arr[][] = new double[s.nextInt()][s.nextInt()];
		
		System.out.println("\nEnter the array: ");
		
		for(int i = 0; i < arr.length; i ++) {
			for(int j = 0; j < arr[0].length; j ++) {
				arr[i][j] = s.nextDouble();
			}
		}
		
		System.out.println("The location of the largest element is "  + Location.locateLargest(arr).maxValue + " at (" + Location.locateLargest(arr).row + ", " + Location.locateLargest(arr).column + ").");
		
		s.close();
	}
}
