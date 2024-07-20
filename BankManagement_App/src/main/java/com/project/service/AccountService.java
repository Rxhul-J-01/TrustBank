package com.project.service;

import java.util.List;

import com.project.entity.Account;

public interface AccountService {
	
	public Account createAccount(Account a);
	public Account getAccountDetails(Long accountNumber);
	public List<Account> getAllAccounts();
	public Account depostiMoney(Long accNumber, Double amount);
	public Account withdrawAmount(Long accountNumber, Double amount);
	public void closeAccount(Long accountNumber);
}
