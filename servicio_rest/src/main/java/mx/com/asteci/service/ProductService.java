package mx.com.asteci.service;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import mx.com.asteci.client.soap.ProductSoapClient;
import mx.com.asteci.model.ProductRequest;
import mx.com.asteci.model.ProductResponse;
import mx.com.asteci.ws.ValidationException;

import java.util.List;

@Stateless
public class ProductService {

    @Inject
    private ProductSoapClient soapClient;

    public ProductResponse createProduct(ProductRequest request) throws ValidationException {
        return soapClient.createProduct(request);
    }

    public List<ProductResponse> listProducts() {
        return soapClient.listProducts();
    }
}
