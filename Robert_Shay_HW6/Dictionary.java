package edu.iastate.summer18.cs228.hw6;

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;

/**
 * A class that reads a inputed file and performs what is on it.
 * 
 * @author Robert Shay
 */
public class Dictionary {
	public static void main(String[] args) throws FileNotFoundException {
		EntryTree<Character, String> tree = new EntryTree<Character, String>();
		File inputFile = new File(args[0]);
		
		Scanner sc = new Scanner(inputFile);
		while (sc.hasNextLine()) {
			String nextLine = sc.nextLine();
			String[] arr = nextLine.split("\\s+");
			if (arr[0].equalsIgnoreCase("add"))
				System.out.println("Command: add " + arr[1] + "  " + arr[2] + "\nResult from an add: " + tree.add(toArray(arr[1]), arr[2]));
			else if (arr[0].equalsIgnoreCase("remove"))
				System.out.println("Command: remove  " + arr[1] + "\nResult from a remove: " + tree.remove(toArray(arr[1])));
			else if (arr[0].equalsIgnoreCase("showTree")) {
				System.out.println("Command: showTree\n\nResult from a showTree: ");
				tree.showTree();
			}
			else if (arr[0].equalsIgnoreCase("search"))
				System.out.println("Command: search  " + arr[1] + "\nResult from a search: " + tree.search(toArray(arr[1])));
			else if (arr[0].equalsIgnoreCase("prefix")) {
				Character[] chars = tree.prefix(toArray(sc.next()));
				System.out.println("Command: prefix  " + arr[1]);
				System.out.print("Result from a prefix: ");
				for (int i = 0; i < chars.length; i++)
					System.out.print(chars[i]);
			}
			System.out.println("");
		}
		sc.close();
	}
	
	private static Character[] toArray(String s) {
		if (s == null) { return null; }
		Character[] array = new Character[s.length()];
		for (int i = 0; i < s.length(); i++)
			array[i] = new Character(s.charAt(i));
		
		return array;
	}
	
}