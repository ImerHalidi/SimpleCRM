package org.example.resources;


import com.google.gson.Gson;
import org.example.domain.InteractionLog;
import org.example.domain.Opportunity;
import org.example.services.InteractionLogService;
import org.example.services.InteractionLogServiceImpl;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.image.RescaleOp;

@Path("/interactions")
public class InteractionLogResource {
    private final InteractionLogService interactionLogService = new InteractionLogServiceImpl();

    private final Gson gson = new Gson();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(String payload){
        InteractionLog interactionLogs=gson.fromJson(payload,InteractionLog.class);
        InteractionLog createdInteractions =interactionLogService.create(interactionLogs);

        return Response.status(Response.Status.CREATED)
                .entity(gson.toJson(createdInteractions))
                .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetAll(){
        return Response.ok(gson.toJson(interactionLogService.findAll()))
                .build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response FindById(@PathParam("id") Long id){
        InteractionLog interactionLog=interactionLogService.findById(id);
        return Response.ok(gson.toJson(interactionLog))
                .build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response Update(@PathParam("id") Long id,String payload){
        InteractionLog interactionLog=gson.fromJson(payload,InteractionLog.class);
        InteractionLog updatedInteraction=interactionLogService.update(id,interactionLog);

        return Response.ok(gson.toJson(updatedInteraction))
                .build();

    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Long id){
        interactionLogService.delete(id);

        return Response.ok("{\"message\":\"Contact person deleted successfully\"}")
                .build();
    }

}
