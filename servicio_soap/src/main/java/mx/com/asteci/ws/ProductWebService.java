package mx.com.asteci.ws;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import mx.com.asteci.core.repository.entity.Product;
import mx.com.asteci.core.service.ProductService;
import mx.com.asteci.ws.converter.ProductConverter;
import mx.com.asteci.ws.dto.CreateProductRequest;
import mx.com.asteci.ws.dto.CreateProductResponse;
import mx.com.asteci.ws.dto.ListProductsResponse;
import mx.com.asteci.ws.exception.ValidationException;
import mx.com.asteci.ws.validation.ProductValidator;
import java.util.List;

@Stateless
@WebService(serviceName = "ProductWebService", portName = "ProductWebServicePort", targetNamespace = "http://ws.asteci.com.mx/")
public class ProductWebService {

    @Inject
    private ProductService productService;

    @Inject
    private ProductValidator productValidator;

    @Inject
    private ProductConverter productConverter;

    @WebMethod(operationName = "createProduct")
    @WebResult(name = "response")
    public CreateProductResponse createProduct(
            @WebParam(name = "request") CreateProductRequest request) throws ValidationException {

        productValidator.validate(request);
        Product product = productConverter.toEntity(request);

        Product createdProduct = productService.createProduct(product);
        return productConverter.toCreateResponse(createdProduct);
    }

    @WebMethod(operationName = "listProducts")
    @WebResult(name = "response")
    public ListProductsResponse listProducts() {
        List<Product> products = productService.listProducts();
        return productConverter.toListResponse(products);
    }
}
