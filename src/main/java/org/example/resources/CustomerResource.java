package org.example.resources;

import com.google.gson.Gson;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.example.domain.ContactPerson;
import org.example.domain.Customer;
import org.example.domain.InteractionLog;
import org.example.domain.Opportunity;
import org.example.services.*;

@Path("/customers")
public class CustomerResource {
    private final InteractionLogService interactionLogService = new InteractionLogServiceImpl();
    private final OpportunityService opportunityService = new OpportunityServiceImpl();
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

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public  Response getAll(){
        return Response.ok(gson.toJson(customerService.findAll()))
                .build();
    }


    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public  Response findById(@PathParam("id") Long id){
        Customer customer =customerService.findById(id);
        return Response.ok(gson.toJson(customer))
                .build();
    }

    @GET
    @Path("/{id}/opportunity")
    @Produces(MediaType.APPLICATION_JSON)
    public  Response findOpportunitiesByCustomer(@PathParam("id") Long id){
        
        return Response.ok(gson.toJson(opportunityService.findByCostumerId(id)))
                .build();
    }

    @GET
    @Path("/{id}/interactions")
    @Produces(MediaType.APPLICATION_JSON)
    public  Response findInteractionsByCustomer(@PathParam("id") Long id){

        return Response.ok(gson.toJson(interactionLogService.findInteractionLogsByCustomerID(id)))
                .build();
    }



    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id,String payload){

        Customer customer =gson.fromJson(payload,Customer.class);
        Customer updatedCustomer=customerService.update(id, customer);

        return Response.ok(gson.toJson(updatedCustomer))
                .build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Long id){
        customerService.delete(id);

        return Response.ok("{\"message\":\"Contact person deleted successfully\"}")
                .build();
    }

}