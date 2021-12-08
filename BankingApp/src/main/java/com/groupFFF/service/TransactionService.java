package com.groupFFF.service;

import java.util.List;

/**
 *
 * FFF
 */
public class TransactionService {
    Database d = new Database();
    private List<Transaction> transaction = Transaction;
    
    public List<Transaction> getAllTransactions(){
        return transaction;
    }
    
    public Transaction getAccount(int id){
        return transaction.get(id-1);
    }
    
    public Transaction 
}
