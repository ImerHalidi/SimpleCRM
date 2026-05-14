package org.example.resources;

import com.google.gson.Gson;
import org.example.domain.ContactPerson;
import org.example.domain.Customer;
import org.example.domain.Opportunity;
import org.example.services.CustomerService;
import org.example.services.CustomerServiceImpl;
import org.example.services.OpportunityService;
import org.example.services.OpportunityServiceImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/opportunity")
public class OpportunityResource {
        private final OpportunityService opportunityService = new OpportunityServiceImpl();

        private final Gson gson = new Gson();

        @POST
        @Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
        public Response create(String payload) {

            Opportunity opportunities = gson.fromJson(payload,Opportunity.class);

            Opportunity createdOpportunity =opportunityService.create(opportunities);

            return Response.status(Response.Status.CREATED)
                    .entity(gson.toJson(createdOpportunity))
                    .build();
        }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(){
            return Response.ok(gson.toJson(opportunityService.findAll()))
                    .build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public  Response findById(@PathParam("id") Long id){
            Opportunity opportunities= opportunityService.findById(id);
            return Response.ok(gson.toJson(opportunities))
                    .build();
    }


    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id,String payload){

        Opportunity opportunity=gson.fromJson(payload,Opportunity.class);
        Opportunity updatedOpportunity=opportunityService.update(id,opportunity);

        return Response.ok(gson.toJson(updatedOpportunity))
                .build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Long id){
        opportunityService.delete(id);

        return Response.ok("{\"message\":\"Contact person deleted successfully\"}")
                .build();
    }

}
