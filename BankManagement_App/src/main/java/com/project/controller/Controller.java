package com.project.controller;

import java.util.ArrayList;
import java.util.List;

import javax.crypto.interfaces.DHPublicKey;
import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;

import org.antlr.v4.runtime.ListTokenSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PutExchange;

import com.project.entity.Account;
import com.project.service.AccountService;

import jakarta.persistence.Id;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/Account")
public class Controller {
	
	@Autowired
	AccountService service;
	
	@PostMapping("/create")
	public ResponseEntity<Account> createAccount(@RequestBody Account a) {
		
		Account account = service.createAccount(a);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(account);  
	}

	@GetMapping("/get/{accountNumber}")
	public ResponseEntity<Account> getAccountByNumber(@PathVariable Long accountNumber){
		
		Account account =  service.getAccountDetails(accountNumber);
		
		return ResponseEntity.status(HttpStatus.FOUND).body(account);
	}
	
	@GetMapping("/allAccounts")
	public ResponseEntity<List<Account>> getAllAccounts(){
		
		List<Account> list = service.getAllAccounts();
		
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}
	
	@PutMapping("/deposit/{accountNumber}/{amount}")
	public Account depositMoney(@PathVariable Long accountNumber, @PathVariable Double amount) {
			
		Account account = service.depostiMoney(accountNumber, amount);
		
		return account;
	}
	
	@PutMapping("/withdraw/{accountNumber}/{amount}")
	public Account withdrawAmount(@PathVariable Long accountNumber, @PathVariable Double amount) {
		
		Account account = service.withdrawAmount(accountNumber, amount);
		
		return account;
	}
	
	@DeleteMapping("/delete/{accountNumber}")
	public ResponseEntity<String> deleteAccount(@PathVariable Long accountNumber) {
		service.closeAccount(accountNumber);
		
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Account closed");
	}
}
