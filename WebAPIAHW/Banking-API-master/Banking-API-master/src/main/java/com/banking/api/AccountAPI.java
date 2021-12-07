package com.banking.api;

import com.banking.bank.Account;
import com.banking.bank.Customer;
import com.banking.bank.Transaction;
import com.banking.bank.Withdrawal;
import com.banking.bank.exception.CustomerNotOwnerException;
import com.banking.bank.exception.InsufficientFundsException;
import com.banking.bank.exception.InvalidAmountException;
import com.banking.controller.InteractionController;
import com.google.gson.Gson;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Hannah ORourke
 */

@Path("/account")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class AccountAPI {

    private InteractionController interactionController;
    private Gson gson;
    private Map errors;

    public AccountAPI() {
        interactionController = new InteractionController();
        gson = new Gson();
        errors = new HashMap();
    }

    @GET
    @Path("/{id}")
    public Account getAccount(@PathParam("id") int id, @Context UriInfo data) {
        String accountNumber = data.getQueryParameters().getFirst("accountNumber");

        if (accountNumber != null) {
            Account account = interactionController.getAccount(Integer.parseInt(accountNumber));

            if(account != null) {
                return account;
            }
        }

        return new Account();
    }

    @POST
    @Path("/{customerId}")
    public Account createAccount(@PathParam("customerId") int id) {
        return interactionController.addAccount(id);
    }

    @GET
    @Path("/transactions")
    public List<Transaction> getTransactions(@Context UriInfo data) {
        int customerId = Integer.valueOf(data.getQueryParameters().getFirst("customerId"));
        int accountNumber = Integer.valueOf(data.getQueryParameters().getFirst("accountNumber"));
        int sortCode = Integer.valueOf(data.getQueryParameters().getFirst("sortCode"));

        Customer customer = interactionController.getCustomerById(customerId);

        if (customer != null) {
            Account account =  customer.getAccount(accountNumber, sortCode);

            if (account != null) {
                return  account.getTransactions();
            }
        }

        return null;
    }

    @GET
    @Path("/balance")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBalance(@Context UriInfo data) {
        int customerId = Integer.valueOf(data.getQueryParameters().getFirst("customerId"));
        int accountNumber = Integer.valueOf(data.getQueryParameters().getFirst("accountNumber"));
        int sortCode = Integer.valueOf(data.getQueryParameters().getFirst("sortCode"));

        Customer customer = interactionController.getCustomerById(customerId);

        if (customer != null) {
            Account account =  customer.getAccount(accountNumber, sortCode);

            if (account != null) {
                return Response.status(Response.Status.OK).entity(gson.toJson(account.getBalance())).build();
            }
        }

        errors.put("Error", "Invalid Account");
        return Response.status(Response.Status.OK).entity(gson.toJson(errors)).build();
    }

    @GET
    @Path("/withdrawal/{customerId}/{accountId}")
    public Transaction withdraw(@PathParam("customerId") int customerId, @PathParam("accountId") int accountId, @Context UriInfo data){
        int accountNumber = Integer.valueOf(data.getQueryParameters().getFirst("accountNumber"));
        int sortCode = Integer.valueOf(data.getQueryParameters().getFirst("sortCode"));
        double amount = Double.valueOf(data.getQueryParameters().getFirst("amount"));

        Customer customer = interactionController.getCustomerById(customerId);

        if (customer != null) {
            Account account =  customer.getAccount(accountNumber, sortCode);

            if (account != null) {
                try {
                   return interactionController.withdraw(account, amount);
                } catch (InsufficientFundsException e) {
                    e.printStackTrace();
                } catch (CustomerNotOwnerException e) {
                    e.printStackTrace();
                } catch (InvalidAmountException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    @GET
    @Path("/lodgement/{customerId}/{accountId}")
    public Transaction lodge(@PathParam("customerId") int customerId, @PathParam("accountId") int accountId, @Context UriInfo data){
        int accountNumber = Integer.valueOf(data.getQueryParameters().getFirst("accountNumber"));
        int sortCode = Integer.valueOf(data.getQueryParameters().getFirst("sortCode"));
        double amount = Double.valueOf(data.getQueryParameters().getFirst("amount"));

        Customer customer = interactionController.getCustomerById(customerId);

        if (customer != null) {
            Account account =  customer.getAccount(accountNumber, sortCode);

            if (account != null) {
                try {
                    return interactionController.lodge(account, amount);
                } catch (CustomerNotOwnerException e) {
                    e.printStackTrace();
                } catch (InvalidAmountException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    @GET
    @Path("/transfer/{customerId}/{accountId}/{customerIdTwo}/{accountIdTwo}")
    public Transaction transfer(@PathParam("customerId") int customerId, @PathParam("accountId") int accountId, @PathParam("customerIdTwo") int customerIdTwo, @PathParam("accountIdTwo") int accountIdTwo, @Context UriInfo data){
        int fromAccountNumber = Integer.valueOf(data.getQueryParameters().getFirst("fromAccountNumber"));
        int fromSortCode = Integer.valueOf(data.getQueryParameters().getFirst("fromSortCode"));
        int toAccountNumber = Integer.valueOf(data.getQueryParameters().getFirst("toAccountNumber"));
        int toSortCode = Integer.valueOf(data.getQueryParameters().getFirst("toSortCode"));
        double amount = Double.valueOf(data.getQueryParameters().getFirst("amount"));

        Customer customer = interactionController.getCustomerById(customerId);
        Customer customerTwo = interactionController.getCustomerById(customerIdTwo);

        if (customer != null && customerTwo != null) {
            Account fromAccount =  customer.getAccount(fromAccountNumber, fromSortCode);
            Account toAccount =  customerTwo.getAccount(toAccountNumber, toSortCode);

            if (fromAccount != null && toAccount != null) {
                try {
                    return interactionController.transfer(fromAccount, toAccount, amount);
                } catch (InsufficientFundsException e) {
                    e.printStackTrace();
                } catch (CustomerNotOwnerException e) {
                    e.printStackTrace();
                } catch (InvalidAmountException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

}
