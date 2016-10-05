package com.capgemini.BankRepository.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.capgemini.exception.DuplicateAccountException;
import com.capgemini.exception.InsufficientBalanceException;
import com.capgemini.exception.InsufficientInitialBalanceException;
import com.capgemini.exception.InvalidAccountNumberException;
import com.capgemini.exception.InvalidAmountException;
import com.capgemini.model.Account;
import com.capgemini.model.Customer;
import com.capgemini.repository.BankRepository;
import com.capgemini.repository.BankRepositoryImpl;

public class BankRepositoryTest {

	BankRepository bankRepository = new BankRepositoryImpl();
	
	@Test(expected = com.capgemini.exception.InsufficientInitialBalanceException.class)
	public void testInsufficientInitialBalanceOnCreate() throws DuplicateAccountException, InsufficientInitialBalanceException, InvalidAccountNumberException {
		Account account = new Account();
		Customer customer = new Customer();
		bankRepository.save(400, account, customer);
	}
	
	@Test(expected = com.capgemini.exception.InvalidAccountNumberException.class)
	public void testInvalidAccountNumberExceptionOnCreate() throws DuplicateAccountException, InsufficientInitialBalanceException, InvalidAccountNumberException {
		
		Account account = new Account();
		account.setAccountNumber(-1);
		
		Customer customer = new Customer();
		customer.setCustomerID(1);
		customer.setName("Kartik");
		
		bankRepository.save(500, account, customer);
	}
	
	@Test(expected = com.capgemini.exception.DuplicateAccountException.class)
	public void testDuplicateAccountExceptionOnCreate() throws DuplicateAccountException, InsufficientInitialBalanceException, InvalidAccountNumberException {
		
		Account account = new Account();
		account.setAccountNumber(1);
		
		Customer customer = new Customer();
		customer.setCustomerID(1);
		customer.setName("Kartik");
		
		bankRepository.save(500, account, customer);
		bankRepository.save(500, account, customer);
	}
	
	@Test
	public void testSuccesfulSave() throws DuplicateAccountException, InsufficientInitialBalanceException, InvalidAccountNumberException {
		
		Account account = new Account();
		account.setAccountNumber(2);
		
		Customer customer = new Customer();
		customer.setCustomerID(2);
		customer.setName("Kartik");
		
		assertEquals(bankRepository.save(500, account, customer), true);
	}
	
	@Test(expected = com.capgemini.exception.InvalidAccountNumberException.class)
	public void testInvalidAccountNumberExceptionOnWithdraw() throws InvalidAccountNumberException, InvalidAmountException, InsufficientBalanceException {
		
		Account account = new Account();
		account.setAccountNumber(-1);
		bankRepository.withDraw(100, account);
	}
	
	@Test(expected = com.capgemini.exception.InvalidAmountException.class)
	public void testInvalidAmountNumberExceptionOnWithdraw() throws InvalidAccountNumberException, InvalidAmountException, InsufficientBalanceException {
		
		Account account = new Account();
		account.setAccountNumber(1);
		bankRepository.withDraw(-100, account);
	}

	@Test(expected = com.capgemini.exception.InsufficientBalanceException.class)
	public void testInsufficientBalanceExceptionOnWithdraw() throws InvalidAccountNumberException, InvalidAmountException, InsufficientBalanceException {
		
		Account account = new Account();
		account.setAccountNumber(1);
		bankRepository.withDraw(600, account);
	}
	
	@Test
	public void testSuccessfulWithDraw() throws InvalidAccountNumberException, InvalidAmountException, InsufficientBalanceException{
		
		Account account = new Account();
		account.setAccountNumber(3);
		account.setBalance(1000);
		assertEquals(bankRepository.withDraw(600, account),true);
		
	}
	
	@Test(expected = java.lang.IllegalArgumentException.class)
	public void testIllegalArgumentExceptionOnDeposit() throws InvalidAccountNumberException{
		
		Account account = new Account();
		account.setAccountNumber(1);
		bankRepository.deposit(-600, account);
	}
	
	@Test(expected = com.capgemini.exception.InvalidAccountNumberException.class)
	public void testInvalidAccountNumberExceptionOnDeposit() throws InvalidAccountNumberException{
		
		Account account = new Account();
		account.setAccountNumber(-1);
		bankRepository.deposit(600, account);
	}
	
	@Test
	public void testSuccesfulDeposit() throws InvalidAccountNumberException{
		
		Account account = new Account();
		account.setAccountNumber(1);
		assertEquals(bankRepository.deposit(600, account), true);
	}
	
	@Test(expected = com.capgemini.exception.InvalidAccountNumberException.class)
	public void testInvalidAccountNumberExceptionOnShowBalance() throws InvalidAccountNumberException {
		
		Account account = new Account();
		account.setAccountNumber(-1);
		account.setBalance(200);
		
		bankRepository.showBalance(account);
	}
	
	@Test
	public void testSuccessfullShowBalance() throws InvalidAccountNumberException {
		
		Account account = new Account();
		account.setAccountNumber(5);
		account.setBalance(600);
		
		assertEquals(bankRepository.showBalance(account), "600");
	}
	
	@Test(expected = com.capgemini.exception.InvalidAmountException.class)
	public void testInvalidAmountExceptionOnTransfer() throws InsufficientBalanceException, InvalidAccountNumberException, InvalidAmountException{
		
		Account sourceAccount = new Account();
		Account destinationAccount = new Account();
		bankRepository.transfer(-100, sourceAccount, destinationAccount);
	}
	
	@Test(expected = com.capgemini.exception.InvalidAccountNumberException.class)
	public void testInvalidSourceAccountNumberOnTransfer() throws InsufficientBalanceException, InvalidAccountNumberException, InvalidAmountException{
		
		Account sourceAccount = new Account();
		sourceAccount.setAccountNumber(-2);
		sourceAccount.setBalance(2000);
		
		Account destinationAccount = new Account();
		destinationAccount.setAccountNumber(5);
		destinationAccount.setBalance(500);
		bankRepository.transfer(100, sourceAccount, destinationAccount);
	}
	
	@Test(expected = com.capgemini.exception.InvalidAccountNumberException.class)
	public void testInvalidSourceDestinationAccountNumberOnTransfer() throws InsufficientBalanceException, InvalidAccountNumberException, InvalidAmountException{
		
		Account sourceAccount = new Account();
		sourceAccount.setAccountNumber(5);
		sourceAccount.setBalance(2000);
		
		Account destinationAccount = new Account();
		destinationAccount.setAccountNumber(-1);
		destinationAccount.setBalance(500);
		bankRepository.transfer(100, sourceAccount, destinationAccount);
	}
	
	@Test(expected = com.capgemini.exception.InsufficientBalanceException.class)
	public void testInsufficientBalanceExceptionOnTransfer() throws InsufficientBalanceException, InvalidAccountNumberException, InvalidAmountException{
		
		Account sourceAccount = new Account();
		sourceAccount.setAccountNumber(8);
		sourceAccount.setBalance(2000);
		
		Account destinationAccount = new Account();
		destinationAccount.setAccountNumber(9);
		destinationAccount.setBalance(500);
		bankRepository.transfer(2001, sourceAccount, destinationAccount);
	}
	
	@Test
	public void testSuccesfulTransfer()	throws InsufficientBalanceException, InvalidAccountNumberException, InvalidAmountException {
		
		Account sourceAccount = new Account();
		sourceAccount.setAccountNumber(10);
		sourceAccount.setBalance(2000);
		
		Account destinationAccount = new Account();
		destinationAccount.setAccountNumber(11);
		destinationAccount.setBalance(500);
		
		assertEquals(bankRepository.transfer(500, sourceAccount, destinationAccount), true);
		
	}
}
