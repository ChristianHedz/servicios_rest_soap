package mx.com.asteci.exception;

import mx.com.asteci.model.ErrorResponse;

public class ValidationException extends RuntimeException {

    private final ErrorResponse errorResponse;

    public ValidationException(ErrorResponse errorResponse) {
        super(errorResponse.getMessage());
        this.errorResponse = errorResponse;
    }

    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }
}
