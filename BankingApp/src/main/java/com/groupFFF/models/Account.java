package com.groupFFF.models;

import java.util.ArrayList;
import java.util.List;
/**
 *
 *FFF
 */
public class Account {
    private int accountId;
    private int sortCode;
    private int currentBal;
    private List<Transaction> transactions = new ArrayList<>();
    
    public Account(){}
    
    public Account(int accountId, int sortCode, int currentBal, List<Transaction>transactions){
        this.accountId = accountId;
        this.sortCode = sortCode;
        this.currentBal = currentBal;
        this.transactions = transactions;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getSortCode() {
        return sortCode;
    }

    public void setSortCode(int sortCode) {
        this.sortCode = sortCode;
    }

    public int getCurrentBal() {
        return currentBal;
    }

    public void setCurrentBal(int currentBal) {
        this.currentBal = currentBal;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
    
    public String printAccount(){
        String str = "Account Id : "+ this.getAccountId() + "/t Sort Code : "+ this.getSortCode() + "/tCurrent Balance : "+ this.getCurrentBal() + "/t Transactions : " + this.getTransactions();
        return str;
    }
    
}
