package com.capgemini.repository;

import com.capgemini.exception.DuplicateAccountException;
import com.capgemini.exception.InsufficientBalanceException;
import com.capgemini.exception.InsufficientInitialBalanceException;
import com.capgemini.exception.InvalidAccountNumberException;
import com.capgemini.exception.InvalidAmountException;
import com.capgemini.model.Account;
import com.capgemini.model.Customer;

public class BankRepositoryImpl implements BankRepository {

	@Override
	public Boolean save(int initialBalance, Account account, Customer customer)
			throws DuplicateAccountException,
			InsufficientInitialBalanceException, InvalidAccountNumberException {
		
		Boolean result = false;
		
		if(initialBalance < 500){
			throw new InsufficientInitialBalanceException();
		} 
		
		if (account.getAccountNumber() < 0){
			throw new InvalidAccountNumberException();
		}
		
		int accountNumber = account.getAccountNumber();
		
		for(int accNumber : Account.getAccountNumberList()){
			if (accountNumber == accNumber){
				throw new DuplicateAccountException();
			}
		}
		
		Account.getAccountNumberList().add(accountNumber);
		result = true;
		return result;
	}

	@Override
	public Boolean withDraw(int amount, Account account)
			throws InvalidAccountNumberException, InvalidAmountException, InsufficientBalanceException {
		
		 boolean result = false;
		
		if(amount < 0){
			throw new InvalidAmountException();
		}
				
		if(account.getAccountNumber() < 0){
			throw new InvalidAccountNumberException();
		}
		
		if ((account.getBalance() - amount) < 0){
			throw new InsufficientBalanceException();
		}
		
		account.setBalance(account.getBalance() - amount);
		result = true;
		
		return result;
	}

	@Override
	public Boolean deposit(int amount, Account account)
			throws InvalidAccountNumberException {

		boolean result = false;
		
		if(amount < 0){
			throw new IllegalArgumentException();
		}
		
		if(account.getAccountNumber() < 0){
			throw new InvalidAccountNumberException();
		}
		
		int balance = account.getBalance();
		account.setBalance(balance + amount);
		result = true;
		
		return result;
	}

	@Override
	public String showBalance(Account account)
			throws InvalidAccountNumberException {
		
		if(account.getAccountNumber() < 0){
			throw new InvalidAccountNumberException();
		}
		else 
			return Integer.valueOf(account.getBalance()).toString();
	}

	@Override
	public Boolean transfer(int amount, Account sourceAccount,
			Account destinationAccount) throws InsufficientBalanceException,
			InvalidAccountNumberException, InvalidAmountException {
		
		boolean result = false;
		
		if(amount < 0){
			throw new InvalidAmountException();
		}
		
		if(sourceAccount.getAccountNumber() < 0 || destinationAccount.getAccountNumber()<0){
			throw new InvalidAccountNumberException();
		}
		
		if(sourceAccount.getBalance() - amount < 0){
			throw new InsufficientBalanceException();
		}
		
		sourceAccount.setBalance(sourceAccount.getBalance() - amount);
		destinationAccount.setBalance(destinationAccount.getBalance() + amount);
		
		result = true;
		return result;
	}
	
	
}
