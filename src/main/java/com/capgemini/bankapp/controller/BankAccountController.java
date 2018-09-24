package com.capgemini.bankapp.controller;

import java.util.List;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.capgemini.bankapp.entities.BankAccount;
import com.capgemini.bankapp.exceptions.LowBalancException;
import com.capgemini.bankapp.service.BankAccountService;
@Controller
public class BankAccountController {

	private BankAccountService bankAccountService;

	/*public void setBankAccountService(BankAccountService bankAccountService) {
		this.bankAccountService = bankAccountService;
	}*/
	

	
@Autowired
	private BankAccountController(BankAccountService bankAccountService) {
		super();
		this.bankAccountService = bankAccountService;
	}
public double getBalance(long accountId) throws AccountNotFoundException {
	return bankAccountService.getBalance(accountId);
}

	public double withdraw(long accountId, double amount) throws LowBalancException, AccountNotFoundException {
		return bankAccountService.withdraw(accountId, amount);
	}

	public double deposit(long accountId, double amount) throws AccountNotFoundException {
		return bankAccountService.deposit(accountId, amount);
	}

	public boolean fundTransfer(long fromAccount, long toAccount, double amount) throws LowBalancException, AccountNotFoundException {
		return bankAccountService.fundTransfer(fromAccount, toAccount, amount);
	}
	
	public BankAccount findBankAccountById(long accountId) {

		return bankAccountService.findBankAccountById(accountId);
	}


	public List<BankAccount> findAllBankAccounts() {

		return bankAccountService.findAllBankAccounts();
	}


	public BankAccount updateBankAccount(BankAccount account) {

		return bankAccountService.updateBankAccount(account);
	}

	
	public boolean deleteBankAccount(long accountId) {

		return bankAccountService.deleteBankAccount(accountId);
	}

	
	public boolean addBankAccount(BankAccount account) {

		return bankAccountService.addBankAccount(account);
	}
}