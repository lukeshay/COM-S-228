package edu.iastate.summer18.cs228.ex3.grading;

import java.io.FileNotFoundException;


public class AutomatedJUnitRunner {
	public static void main(String[] args) throws FileNotFoundException {
		
		double topTotal = 100;
		//new Grader(AccountTestScript.class, "Account Test Script").run();
		new Grader(LinkedBagTestScript.class, "LinkedBagTestScript").run();
		new Grader(ResizableArrayBagTestScript.class, "ResizableArrayBagTestScript").run();
		new Grader(Ex3_2TestScript.class, "Ex3_2TestScript").run();
		
		// Total print out
		double total = Grader.getTotal();
        double studentTotal = Grader.getStudentTotal();
        double normalized = (studentTotal / total) * topTotal;
        System.out.println();
        if (total != topTotal)
            System.out.println(String.format("Raw Total Normalized Scores: %.2f/%.2f",
                studentTotal, total));
        System.out.println(String.format("Normalized Total Score: %.2f/%.2f", normalized, topTotal));
        
        System.out.println("Manual Deductions:        ");
        System.out.println(String.format("Normalized Final Score: %.2f/%.2f", normalized, topTotal));
        
        System.out.println("----------------------------------");
        System.out.println("Graded by Menglu Yu");
        System.out.println("Email: mengluy@iastate.edu");
        System.out.println("");
        System.out.println("Office Hours: Mondays 8:45-10:45AM Atanasoff BO2");
        System.out.println("----------------------------------");

        System.exit(0);
	}
}
