/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bankapp.model;
import java.util.Date;

public class Transaction {
    private int transactionNumber;
    private String transactionType;
    private String description;
    private double amount;
    private double postBalance;
    private Date transactionCreated;
    
    public Transaction(){
    }

    public Transaction(int transactionNumber, String transactionType, 
                       String description, double postBalance, double amount) {
        this.transactionNumber = transactionNumber;
        this.transactionType = transactionType;
        this.description = description;
        this.amount = amount;
        this.postBalance = postBalance;
        this.transactionCreated = new Date();
    }

    public int getTransactionNumber() {
        return transactionNumber;
    }

    public void setTransactionNumber(int transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPostBalance() {
        return postBalance;
    }

    public void setPostBalance(double postBalance) {
        this.postBalance = postBalance;
    }
    
    
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getTransactionCreated() {
        return transactionCreated;
    }

    public void setTransactionCreated(Date transactionCreated) {
        this.transactionCreated = transactionCreated;
    }
}
