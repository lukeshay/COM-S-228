/**
 * 
 */
package edu.iastate.summer18.cs228.hw5;

import java.util.ListIterator;

/**
 * @author Robert Shay
 */
public class AdaptiveListTest {
	public static void main(String[] args) {
		AdaptiveList<String> seq = new AdaptiveList<String>();
		seq.add("B");
		seq.add("A");
		seq.add("C");
		System.out.println("After the three seq.add() operations:");
		System.out.println("linkedUTD: " + seq.getlinkedUTD());
		System.out.println("arrayUTD: " + seq.getarrayUTD());
		System.out.println(seq.toString());
		System.out.println(seq.get(1));
		System.out.println("After the seq.get(1) operation:");
		System.out.println("linkedUTD: " + seq.getlinkedUTD());
		System.out.println("arrayUTD: " + seq.getarrayUTD());
		System.out.println(seq.toString());
		System.out.println(seq.set(1, "D"));
		System.out.println("After the seq.set(1, 'D') operation:");
		System.out.println("linkedUTD: " + seq.getlinkedUTD());
		System.out.println("arrayUTD: " + seq.getarrayUTD());
		System.out.println(seq.toString());
		seq.add("E");
		System.out.println("After the seq.add('E') operation:");
		System.out.println("linkedUTD: " + seq.getlinkedUTD());
		System.out.println("arrayUTD: " + seq.getarrayUTD());
		System.out.println(seq.toString());
		System.out.println("Any change in array: " + seq.reverse());
		System.out.println("linkedUTD: " + seq.getlinkedUTD());
		System.out.println("arrayUTD: " + seq.getarrayUTD());
		System.out.println(seq.toString());
		
		ListIterator<String> itr = seq.listIterator();
		
		System.out.println("\n\n" + itr.nextIndex());
		System.out.println(itr.hasNext());
		//itr.remove();
		//itr.set("K");
		itr.add("O");
		System.out.println(seq.toString());
		System.out.println(itr.next());
		System.out.println(itr.next());
		System.out.println(itr.next());
		System.out.println(itr.next());
		//System.out.println(itr.next());
		//System.out.println(itr.previous());
		System.out.println(itr.previous());
		System.out.println(itr.nextIndex());
		System.out.println(itr.previousIndex());
		itr.set("Z");
		System.out.println(seq.toString());
		System.out.println(itr.next());
		itr.add("Q");
		System.out.println(seq.toString());
		//itr.set("K");
		System.out.println(itr.previous());
		itr.set("K");
		System.out.println(seq.toString());
		System.out.println(itr.nextIndex());
		System.out.println(itr.previousIndex());
		System.out.println(itr.next());
	}
	
/*After the three seq.add() operations:
linkedUTD: true
arrayUTD: false
A sequence of items from the most recent array:
[]
A sequence of items from the most recent linked list:
(B, A, C)
A
After the seq.get(1) operation:
linkedUTD: true
arrayUTD: true
A sequence of items from the most recent array:
[B, A, C]
A sequence of items from the most recent linked list:
(B, A, C)
A
After the seq.set(1, 'D') operation:
linkedUTD: false
arrayUTD: true
A sequence of items from the most recent array:
[B, D, C]
A sequence of items from the most recent linked list:
(B, A, C)
After the seq.add('E') operation:
linkedUTD: true
arrayUTD: false
A sequence of items from the most recent array:
[B, D, C]
A sequence of items from the most recent linked list:
(B, D, C, E)
Any change in array: true
linkedUTD: false
arrayUTD: true
A sequence of items from the most recent array:
[E, D, C, B]
A sequence of items from the most recent linked list:
(B, D, C, E)


0
true
A sequence of items from the most recent array:
[E, D, C, B]
A sequence of items from the most recent linked list:
(O, E, D, C, B)
E
D
C
B
B
4
3
A sequence of items from the most recent array:
[E, D, C, B]
A sequence of items from the most recent linked list:
(O, E, D, C, Z)
Z
A sequence of items from the most recent array:
[E, D, C, B]
A sequence of items from the most recent linked list:
(O, E, D, C, Z, Q)
Q
A sequence of items from the most recent array:
[E, D, C, B]
A sequence of items from the most recent linked list:
(O, E, D, C, Z, K)
5
4
K
*/
}
