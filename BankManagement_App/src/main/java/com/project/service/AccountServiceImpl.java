package com.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entity.Account;
import com.project.repo.AccountRepo;

import jakarta.websocket.server.ServerEndpoint;

@Service
public class AccountServiceImpl implements AccountService{
	
	@Autowired
	AccountRepo repo;

	@Override
	public Account createAccount(Account a) {
		
		Account account = repo.save(a);
		
		return account;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Account getAccountDetails(Long accountNumber) {
		
		Optional<Account> account = repo.findById(accountNumber);
		
		if(account.isEmpty()) {
			throw new RuntimeException("Account is not present");
		}
		
		Account account_found = account.get();
		return account_found;
	}

	@Override
	public List<Account> getAllAccounts() {
		
		List<Account> list = repo.findAll();
		
		return list;
	}

	@Override
	public Account depostiMoney(Long accNumber, Double amount) {
		
		Optional<Account> account = repo.findById(accNumber);
		
		if(account.isEmpty()) {
			throw new RuntimeException("Account is not present");
		}
		
		Account account2 = account.get();
		
		account2.setAccount_balance(account2.getAccount_balance()+amount);
		
		repo.save(account2);
		
		return account2;
	}

	@Override
	public Account withdrawAmount(Long accountNumber, Double amount) {
		
		Optional<Account> account = repo.findById(accountNumber);
		
		if(account.isEmpty()) {
			throw new RuntimeException("Account is not present");
		}
		
		Account newAccount = account.get();
		
		if(newAccount.getAccount_balance() < amount) {
			throw new RuntimeException("Insufficient Account Balance");
		}
		
		newAccount.setAccount_balance(newAccount.getAccount_balance()-amount);
		
		repo.save(newAccount);
		
		
		return newAccount;
	}

	@Override
	public void closeAccount(Long accountNumber) {
		
		getAccountDetails(accountNumber);
		repo.deleteById(accountNumber);
	}

}
