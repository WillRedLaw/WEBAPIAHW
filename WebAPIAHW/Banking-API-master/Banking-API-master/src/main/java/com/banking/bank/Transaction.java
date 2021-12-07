package com.banking.bank;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

@Table
@Entity
@DiscriminatorColumn(name = "type")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@XmlRootElement
public abstract class Transaction implements Serializable {

    public enum Type {
        CREDIT("Credit"),
        DEBIT("Debit");

        private String type;

        Type(String type) {
            this.type = type;
        }

        @Override
        public String toString(){
            return type;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne
    @JoinColumn(name = "accountId", referencedColumnName = "id")
    private Account account;
    private double amount;
    @Column(name = "balance")
    private double postBalance;
    @Column(name = "date", nullable = false,
            updatable = false, insertable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date timestamp;

    public Transaction() {}

    public Transaction(double amount, Account account) {
        this.amount = amount;
        this.account = account;

        doTransaction();
    }

    protected abstract void doTransaction();

    public void setPostBalance(double postBalance) {
        this.postBalance = postBalance;
    }

    public double getPostBalance() {
        return postBalance;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public Account getAccount() {
        return account;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Date getTimestamp() {
        return timestamp;
    }
}

