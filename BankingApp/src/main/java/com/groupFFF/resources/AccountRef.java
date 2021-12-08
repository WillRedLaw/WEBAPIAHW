package com.groupFFF.resources;

import com.groupFFF.models.Account;
import com.groupFFF.service.AccountService;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 *FFF
 */
public class AccountRef {
    AccountRef AccountRefs = new AccountRef();
    
    //get all accounts
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Account> getAccounts() {
        return AccountRefs.getAllAccounts();
    }
	
    //create an account
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Account postAccount(Account a) {
        return AccountRefs.createAccount(a);
    } 
    //get an account based on id
    @GET
    @Path("/{accountId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Account getAccount(@PathParam("accountID") int a_id ) {
    	System.out.println("get Account by ID: "+a_id );
	return AccountRefs.getAccount(a_id);
    }

    private Account createAccount(Account a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
