package mx.com.asteci.core.repository;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import mx.com.asteci.core.model.Product;
import java.util.List;

@Stateless
public class ProductRepository {

    @PersistenceContext(unitName = "ProductsPU")
    private EntityManager entityManager;

    public Product create(Product product) {
        entityManager.persist(product);
        return product;
    }

    public List<Product> findAll() {
        return entityManager
                .createQuery("SELECT p FROM Product p", Product.class)
                .getResultList();
    }
}
