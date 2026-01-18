package mx.com.asteci.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import mx.com.asteci.model.ErrorResponse;
import mx.com.asteci.ws.ValidationException;
import mx.com.asteci.ws.ValidationFault;

import java.util.List;
import java.util.Optional;

@Provider
public class SoapExceptionMapper implements ExceptionMapper<ValidationException> {

    @Override
    public Response toResponse(ValidationException exception) {
        ValidationFault fault = exception.getFaultInfo();

        String message = Optional.ofNullable(fault)
                .map(ValidationFault::getMessage)
                .orElse(exception.getMessage());

        List<String> errors = Optional.ofNullable(fault)
                .map(ValidationFault::getViolations)
                .orElse(List.of());

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(new ErrorResponse(message, errors))
                .build();
    }
}
