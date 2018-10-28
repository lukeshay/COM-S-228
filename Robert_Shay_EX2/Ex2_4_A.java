package edu.iastate.summer18.cs228.ex2;
/**
 * This class analyzes the execution times of different loops then outputs the time to the console.
 * @author Robert Shay
 *
 */
public class Ex2_4_A {
	public static void main(String[] args) {
		long n = 100000;
		while (n <= 700000) {
			System.out.println("Execution time for algorithmA for n = " + n + " is " + algorithmA(n) + " nanoseconds.");
			n += 100000;
		}
		n = 100000;
		while (n <= 700000) {
			System.out
					.println("Execution time for algorithmB for n = " + n + " is " + algorithmB(n) + " milliseconds.");
			n += 100000;
		}
		 n = 100000;
		while (n <= 700000) {
			System.out
					.println("Execution time for algorithmC for n = " + n + " is " + algorithmC(n) + " nanoseconds.");
			n += 100000;
		}
	}

	public static long algorithmA(long n) {
		long start = System.nanoTime();
		long sum = 0;

		for (long i = 1; i <= n; i++) {
			sum += i;
		}

		return System.nanoTime() - start;
	}

	public static long algorithmB(long n) {
		long start = System.currentTimeMillis();
		long sum = 0;

		for (long i = 1; i <= n; i++) {
			for (long j = 1; j <= i; j++)
				sum += 1;
		}

		return System.currentTimeMillis() - start;
	}

	public static long algorithmC(long n) {
		long start = System.nanoTime();
		long sum = n * (n + 1) / 2;

		return System.nanoTime() - start;
	}
/*Execution time for algorithmA for n = 100000 is 1226435 nanoseconds.
Execution time for algorithmA for n = 200000 is 431129 nanoseconds.
Execution time for algorithmA for n = 300000 is 324004 nanoseconds.
Execution time for algorithmA for n = 400000 is 162264 nanoseconds.
Execution time for algorithmA for n = 500000 is 190883 nanoseconds.
Execution time for algorithmA for n = 600000 is 197448 nanoseconds.
Execution time for algorithmA for n = 700000 is 182219 nanoseconds.
Execution time for algorithmB for n = 100000 is 1299 milliseconds.
Execution time for algorithmB for n = 200000 is 5157 milliseconds.
Execution time for algorithmB for n = 300000 is 23218 milliseconds.
Execution time for algorithmB for n = 400000 is 41326 milliseconds.
Execution time for algorithmB for n = 500000 is 64523 milliseconds.
Execution time for algorithmB for n = 600000 is 92857 milliseconds.
Execution time for algorithmB for n = 700000 is 126520 milliseconds.
Execution time for algorithmC for n = 100000 is 525 nanoseconds.
Execution time for algorithmC for n = 200000 is 262 nanoseconds.
Execution time for algorithmC for n = 300000 is 262 nanoseconds.
Execution time for algorithmC for n = 400000 is 263 nanoseconds.
Execution time for algorithmC for n = 500000 is 0 nanoseconds.
Execution time for algorithmC for n = 600000 is 0 nanoseconds.
Execution time for algorithmC for n = 700000 is 0 nanoseconds.*/
}
