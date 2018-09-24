package com.capgemini.bankapp.service;

import java.util.List;

import javax.security.auth.login.AccountNotFoundException;

import com.capgemini.bankapp.entities.BankAccount;
import com.capgemini.bankapp.exceptions.LowBalancException;

public interface BankAccountService {

	public double getBalance(long accountId) throws AccountNotFoundException;

	public double withdraw(long accountId, double amount) throws LowBalancException, AccountNotFoundException;

	public boolean fundTransfer(long fromAccount, long toAccount, double amount)
			throws LowBalancException, AccountNotFoundException;

	public double deposit(long accountId, double amount) throws AccountNotFoundException;

	public BankAccount findBankAccountById(long accountId);

	public List<BankAccount> findAllBankAccounts();

	public BankAccount updateBankAccount(BankAccount account);

	public boolean deleteBankAccount(long accountId);

	public boolean addBankAccount(BankAccount account);
}