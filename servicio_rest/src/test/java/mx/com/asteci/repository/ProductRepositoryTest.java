package mx.com.asteci.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import mx.com.asteci.entity.Product;
import org.junit.jupiter.api.*;
import java.math.BigDecimal;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests de ProductRepository")
class ProductRepositoryTest {

    private static EntityManagerFactory emf;
    private EntityManager em;
    private ProductRepository repository;

    @BeforeAll
    static void setUpFactory() {
        emf = Persistence.createEntityManagerFactory("test-unit");
    }

    @AfterAll
    static void tearDownFactory() {
        emf.close();
    }

    @BeforeEach
    void setUp() {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        repository = new ProductRepository(em);
    }

    @AfterEach
    void tearDown() {
        em.getTransaction().rollback();
        em.close();
    }

    @Nested
    @DisplayName("Tests de save()")
    class SaveTests {

        @Test
        @DisplayName("Debe persistir un producto y asignarle ID")
        void testSaveProduct() {
            // Arrange
            Product product = new Product("Laptop Gaming", "Laptop de alta gama", new BigDecimal("1599.99"), 10);

            // Act
            Product saved = repository.save(product);
            em.flush(); // Forzar persistencia

            // Assert
            assertNotNull(saved.getId(), "El producto debe tener un ID asignado");
            assertEquals("Laptop Gaming", saved.getName());
            assertEquals(new BigDecimal("1599.99"), saved.getPrice());
        }

        @Test
        @DisplayName("Debe persistir producto con descripción null")
        void testSaveProductWithNullDescription() {
            // Arrange
            Product product = new Product("Mouse", null, new BigDecimal("50.00"), 100);

            // Act
            Product saved = repository.save(product);
            em.flush();

            // Assert
            assertNotNull(saved.getId());
            assertNull(saved.getDescription());
        }
    }

    @Nested
    @DisplayName("Tests de findAll()")
    class FindAllTests {

        @Test
        @DisplayName("Debe retornar lista vacía cuando no hay productos")
        void testFindAllEmpty() {
            // Act
            List<Product> products = repository.findAll();

            // Assert
            assertNotNull(products);
            assertTrue(products.isEmpty());
        }

        @Test
        @DisplayName("Debe retornar todos los productos guardados")
        void testFindAllWithProducts() {
            // Arrange
            repository.save(new Product("Laptop", "Gaming", new BigDecimal("1500.00"), 5));
            repository.save(new Product("Mouse", "Inalambrico", new BigDecimal("50.00"), 30));
            em.flush();

            // Act
            List<Product> products = repository.findAll();

            // Assert
            assertEquals(2, products.size());
        }
    }

    @Test
    @DisplayName("Constructor vacío para CDI debe crear instancia")
    void testDefaultConstructor() {
        // Act
        ProductRepository repo = new ProductRepository();

        // Assert
        assertNotNull(repo);
    }
}
