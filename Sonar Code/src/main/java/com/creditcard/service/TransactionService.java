package com.creditcard.service;
import java.util.List;

import com.creditcard.entities.Transaction;


public interface TransactionService {
	
	public Transaction addTransaction(Transaction transaction);
	public Transaction removeTransaction(long id) ;
	public Transaction updateTransaction(long id, Transaction transaction);
	public Transaction getTransactionDetails(long id);
	public List<Transaction> getAllTransactions(); 

}