package edu.iastate.summer18.cs228.ex1;

/**
 * Class that is used to remove duplicate Integers from an ArrayList.
 * 
 * @author Robert Shay
 */
import java.util.ArrayList;

public class Helper {
	/**
	 * Removes duplicate numbers from the inputed list.
	 * 
	 * @param list
	 */
	public static void removeDuplicate(ArrayList<Integer> list) {
		for (int i = 0; i < list.size(); i++) {
			for (int j = i + 1; j < list.size(); j++) {
				if (list.get(i) == list.get(j)) {
					list.remove(j);
					j--;
				}
			}
		}
	}
}
