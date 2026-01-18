package mx.com.asteci.core.service;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import mx.com.asteci.core.repository.ProductRepository;
import mx.com.asteci.core.repository.entity.Product;
import java.util.List;

@Stateless
public class ProductService {

    @Inject
    private ProductRepository productRepository;

    @Transactional
    public Product createProduct(Product product) {
        return productRepository.create(product);
    }

    @Transactional
    public List<Product> listProducts() {
        return productRepository.findAll();
    }
}
