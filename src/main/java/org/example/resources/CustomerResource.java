package org.example.resources;

import com.google.gson.Gson;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.example.domain.Customer;
import org.example.services.CustomerService;
import org.example.services.CustomerServiceImpl;

@Path("/customers")
public class CustomerResource {

    private final CustomerService customerService = new CustomerServiceImpl();

    private final Gson gson = new Gson();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(String payload) {

        Customer customer = gson.fromJson(payload, Customer.class);

        Customer createdCustomer = customerService.create(customer);

        return Response.status(Response.Status.CREATED)
                .entity(gson.toJson(createdCustomer))
                .build();
    }
}