package org.example.resources;

import com.google.gson.Gson;
import org.example.domain.ContactPerson;
import org.example.services.ContactPersonService;
import org.example.services.ContactPersonServiceImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/contact-persons")
public class ContactPersonResource {
    private final ContactPersonService contactPersonService=new ContactPersonServiceImpl();

    private final Gson gson=new Gson();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(String payload){
        ContactPerson contactPerson=gson.fromJson(payload,ContactPerson.class);

        ContactPerson createdContact=contactPersonService.create(contactPerson);

        return Response.status(Response.Status.CREATED)
                .entity(gson.toJson(createdContact))
                .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll(){

        return Response.ok(gson.toJson(contactPersonService.findAll()))
                .build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public  Response findById(@PathParam("id") Long id){
        ContactPerson contactPerson=contactPersonService.findById(id);
        return Response.ok(gson.toJson(contactPerson))
                .build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id,String payload){

        ContactPerson contactPerson=gson.fromJson(payload,ContactPerson.class);
        ContactPerson updatedContact=contactPersonService.update(id,contactPerson);

        return Response.ok(gson.toJson(updatedContact))
                .build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Long id){
        contactPersonService.delete(id);

        return Response.ok("{\"message\":\"Contact person deleted successfully\"}")
                .build();
    }
}
