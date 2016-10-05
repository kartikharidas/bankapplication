package com.capgemini.repository;

import com.capgemini.exception.DuplicateAccountException;
import com.capgemini.exception.InsufficientBalanceException;
import com.capgemini.exception.InsufficientInitialBalanceException;
import com.capgemini.exception.InvalidAccountNumberException;
import com.capgemini.exception.InvalidAmountException;
import com.capgemini.model.Account;
import com.capgemini.model.Customer;

public interface BankRepository {

	public Boolean save(int initialBalance, Account account, Customer customer) throws DuplicateAccountException, InsufficientInitialBalanceException, InvalidAccountNumberException;
	public Boolean withDraw(int amount, Account account) throws InvalidAccountNumberException, InvalidAmountException, InsufficientBalanceException;
	public Boolean deposit(int amount, Account account) throws InvalidAccountNumberException;
	public String showBalance(Account account) throws InvalidAccountNumberException;
	public Boolean transfer(int amount, Account sourceAccount, Account destinationAccount) throws InsufficientBalanceException, InvalidAccountNumberException, InvalidAmountException;

}
