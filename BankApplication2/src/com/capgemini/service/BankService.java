package com.capgemini.service;

import com.capgemini.exception.DuplicateAccountException;
import com.capgemini.exception.InsufficientBalanceException;
import com.capgemini.exception.InsufficientInitialBalanceException;
import com.capgemini.exception.InvalidAccountNumberException;
import com.capgemini.exception.InvalidAmountException;
import com.capgemini.model.Account;
import com.capgemini.model.Customer;

public interface BankService {
	
	public String getBalance(Account account) throws InvalidAccountNumberException ;
	public Boolean withdraw(int amount, Account account) throws InvalidAccountNumberException, InsufficientBalanceException, InvalidAmountException;
	public Boolean deposit(int amount, Account account) throws InvalidAccountNumberException;
	public Boolean createAccount(int initialBalance, Account account, Customer customer) throws DuplicateAccountException, InsufficientInitialBalanceException, InvalidAccountNumberException;
	public Boolean transer(int amount, Account sourceAccount, Account destinationAccount) throws InsufficientBalanceException, InvalidAccountNumberException, InvalidAmountException; 
}
