/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groupFFF.resources;

import com.groupFFF.models.Customer;
import com.groupFFF.service.CustomerService;
import com.groupFFF.models.Account;
import com.groupFFF.models.Transaction;
import com.groupFFF.service.AccountService;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * FFF
 */

@Path("/customers")

public class CustomerRef {
    private CustomerService CustomerService = new CustomerService();
    
    //get all customers
     @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Customer> getCustomers() {
        //Return a list of all customers
        return CustomerService.getAllCustomers();
    }//End get al customers
    
     //create a customer
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Customer postCustomer(Customer c) {
        //Creates a new customer and returns it
        return CustomerService.createCustomer(c);
    } //End create a customer
    
    
     //Get a specific customer
    @GET
    @Path("/{customerID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Customer getSpecificCustomer(@PathParam("customerID") int c_id ) {
        //gets customer for CustomerServices and returns it
	return CustomerService.getCustomer(c_id);
    }//End get a customer
    
    //Get account for specific customer
    @GET
    @Path("/{customerID}/accounts/{accountID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Account getCustomerAccount(@PathParam("customerID") int c_id, @PathParam("accountID") int a_id ) {
        //Get specific customer from customers using id
        Customer cust = CustomerService.getCustomer(c_id);
        //Get a list of the accounts on that customer
        List<Account> accounts = cust.getAccounts();
        //Return the specific account from the array of accounts
	return accounts.get(a_id-1);
    }
    
    //Get all Transactions for a specific customer and account
    @GET
    @Path("/{customerID}/accounts/{accountID}/movies")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Transaction> getCustomerAccountTransaction(@PathParam("customerID") int c_id, @PathParam("accountID") int a_id, @PathParam("id") int t_id) {
        //Get the customer specific in the URI
        Customer cust = CustomerService.getCustomer(c_id);
        //Get a list of accounts within the customer
        List<Account> accounts = cust.getAccounts();
        //get the account specific in the URI
        Account account = accounts.get(a_id-1);
        //Return the list of Transaction objects in the account
	return account.getTransactions();
    }
    
    //Transfer balance (currentBal) from account (accountId) to account2 (account2Id)
    @PATCH
    @Path("/{customerID}/accounts/{accountID}/movies/{movieID}/accounts2/{accounts2Id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Transaction setCustomerBal(@PathParam("accountId") int c_id, @PathParam("accountId") int a_id , @PathParam("customerBal") int cb_id, @PathParam("accounts2ID") int a2_id) {
        //Get the customer specified in the URI
        Customer cust = CustomerService.getCustomer(c_id);
        //Get a list of the accounts and then get the acount specified in the URI
        List<Account> accounts = cust.getAccounts();
        Account account = accounts.get(a_id-1);
        //Get a list of movies in the custoemrs account. get and Store the movie to be removed temporarily.
        List<Transaction> transactions = account.getTransactions();
	Transaction transferB = transactions.get(cb_id-1);
        transactions.remove(cb_id-1);
        //For loop to update the Id of all transactions 
        for(int i=0; i<transactions.size(); i++){
            Transaction temp1 = transactions.get(i);
            temp1.setId(i+1);
            transactions.set(i, temp1);
        }//End for loop
        //Set the Transaction List in the account
        account.setTransactions(transactions);
        //Replace the old account in the array of accounts with the new one
        accounts.set(a_id-1, account);
        
        //Get the new account that the transaction done with
        account = accounts.get(a2_id-1);
        //Get the list of transactions from the account
        transactions = account.getTransactions();
        //Set the Id of the temporarily held transaction to be the next id.
        transferB.setId(transactions.size()+1);
        //Add the transaction to the list in new account
        transactions.add(transferB);
        //Set transaction in the account
        account.setTransactions(transactions);
        //update the account in the list of accounts
        accounts.set(a2_id-1, account);
        
        //Update the array of accounts in the customer
        cust.setAccounts(accounts);
        //Update the customer in the CustomerService
        CustomerService.setCustomer(c_id, cust);
        //Return the transaction that was transfered
        return transferB;
    }//End transfer a movie
    
}
