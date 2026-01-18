package mx.com.asteci.client.soap;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import mx.com.asteci.converter.ProductConverter;
import mx.com.asteci.model.ProductRequest;
import mx.com.asteci.model.ProductResponse;
import mx.com.asteci.ws.*;
import java.util.List;

@ApplicationScoped
public class ProductSoapClient {

    @Inject
    private ProductConverter productConverter;

    public ProductResponse createProduct(ProductRequest request) throws ValidationException {
        ProductWebService_Service service = new ProductWebService_Service();
        ProductWebService port = service.getProductWebServicePort();

        CreateProductRequestType soapRequest = productConverter.toSoapRequest(request);

        CreateProductResponseType response = port.createProduct(soapRequest);
        ProductType product = response.getProduct();

        return productConverter.toResponse(product);
    }

    public List<ProductResponse> listProducts() {
        ProductWebService_Service service = new ProductWebService_Service();
        ProductWebService port = service.getProductWebServicePort();

        ListProductsResponseType response = port.listProducts();

        return productConverter.toResponseList(response.getProducts());
    }
}
