package edu.iastate.summer18.cs228.ex5;

import java.util.Arrays;

/**
 * Simple sorting algorithms' implementations. Note that it is fine to create
 * private helper methods inside this class. However, you are not allowed to
 * have an instance or class variables.
 * 
 * @author Robert Shay
 */

public class SimpleSorts {
	public static void main(String[] args) {
		int[] arr1 = {1,4,5,2,8,2};
		int[] arr2 = {1,4,5,2,8,2};
		int[] arr3 = {1,4,5,2,8,2};
		int[] arr4 = {1,4,5,2,8,2};
		selectionSortRec(arr1);
		insertionSortRec(arr2);
		bubbleSortItr(arr3);
		bubbleSortRec(arr4);

		System.out.println(Arrays.toString(arr1));
		System.out.println(Arrays.toString(arr2));
		System.out.println(Arrays.toString(arr3));
		System.out.println(Arrays.toString(arr4));
		
	}

	/**
	 * Sorts entries of array "arr" using recursive selection sort algorithm in
	 * nondecreasing order. You can assume that array won't be null and will have at
	 * least 2 elements.
	 */
	public static void selectionSortRec(int[] arr) {
		selectionSort(arr, 0);
	}

	/**
	 * Sorts entries of array "arr" using recursive insertion sort algorithm in
	 * nondecreasing order. You can assume that array won't be null and will have at
	 * least 2 elements.
	 */
	public static void insertionSortRec(int[] arr) {
		insertionSort(arr, arr.length);
	}

	/**
	 * A bubble sort can sort an array of n entries into nondecreasing order by
	 * making n-1 passes through the array. On each pass, it compares adjacent
	 * entries and swaps them if they are out of order. For example, on the first
	 * pass, it compares the first and second entries, then the second and third
	 * entries, and so on. At the end of the first pass, the largest entry is in its
	 * proper position at the end of the array. We say that it has bubbled to its
	 * correct spot. Each subsequent pass ignores the entries at the end of the
	 * array, since they are sorted and are larger than any of the remaining
	 * entries. Thus, each pass makes one fewer comparison than the previous pass.
	 * Here is an example of a bubble sort. (Numbers in parentheses represent sorted
	 * subarray.) Original array: 8 2 6 4 9 7 1 After pass 1 : 2 6 4 8 7 1 (9) After
	 * pass 2 : 2 4 6 7 1 (8 9) After pass 3 : 2 4 6 1 (7 8 9) After pass 4 : 2 4 1
	 * (6 7 8 9) After pass 5 : 2 1 (4 6 7 8 9) After pass 6 : 1 (2 4 6 7 8 9) Here
	 * is the detail of pass 1. (Square brackets indicate currently compared entries
	 * of array.) Original array: 8 2 6 4 9 7 1 Step 1 : [8 2] 6 4 9 7 1 compare 1st
	 * and 2nd; swap Step 2 : 2 [8 6] 4 9 7 1 compare 2nd and 3rd; swap Step 3 : 2 6
	 * [8 4] 9 7 1 compare 3rd and 4th; swap Step 4 : 2 6 4 [8 9] 7 1 compare 4th
	 * and 5th; no swap Step 5 : 2 6 4 8 [9 7] 1 compare 5th and 6th; swap Step 6 :
	 * 2 6 4 8 7 [9 1] compare 6th and 7th; swap Step 7 : 2 6 4 8 7 1 9 steps
	 * repeated at next pass. For more info check:
	 * https://en.wikipedia.org/wiki/Bubble_sort Note: It is OK to reuse their
	 * pseudocode ideas.
	 */

	/**
	 * Sorts entries of array "arr" using iterative bubble sort algorithm in
	 * nondecreasing order. You can assume that array won't be null and will have at
	 * least 2 elements.
	 */
	public static void bubbleSortItr(int[] arr) {
		int n = arr.length;
		for (int i = 0; i < n - 1; i++)
			for (int j = 0; j < n - i - 1; j++)
				if (arr[j] > arr[j + 1]) {
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
	}

	/**
	 * Sorts entries of array "arr" using recursive bubble sort algorithm in
	 * nondecreasing order. You can assume that array won't be null and will have at
	 * least 2 elements.
	 */
	public static void bubbleSortRec(int[] arr) {
		bubbleSort(arr, arr.length);
	}
	
	private static void selectionSort(int[] arr, int n) {
		if (n >= arr.length - 1) return;
		int minIndex = n;
		for (int index = n + 1; index < arr.length; index++) {
			if (arr[index] < arr[minIndex]) minIndex = index;
		}
		int temp = arr[n];
		arr[n] = arr[minIndex];
		arr[minIndex] = temp;
		selectionSort(arr, n + 1);
	}
	
	private static void insertionSort(int[] arr, int n) {
		if (n <= 1) return;

		insertionSort(arr, n - 1);

		int last = arr[n - 1];
		int j = n - 2;
		while (j >= 0 && arr[j] > last) {
			arr[j + 1] = arr[j];
			j--;
		}
		arr[j + 1] = last;
	}
	
	private static void bubbleSort(int[] arr, int n) {
		if (n == 1) return;
		for (int i = 0; i < n - 1; i++)
			if (arr[i] > arr[i + 1]) {
				int temp = arr[i];
				arr[i] = arr[i + 1];
				arr[i + 1] = temp;
			}

		bubbleSort(arr, n - 1);
	}
}
