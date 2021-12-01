/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bankapp.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * @author Hannah
 * 
 */
public class Account {
    private String sortCode;
    private int accountId;
    private String accountType;
    private int accNumber;
    private double balance;
    private Date accountCreated;
    private List<Transaction> transactionList = new ArrayList<>();
    
    
    private Account(){
    }

    public Account(String sortCode, int accountId, 
                   String accountType, int accNumber, 
                   double balance) {
        
        this.sortCode = sortCode;
        this.accountId = accountId;
        this.accountType = accountType;
        this.accNumber = accNumber;
        this.balance = balance;
        this.accountCreated = new Date();
    }
    
    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    public String getSortCode() {
        return sortCode;
    }

    public void setSortCode(String sortCode) {
        this.sortCode = sortCode;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
    
    
    public int getAccNumber() {
        return accNumber;
    }

    public void setAccNumber(int accNumber) {
        this.accNumber = accNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Date getAccountCreated() {
        return accountCreated;
    }

    public void setAccountCreated(Date accountCreated) {
        this.accountCreated = accountCreated;
    }
    
    
}
