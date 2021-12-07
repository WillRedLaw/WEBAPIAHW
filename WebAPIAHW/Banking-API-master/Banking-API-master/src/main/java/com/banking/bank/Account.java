package com.banking.bank;

import com.banking.bank.exception.CustomerNotOwnerException;
import com.banking.bank.exception.InsufficientFundsException;
import com.banking.bank.exception.InvalidAmountException;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class represents a Bank Account
 * It has various operations defined
 * such as lodge, withdraw and transfer
 *
 * @author Hannah OR
 * @version 1.0
 */
@Entity
@Table
@XmlRootElement
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int sortCode;
    private int accountNumber;
    private double balance;
    @OneToMany(mappedBy = "account")
    private List<Transaction> transactions;
    @ManyToOne
    @JoinColumn(name = "ownerId", referencedColumnName = "id")
    private Customer owner;

    /**
     * This is a no args constructor used by the
     * persistence layer when loading an Account
     */
    public Account() {}

    /**
     * Constructor used to create an new bank account
     * It generates an account number and registers
     * the new account with an owner to create the
     * many to one relationship from Account to Customer
     *
     * @param owner
     */
    public Account(Customer owner) {
        transactions = new ArrayList<>();
        balance = 0.0;
        setSortCode(sortCode);
        setOwner(owner);
        generateAccountNumber();
        generateSortCode();
        this.owner.addAccount(this);
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setOwner(Customer owner) {
        this.owner = owner;
    }

    public Customer getOwner() {
        return owner;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void updateBalance(double amount) {
        balance = amount;
    }


    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public Transaction getLastTransaction(){
        return transactions.get(transactions.size() - 1);
    }

    public List getTransactions(){
        return transactions;
    }

    public int getSortCode() {
        return sortCode;
    }

    /**
     * Sets the sortCode for an account and checks
     * that it is between 999 and 9999
     *
     * @param sortCode
     */
    public void setSortCode(int sortCode) {
        this.sortCode = sortCode;

    }

    /**
     * Lodges an amount x to an account and returns
     * transaction that took place on the account
     *
     * @param amount
     * @throws InvalidAmountException
     * @return Transaction
     */
    public void lodge(double amount) throws InvalidAmountException {
        Transaction transaction;

        if (amount > 0) {
            transaction = new Lodgement(this, amount);
            addTransaction(transaction);
        } else {
            throw new InvalidAmountException();
        }

    }

    /**
     *
     * @param amount
     * @throws InvalidAmountException
     * @throws CustomerNotOwnerException
     * @throws InsufficientFundsException
     */
    public void withdraw(double amount) throws InvalidAmountException, InsufficientFundsException {

        if (amount > 0) {
            if (canWithdraw(amount)) {
                Transaction transaction = new Withdrawal(this, amount);
                addTransaction(transaction);
            } else {
                throw new InsufficientFundsException();
            }
        } else {
            throw new InvalidAmountException();
        }
    }

    /**
     *
     * @param toAccount
     * @param amount
     * @throws InvalidAmountException
     * @throws CustomerNotOwnerException
     * @throws InsufficientFundsException
     */
    public void transfer(Account toAccount, double amount) throws InvalidAmountException, CustomerNotOwnerException, InsufficientFundsException {
        withdraw(amount);
        toAccount.lodge(amount);
    }

    /**
     *
     * @param amount
     * @return
     */
    protected boolean canWithdraw(double amount) {
        return !(balance < 0) && !((balance - amount) < 0);
    }

    /**
     *
     * @param transaction
     */
    private void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    /**
     *
     */
    private void generateAccountNumber() {
        Random rnd = new Random();
        accountNumber = 1000000 + rnd.nextInt(9999999);
    }

    private void generateSortCode() {
        Random rnd = new Random();
        sortCode = 1000 + rnd.nextInt(9999);
    }

}
