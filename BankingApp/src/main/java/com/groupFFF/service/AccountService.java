
package com.groupFFF.service;

import com.groupFFF.models.Account;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * FFF
 */
public class AccountService {
    Database a = new Database();
    private List<Account> accountS = a.getAccountService();
    
    
    public List<Account> getAllAccounts() {
        return accountS;
    }
    
    public Account getAccount(int id){
        return accountS.get(id-1);
    }
    
    public Account createAccount(Account c){
        a.setAccountId(accountS.size() + 1);
        accountS.add(c);
        System.out.println("Account Created : " + 
                  String.valueOf(c.getAccountId()));
        return c;
        }
    
    public List<Account> getSearchAccounts(int accountId) {
        List<Account> matcheslist = new ArrayList<>();
        
        for (Account q : getAllAccounts()) {
            if ((accountId == 0 || q.getAccountId().equals(accountId))){
                matcheslist.add(q);
            }
        }
        return matcheslist;     
}
