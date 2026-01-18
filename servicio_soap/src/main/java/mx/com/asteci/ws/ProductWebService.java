package mx.com.asteci.ws;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import mx.com.asteci.core.repository.entity.Product;
import mx.com.asteci.core.service.ProductService;
import mx.com.asteci.ws.converter.ProductConverter;
import mx.com.asteci.ws.dto.CreateProductRequest;
import mx.com.asteci.ws.dto.CreateProductResponse;
import mx.com.asteci.ws.dto.ListProductsResponse;
import mx.com.asteci.ws.exception.ValidationException;
import mx.com.asteci.ws.exception.ValidationFaultBean;


import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Stateless
@WebService(
        serviceName = "ProductWebService",
        portName = "ProductWebServicePort",
        targetNamespace = "http://ws.asteci.com.mx/"
)
public class ProductWebService {

    @Inject
    private ProductService productService;

    @WebMethod(operationName = "createProduct")
    @WebResult(name = "response")
    public CreateProductResponse createProduct(
            @WebParam(name = "request") CreateProductRequest request) throws ValidationException {

        Product product = ProductConverter.toEntity(request);
        validateProduct(request);

        Product createdProduct = productService.createProduct(product);
        return ProductConverter.toCreateResponse(createdProduct);
    }

    @WebMethod(operationName = "listProducts")
    @WebResult(name = "response")
    public ListProductsResponse listProducts() {
        List<Product> products = productService.listProducts();
        return ProductConverter.toListResponse(products);
    }

    private void validateProduct(CreateProductRequest product) throws ValidationException {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<CreateProductRequest>> violations = validator.validate(product);

        if (!violations.isEmpty()) {
            List<String> violationMessages = violations.stream()
                    .map(v -> v.getPropertyPath() + ": " + v.getMessage())
                    .collect(Collectors.toList());

            ValidationFaultBean faultBean = new ValidationFaultBean(
                    "Validación del producto falló",
                    violationMessages
            );
            throw new ValidationException("Validación fallida", faultBean);
        }
    }
}
