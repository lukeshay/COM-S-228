package edu.iastate.summer18.cs228.ex1;

/**
 * Location class that is used to find the largest double in an array.
 * 
 * @author Robert Shay
 */
public class Location {
	public int row, column;
	public double maxValue;

	/**
	 * Constructs a new Location object with the inputed row, column, and value.
	 * 
	 * @param row
	 * @param column
	 * @param maxValue
	 */
	public Location(int row, int column, double maxValue) {
		this.row = row;
		this.column = column;
		this.maxValue = maxValue;
	}

	/**
	 * Static method that is used to find the largest value of an array.
	 * 
	 * @param a
	 *            Inputed 2D array that is used to find the largest value.
	 * @return
	 */
	public static Location locateLargest(double[][] a) {
		int row = -1, col = -1;
		double max = Double.NEGATIVE_INFINITY;

		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[0].length; j++) {
				if (a[i][j] > max) {
					max = a[i][j];
					row = i;
					col = j;
				}
			}
		}
		return new Location(row, col, max);
	}
}
