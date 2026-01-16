package mx.com.asteci.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import mx.com.asteci.model.ErrorResponse;
import mx.com.asteci.ws.ValidationException;
import mx.com.asteci.ws.ValidationFault;

import java.util.List;

@Provider
public class SoapExceptionMapper implements ExceptionMapper<ValidationException> {

    @Override
    public Response toResponse(ValidationException exception) {
        ValidationFault fault = exception.getFaultInfo();
        
        String message = fault != null ? fault.getMessage() : exception.getMessage();
        List<String> errors = fault != null ? fault.getViolations() : null;
        
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(new ErrorResponse(message, errors))
                .build();
    }
}
