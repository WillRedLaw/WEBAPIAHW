
package com.groupFFF.models;


/**
 *
 * FFF
 */

import java.util.ArrayList;
import java.util.List;

public class Customer {
    
    private int securityId;
    private String cname;
    private String address;
    private String email;
    private List<Account> accounts = new ArrayList<>();
    
    public Customer(){}
    
    public Customer(int securityId, String cname, String address, String email, List<Account> accounts){
        this.securityId = securityId;
        this.cname = cname;
        this.address = address;
        this.email  = email;
        this.accounts = accounts;
    }

    public int getSecurityId() {
        return securityId;
    }

    public void setSecurityId(int securityId) {
        this.securityId = securityId;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
    
    public void addAccount(Account account){
        (this.accounts).add(account);
    }
    
    public String printCustomer(){
        String str = "SecurityID : " + this.getSecurityId()+"/tName : "+ this.getCname() + "/tAddress : "+ this.getAddress() + "/tEmail : " + this.getEmail() + "/tAccounts : "+ this.getAccounts();
        return str;
    }    
}
