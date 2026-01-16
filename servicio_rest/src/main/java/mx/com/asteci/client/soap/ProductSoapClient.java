package mx.com.asteci.client.soap;

import jakarta.enterprise.context.ApplicationScoped;
import mx.com.asteci.model.ProductRequest;
import mx.com.asteci.model.ProductResponse;
import mx.com.asteci.ws.*;
import java.util.List;

@ApplicationScoped
public class ProductSoapClient {

    public ProductResponse createProduct(ProductRequest request) throws ValidationException {
        ProductWebService_Service service = new ProductWebService_Service();
        ProductWebService port = service.getProductWebServicePort();

        CreateProductRequestType soapRequest = new CreateProductRequestType();
        soapRequest.setName(request.getName());
        soapRequest.setDescription(request.getDescription());
        soapRequest.setPrice(request.getPrice());
        soapRequest.setStock(request.getStock());

        CreateProduct createProduct = new CreateProduct();
        createProduct.setRequest(soapRequest);

        CreateProductResponse response = port.createProduct(createProduct);
        ProductType product = response.getResponse().getProduct();

        return toResponse(product);
    }

    public List<ProductResponse> listProducts() {
        ProductWebService_Service service = new ProductWebService_Service();
        ProductWebService port = service.getProductWebServicePort();

        ListProducts listProducts = new ListProducts();
        ListProductsResponse response = port.listProducts(listProducts);
        List<ProductType> products = response.getResponse().getProducts();

        return products.stream().map(this::toResponse).toList();
    }

    private ProductResponse toResponse(ProductType product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock()
        );
    }
}
