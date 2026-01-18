package mx.com.asteci.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import mx.com.asteci.constants.Constants;
import mx.com.asteci.model.ErrorResponse;

import java.util.List;

@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException exception) {
        List<String> errors = exception.getConstraintViolations().stream()
                .map(this::formatViolation)
                .toList();

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(new ErrorResponse(Constants.VALIDATION_FAILED, errors))
                .build();
    }

    private String formatViolation(ConstraintViolation<?> violation) {
        String field = violation.getPropertyPath().toString();
        // Remover prefijo del método (ej: "createProduct.request.name" -> "name")
        if (field.contains(Constants.DOT)) {
            field = field.substring(field.lastIndexOf(Constants.DOT) + 1);
        }
        return field + ": " + violation.getMessage();
    }
}
