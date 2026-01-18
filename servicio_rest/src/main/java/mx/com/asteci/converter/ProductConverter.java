package mx.com.asteci.converter;

import jakarta.enterprise.context.ApplicationScoped;
import mx.com.asteci.model.ProductRequest;
import mx.com.asteci.model.ProductResponse;
import mx.com.asteci.ws.CreateProductRequestType;
import mx.com.asteci.ws.ProductType;

import java.util.List;

@ApplicationScoped
public class ProductConverter {

    public CreateProductRequestType toSoapRequest(ProductRequest request) {
        CreateProductRequestType soapRequest = new CreateProductRequestType();
        soapRequest.setName(request.getName());
        soapRequest.setDescription(request.getDescription());
        soapRequest.setPrice(request.getPrice());
        soapRequest.setStock(request.getStock());
        return soapRequest;
    }

    public ProductResponse toResponse(ProductType product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock());
    }

    public List<ProductResponse> toResponseList(List<ProductType> products) {
        return products.stream()
                .map(this::toResponse)
                .toList();
    }
}
