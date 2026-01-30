package mx.com.asteci.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import mx.com.asteci.model.ErrorResponse;
import mx.com.asteci.stub.ConstraintViolationStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests de ValidationExceptionMapper")
class ValidationExceptionMapperTest {

    private ValidationExceptionMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new ValidationExceptionMapper();
    }

    @Test
    @DisplayName("Debe retornar 400 BAD_REQUEST con ErrorResponse")
    void testToResponse() {
        Set<ConstraintViolation<?>> violations = new HashSet<>();
        violations.add(new ConstraintViolationStub("createProduct.request.name", "no puede estar vacío"));
        ConstraintViolationException exception = new ConstraintViolationException(violations);

        Response response = mapper.toResponse(exception);

        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
        assertNotNull(response.getEntity());
        assertInstanceOf(ErrorResponse.class, response.getEntity());
    }
}
