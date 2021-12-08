/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groupFFF.models;

/**
 *
 *FFF
 */
public class Transaction {
    private int id;
    private boolean credit = false;
    private boolean debit = false;
    private String date;
    private String description;
    private int postBal;
    
    public Transaction(){}
    
    public Transaction(int id, boolean credit, boolean debit, String date, String description, int postBal){
        this.id = id;
        this.credit = credit;
        this.debit = debit;
        this.date = date;
        this.description = description;
        this.postBal = postBal;
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isCredit() {
        return credit;
    }

    public void setCredit(boolean credit) {
        this.credit = credit;
    }

    public boolean isDebit() {
        return debit;
    }

    public void setDebit(boolean debit) {
        this.debit = debit;
    }

    public String getdate() {
        return date;
    }

    public void setDate(String Date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String Description) {
        this.description = description;
    }

    public int getPostBal() {
        return postBal;
    }

    public void setPostBal(int postBal) {
        this.postBal = postBal;
    }
    
}
