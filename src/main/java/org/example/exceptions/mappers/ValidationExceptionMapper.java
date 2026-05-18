package org.example.exceptions.mappers;

import com.google.gson.Gson;
import org.example.common.ErrorResponse;
import org.example.exceptions.ValidationException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ValidationException> {

    private final Gson gson = new Gson();

    @Override
    public Response toResponse(ValidationException exception) {

        ErrorResponse errorResponse = new ErrorResponse(
                "VALIDATION_ERROR",
                exception.getMessage()
        );

        return Response.status(Response.Status.BAD_REQUEST)
                .type(MediaType.APPLICATION_JSON)
                .entity(gson.toJson(errorResponse))
                .build();
    }
}