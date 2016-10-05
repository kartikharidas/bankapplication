package com.capgemini.service;

import com.capgemini.exception.DuplicateAccountException;
import com.capgemini.exception.InsufficientBalanceException;
import com.capgemini.exception.InsufficientInitialBalanceException;
import com.capgemini.exception.InvalidAccountNumberException;
import com.capgemini.exception.InvalidAmountException;
import com.capgemini.model.Account;
import com.capgemini.model.Customer;
import com.capgemini.repository.BankRepository;
import com.capgemini.repository.BankRepositoryImpl;

public class BankServiceImpl implements BankService {

	BankRepository repository = new BankRepositoryImpl();
	
	@Override
	public String getBalance(Account account) throws InvalidAccountNumberException {
		return repository.showBalance(account);
	}

	@Override
	public Boolean withdraw(int amount, Account account) throws InvalidAccountNumberException, InsufficientBalanceException, InvalidAmountException {
		return repository.withDraw(amount, account);
	}

	@Override
	public Boolean deposit(int amount, Account account) throws InvalidAccountNumberException {
		return repository.deposit(amount, account);
	}

	@Override
	public Boolean createAccount(int initialBalance, Account account, Customer customer) throws DuplicateAccountException, InsufficientInitialBalanceException, InvalidAccountNumberException {
		return repository.save(initialBalance, account, customer);
	}

	@Override
	public Boolean transer(int amount, Account sourceAccount, Account destinationAccount) throws InsufficientBalanceException, InvalidAccountNumberException, InvalidAmountException {
		return repository.transfer(amount, sourceAccount, destinationAccount);
	}
}
