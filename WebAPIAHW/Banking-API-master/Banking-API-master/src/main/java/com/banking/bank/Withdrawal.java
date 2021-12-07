package com.banking.bank;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Inheritance
@XmlRootElement
@DiscriminatorValue(value = "Debit")
public class Withdrawal extends Transaction {

    public Withdrawal() {
        super();
    }

    public Withdrawal(Account account, double amount) {
        super(amount, account);
    }

    @Override
    protected void doTransaction() {
        getAccount().updateBalance(getAccount().getBalance() - getAmount());
        setPostBalance(getAccount().getBalance());
    }
}
