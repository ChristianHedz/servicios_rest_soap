package mx.com.asteci.ws.validation;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import mx.com.asteci.core.common.Constants;
import mx.com.asteci.ws.dto.CreateProductRequest;
import mx.com.asteci.ws.exception.ValidationException;
import mx.com.asteci.ws.exception.ValidationFaultBean;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@ApplicationScoped
public class ProductValidator {

    private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public void validate(CreateProductRequest product) throws ValidationException {
        Set<ConstraintViolation<CreateProductRequest>> violations = validator.validate(product);

        if (!violations.isEmpty()) {
            List<String> violationMessages = violations.stream()
                    .map(v -> v.getPropertyPath() + ": " + v.getMessage())
                    .collect(Collectors.toList());

            ValidationFaultBean faultBean = new ValidationFaultBean(
                    Constants.VALIDATION_PRODUCT_FAILED,
                    violationMessages);
            throw new ValidationException(Constants.VALIDATION_FAILED, faultBean);
        }
    }
}
