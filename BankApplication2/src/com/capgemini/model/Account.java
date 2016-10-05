package com.capgemini.model;

import java.util.ArrayList;
import java.util.List;

public class Account {
	
	private int accountNumber;
	private int balance;
	private static List<Integer> accountNumberList = new ArrayList<Integer>();

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public static List<Integer> getAccountNumberList() {
		return accountNumberList;
	}

	public static void setAccountNumberList(List<Integer> accountNumberList) {
		Account.accountNumberList = accountNumberList;
	}
}
