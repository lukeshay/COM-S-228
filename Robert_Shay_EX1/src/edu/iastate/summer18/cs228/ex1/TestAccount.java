package edu.iastate.summer18.cs228.ex1;
/**
 * @author Robert Shay
 */
public class TestAccount {
	public static void main(String[] args) {
		Account acc = new Account(1122, 20000);
		acc.setAnnualInterestRate(4.5);
		
		acc.withdraw(2500);
		
		acc.deposit(3000);
		
		System.out.println("Balance = " + acc.getBalance() + "\nMonthly Interest Rate = " + acc.getMonthlyInterestRate() + "\nDate Created = " + acc.getDateCreated());
		
	}
}
