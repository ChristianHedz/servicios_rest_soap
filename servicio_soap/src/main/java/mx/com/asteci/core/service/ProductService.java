package mx.com.asteci.core.service;

import jakarta.ejb.Stateless;
import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import mx.com.asteci.core.event.ProductCreatedEvent;
import mx.com.asteci.core.repository.ProductRepository;
import mx.com.asteci.core.repository.entity.Product;

import java.util.List;

/**
 * Servicio de productos.
 * Usa el patrón Observer para notificar la creación de productos.
 */
@Stateless
public class ProductService {

    @Inject
    private ProductRepository productRepository;

    @Inject
    private Event<ProductCreatedEvent> productCreatedEvent; // Observer Pattern

    @Transactional
    public Product createProduct(Product product) {
        Product createdProduct = productRepository.create(product);

        // Observer Pattern: Notificar a todos los observadores
        productCreatedEvent.fire(new ProductCreatedEvent(createdProduct));

        return createdProduct;
    }

    @Transactional
    public List<Product> listProducts() {
        return productRepository.findAll();
    }
}
