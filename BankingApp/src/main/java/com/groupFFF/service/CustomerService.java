package com.groupFFF.service;

import com.groupFFF.models.Customer;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * FFF
 */
public class CustomerService {
    Database d = new Database();
    private List<Customer> customers = d.getCustomer();
    
    //Method to get all customers
    public List<Customer> getAllCustomers() {
        return customers;
    } 
    //Get a customer by id
    public Customer getCustomer(int id) {
        return customers.get(id-1);
    }
    //Set a customer by id
    public void setCustomer(int id, Customer customerIn) {
        customers.set(id-1, customerIn);
    }
    
    //Create a new customer
    public Customer createCustomer(Customer c) {
        c.setSecurityId(customers.size() + 1);
	customers.add(c);
	System.out.println(String.valueOf(c.setSecurityId(0)));
	return c;
    }
    
    public List<Customer> getSearchAccounts(String name) {
        List<Customer> matcheslist = new ArrayList<>();
      
        for (Customer q: getAllCustomers()) {
            if ((name == null || q.getCname().equals(name))) {
               matcheslist.add(q);
            }
        }
        return matcheslist;
    }  
    
}
