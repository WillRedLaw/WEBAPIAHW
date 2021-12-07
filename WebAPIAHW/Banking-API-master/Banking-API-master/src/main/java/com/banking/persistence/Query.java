package com.banking.persistence;

import com.banking.bank.Account;
import com.banking.bank.Customer;

import javax.persistence.TypedQuery;

/**
 * Created by Hannah ORourke
 */
public class Query {

    public static TypedQuery getCustomer(PersistenceManager persistenceManager, String email) {
        TypedQuery<Customer> query = persistenceManager.getEntityManager().createQuery(
                "SELECT c FROM Customer c WHERE c.email = ?1", Customer.class);
        query.setParameter(1, email);

        return  query;
    }

    public static TypedQuery getAccount(PersistenceManager persistenceManager, int accountNumber) {
        TypedQuery<Account> query = persistenceManager.getEntityManager().createQuery(
                "SELECT a FROM Account a WHERE a.accountNumber = :accountNumber", Account.class);
        query.setParameter("accountNumber", accountNumber);

        return  query;
    }

}
