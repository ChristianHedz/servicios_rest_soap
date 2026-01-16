package mx.com.asteci.ws.converter;

import mx.com.asteci.core.model.Product;
import mx.com.asteci.ws.dto.CreateProductRequest;
import mx.com.asteci.ws.dto.CreateProductResponse;
import mx.com.asteci.ws.dto.ListProductsResponse;

import java.util.List;

public class ProductConverter {

    public static Product toEntity(CreateProductRequest request) {
        return new Product(
                request.getName(),
                request.getDescription(),
                request.getPrice(),
                request.getStock()
        );
    }

    public static CreateProductResponse toCreateResponse(Product product) {
        return new CreateProductResponse(product);
    }

    public static ListProductsResponse toListResponse(List<Product> products) {
        return new ListProductsResponse(products);
    }
}
