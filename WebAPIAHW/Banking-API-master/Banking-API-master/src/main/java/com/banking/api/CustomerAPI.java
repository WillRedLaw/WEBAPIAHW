package com.banking.api;

import com.banking.bank.Customer;
import com.banking.bank.exception.CustomerNotOwnerException;
import com.banking.controller.InteractionController;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import java.util.HashMap;
import java.util.Map;

@Path("/customer")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class CustomerAPI {

    private InteractionController interactionController;

    public CustomerAPI() {
        interactionController = new InteractionController();
    }

    @GET
    @Path("/{id}")
    public Customer getCustomer(@PathParam("id") int id) {
        Customer customer = interactionController.getCustomerById(id);

        if(customer == null) {
            return new Customer();
        }

        return customer;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Customer createCustomer(Customer customer) {
        if(customer != null && customer.isValid()) {
            return interactionController.addNewCustomer(customer);
        }

        return new Customer();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Customer updateCustomer(@PathParam("id") int id, Customer customer) {
        if(customer != null) {
            customer.setId(id);
            return interactionController.updateCustomer(customer);
        }

        return new Customer();
    }
}
