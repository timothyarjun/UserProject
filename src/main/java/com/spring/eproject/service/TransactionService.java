package com.spring.eproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.eproject.dto.UserTransaction;
import com.spring.eproject.entity.Transaction;
import com.spring.eproject.repository.TransactionRepo;

@Service
public class TransactionService {

	@Autowired
	private TransactionRepo transactionRepo;
	
	public Transaction transaction(Transaction transaction) {
		return transactionRepo.save(transaction);
	}
	
	public Transaction getTransaction(long id) {
		return transactionRepo.getOne(id);
	}

	public List<UserTransaction> getUserTransaction() {
		
		return transactionRepo.getUserTransaction();
	}
	
}
