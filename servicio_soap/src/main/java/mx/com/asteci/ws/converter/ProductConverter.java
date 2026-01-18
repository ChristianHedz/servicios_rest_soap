package mx.com.asteci.ws.converter;

import jakarta.enterprise.context.ApplicationScoped;
import mx.com.asteci.core.repository.entity.Product;
import mx.com.asteci.ws.dto.CreateProductRequest;
import mx.com.asteci.ws.dto.CreateProductResponse;
import mx.com.asteci.ws.dto.ListProductsResponse;
import mx.com.asteci.ws.dto.ProductDto;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ProductConverter {

    public Product toEntity(CreateProductRequest request) {
        return new Product(
                request.getName(),
                request.getDescription(),
                request.getPrice(),
                request.getStock());
    }

    public ProductDto toDto(Product product) {
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock());
    }

    public CreateProductResponse toCreateResponse(Product product) {
        return new CreateProductResponse(toDto(product));
    }

    public ListProductsResponse toListResponse(List<Product> products) {
        List<ProductDto> dtos = products.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
        return new ListProductsResponse(dtos);
    }
}
