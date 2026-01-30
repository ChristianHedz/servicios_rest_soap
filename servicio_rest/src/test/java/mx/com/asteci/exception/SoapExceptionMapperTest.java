package mx.com.asteci.exception;

import jakarta.ws.rs.core.Response;
import mx.com.asteci.model.ErrorResponse;
import mx.com.asteci.ws.ValidationException;
import mx.com.asteci.ws.ValidationFault;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests de SoapExceptionMapper")
class SoapExceptionMapperTest {

    private SoapExceptionMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new SoapExceptionMapper();
    }

    @Test
    @DisplayName("Debe retornar 400 BAD_REQUEST con ErrorResponse")
    void testToResponse() {
        // Arrange
        ValidationFault fault = new ValidationFault();
        fault.setMessage("Error de validación");
        fault.getViolations().addAll(Arrays.asList("name: requerido", "price: positivo"));
        ValidationException exception = new ValidationException("Error", fault);

        // Act
        Response response = mapper.toResponse(exception);

        // Assert
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
        assertNotNull(response.getEntity());
        ErrorResponse errorResponse = (ErrorResponse) response.getEntity();
        assertEquals("Error de validación", errorResponse.getMessage());
        assertEquals(2, errorResponse.getErrors().size());
    }
}
