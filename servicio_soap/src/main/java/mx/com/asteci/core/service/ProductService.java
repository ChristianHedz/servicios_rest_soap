package mx.com.asteci.core.service;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import mx.com.asteci.core.model.Product;
import mx.com.asteci.core.repository.ProductRepository;

import java.util.List;

@Stateless
public class ProductService {

    @Inject
    private ProductRepository productRepository;

    @Transactional(Transactional.TxType.REQUIRED)
    public Product createProduct(Product product) {
        return productRepository.create(product);
    }

    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Product> listProducts() {
        return productRepository.findAll();
    }
}
