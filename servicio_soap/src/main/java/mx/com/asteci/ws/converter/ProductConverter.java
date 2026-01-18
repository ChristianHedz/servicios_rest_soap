package mx.com.asteci.ws.converter;

import mx.com.asteci.core.repository.entity.Product;
import mx.com.asteci.ws.dto.CreateProductRequest;
import mx.com.asteci.ws.dto.CreateProductResponse;
import mx.com.asteci.ws.dto.ListProductsResponse;
import mx.com.asteci.ws.dto.ProductDto;

import java.util.List;
import java.util.stream.Collectors;

public class ProductConverter {

    public static Product toEntity(CreateProductRequest request) {
        return new Product(
                request.getName(),
                request.getDescription(),
                request.getPrice(),
                request.getStock()
        );
    }

    public static ProductDto toDto(Product product) {
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock()
        );
    }

    public static CreateProductResponse toCreateResponse(Product product) {
        return new CreateProductResponse(toDto(product));
    }

    public static ListProductsResponse toListResponse(List<Product> products) {
        List<ProductDto> dtos = products.stream()
                .map(ProductConverter::toDto)
                .collect(Collectors.toList());
        return new ListProductsResponse(dtos);
    }
}
