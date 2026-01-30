package mx.com.asteci.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import mx.com.asteci.model.ProductRequest;
import mx.com.asteci.model.ProductResponse;
import mx.com.asteci.repository.ProductRepository;
import org.junit.jupiter.api.*;
import java.math.BigDecimal;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests de integración de ProductService")
class ProductServiceTest {

    private static EntityManagerFactory emf;
    private EntityManager em;
    private ProductRepository repository;
    private ProductService productService;

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
        productService = new ProductService(repository);
    }

    @AfterEach
    void tearDown() {
        em.getTransaction().rollback();
        em.close();
    }

    @Test
    @DisplayName("Constructor vacío para CDI debe crear instancia")
    void testDefaultConstructor() {
        ProductService service = new ProductService();
        assertNotNull(service);
    }

    @Nested
    @DisplayName("Tests de createProduct()")
    class CreateProductTests {

        @Test
        @DisplayName("Debe crear producto y retornar respuesta con ID")
        void testCreateProductSuccess() {
            // Arrange
            ProductRequest request = new ProductRequest(
                    "Laptop Gaming",
                    "Laptop de alta gama",
                    new BigDecimal("1599.99"),
                    10);

            // Act
            ProductResponse response = productService.createProduct(request);
            em.flush();

            // Assert
            assertAll("Verificar producto creado",
                    () -> assertNotNull(response.getId()),
                    () -> assertEquals("Laptop Gaming", response.getName()),
                    () -> assertEquals("Laptop de alta gama", response.getDescription()),
                    () -> assertEquals(new BigDecimal("1599.99"), response.getPrice()),
                    () -> assertEquals(10, response.getStock()));
        }
    }

    @Nested
    @DisplayName("Tests de listProducts()")
    class ListProductsTests {

        @Test
        @DisplayName("Debe retornar lista vacia cuando no hay productos")
        void testListProductsEmpty() {
            // Act
            List<ProductResponse> products = productService.listProducts();

            // Assert
            assertNotNull(products);
            assertTrue(products.isEmpty());
        }

        @Test
        @DisplayName("Debe retornar todos los productos creados")
        void testListProductsWithData() {
            // Arrange
            productService.createProduct(new ProductRequest("Laptop", "Gaming", new BigDecimal("1500.00"), 5));
            productService.createProduct(new ProductRequest("Mouse", "Inalambrico", new BigDecimal("50.00"), 30));
            em.flush();

            // Act
            List<ProductResponse> products = productService.listProducts();

            // Assert
            assertEquals(2, products.size());
        }
    }
}
