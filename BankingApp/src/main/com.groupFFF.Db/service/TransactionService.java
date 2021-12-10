package com.groupFFF.service;

import java.util.List;
import com.groupFFF.models.Transaction;
import java.util.Date;

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
    
    public Transaction createCreditTransaction(Transaction ct){
        ct.setId(transaction.size() + 1);
        ct.setCredit(true);
        transaction.add(ct);
        System.out.println("Transaction Id Created credit : " + String.valueOf(ct.getId()));
        return ct;
    }
    
    public Transaction createDebitTransaction(Transaction dt){
        dt.setId(transaction.size() + 1);
        dt.setDebit(true);
        transaction.add(dt);
        System.out.println("Transaction Id Created debit : " + String.valueOf(dt.getId()));
        return dt;
    }
}
