package edu.iastate.summer18.cs228.ex1;
/**
 * An account object that is used to create accounts and store the ID, balance,
 * date created, and annual interest rate. The balance, ID, and monthly interest
 * rate can be modified.
 * 
 * @author Robert Shay
 */

import java.util.Date;

public class Account {
	/**
	 * int variable used to hold the ID number of the account.
	 */
	private int id;
	/**
	 * double variable used to hold the balance of the account.
	 */
	private double balance;
	/**
	 * double variable used to hold the annual interest rate for the account.
	 */
	private double annualInterestRate;
	/**
	 * Date variable used to hold the date the account is created.
	 */
	private Date dateCreated;

	/*
	 * Constructor for a new account. Sets the date of the account.
	 */
	public Account() {
		dateCreated = new Date();
	}

	/**
	 * Constructor for a new account. Sets the ID number, balance and date.
	 * 
	 * @param id
	 * @param balance
	 */
	public Account(int id, double balance) {
		this.id = id;
		this.balance = balance;
		dateCreated = new Date();
	}

	/*
	 * Sets the ID number of the account.
	 */
	public void setID(int id) {
		this.id = id;
	}

	/**
	 * Sets the balance of the account.
	 * 
	 * @param balance
	 */
	public void setBalance(double balance) {
		this.balance = balance;
	}

	/**
	 * Sets the annual interest rate of the account.
	 */
	public void setAnnualInterestRate(double annualInterestRate) {
		this.annualInterestRate = annualInterestRate;
	}

	/**
	 * Returns the ID of the account.
	 * 
	 * @return
	 */
	public int getID() {
		return id;
	}

	/**
	 * Returns the current balance of the account.
	 * 
	 * @return
	 */
	public double getBalance() {
		return balance;
	}

	/**
	 * Returns the annual interest rate of the account
	 * 
	 * @return
	 */
	public double getAnnualInterestRate() {
		return annualInterestRate;
	}

	/**
	 * Returns the date that the account was created.
	 * 
	 * @return
	 */
	public Date getDateCreated() {
		return dateCreated;
	}

	/**
	 * Returns the monthly interest rate of the account.
	 * 
	 * @return
	 */
	public double getMonthlyInterestRate() {
		return annualInterestRate / 12;
	}

	/**
	 * Returns the monthly interest for the account.
	 * 
	 * @return
	 */
	public double getMonthlyInterest() {
		return getMonthlyInterestRate() / 100 * balance;
	}

	/**
	 * Withdraws a specified amount from the account.
	 * 
	 * @param amount
	 */
	public void withdraw(double amount) {
		balance -= amount;
	}

	/**
	 * Deposits a specified amount into the account.
	 * 
	 * @param amount
	 */
	public void deposit(double amount) {
		balance += amount;
	}
}
