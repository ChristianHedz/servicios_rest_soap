package mx.com.asteci.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import mx.com.asteci.entity.Product;
import mx.com.asteci.model.ProductRequest;
import mx.com.asteci.model.ProductResponse;
import mx.com.asteci.repository.ProductRepository;

import java.util.List;

@ApplicationScoped
public class ProductService {

    private ProductRepository productRepository;

    public ProductService() {
    }

    @Inject
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public ProductResponse createProduct(ProductRequest request) {
        Product product = new Product(
                request.getName(),
                request.getDescription(),
                request.getPrice(),
                request.getStock()
        );
        Product saved = productRepository.save(product);
        return toResponse(saved);
    }

    public List<ProductResponse> listProducts() {
        return productRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    private ProductResponse toResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock()
        );
    }
}
