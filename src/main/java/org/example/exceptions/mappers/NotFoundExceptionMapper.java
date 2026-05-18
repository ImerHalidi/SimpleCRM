package org.example.exceptions.mappers;

import com.google.gson.Gson;
import org.example.common.ErrorResponse;
import org.example.exceptions.NotFoundException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {

    private final Gson gson = new Gson();

    @Override
    public Response toResponse(NotFoundException exception) {

        ErrorResponse errorResponse = new ErrorResponse(
                "NOT_FOUND",
                exception.getMessage()
        );

        return Response.status(Response.Status.NOT_FOUND)
                .type(MediaType.APPLICATION_JSON)
                .entity(gson.toJson(errorResponse))
                .build();
    }
}