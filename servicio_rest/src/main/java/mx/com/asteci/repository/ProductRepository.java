package mx.com.asteci.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import mx.com.asteci.entity.Product;

import java.util.List;

@ApplicationScoped
public class ProductRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public ProductRepository() {
    }

    // Constructor para inyeccion manual en tests
    public ProductRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Product save(Product product) {
        entityManager.persist(product);
        return product;
    }

    public List<Product> findAll() {
        return entityManager.createQuery("SELECT p FROM Product p", Product.class)
                .getResultList();
    }
}
