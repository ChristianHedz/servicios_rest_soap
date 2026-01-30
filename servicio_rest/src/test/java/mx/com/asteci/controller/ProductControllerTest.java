package mx.com.asteci.controller;

import jakarta.ws.rs.core.Response;
import mx.com.asteci.model.ProductRequest;
import mx.com.asteci.model.ProductResponse;
import mx.com.asteci.stub.ProductServiceStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests de ProductController")
class ProductControllerTest {

    private ProductServiceStub serviceStub;
    private ProductController productController;

    @BeforeEach
    void setUp() {
        serviceStub = new ProductServiceStub();
        productController = new ProductController(serviceStub);
    }

    @Test
    @DisplayName("Constructor vacío para CDI debe crear instancia")
    void testDefaultConstructor() {
        ProductController controller = new ProductController();
        assertNotNull(controller);
    }

    @Nested
    @DisplayName("Tests de POST /products (createProduct)")
    class CreateProductTests {

        @Test
        @DisplayName("Debe retornar 201 CREATED con el producto creado")
        void testCreateProductSuccess() {
            // Arrange
            ProductRequest request = new ProductRequest(
                    "Laptop Gaming",
                    "Laptop de alta gama",
                    new BigDecimal("1599.99"),
                    10);

            // Act
            Response response = productController.createProduct(request);

            // Assert
            assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
            assertNotNull(response.getEntity());
            assertInstanceOf(ProductResponse.class, response.getEntity());

            ProductResponse body = (ProductResponse) response.getEntity();
            assertEquals("Laptop Gaming", body.getName());
        }

        @Test
        @DisplayName("Debe delegar la creacion al service")
        void testCreateProductDelegatesToService() {
            // Arrange
            ProductRequest request = new ProductRequest(
                    "Mouse",
                    "Inalambrico",
                    new BigDecimal("50.00"),
                    30);

            // Act
            productController.createProduct(request);

            // Assert
            assertEquals(1, serviceStub.count());
        }
    }

    @Nested
    @DisplayName("Tests de GET /products (listProducts)")
    class ListProductsTests {

        @Test
        @DisplayName("Debe retornar 200 OK con lista vacia")
        void testListProductsEmpty() {
            // Act
            Response response = productController.listProducts();

            // Assert
            assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

            @SuppressWarnings("unchecked")
            List<ProductResponse> body = (List<ProductResponse>) response.getEntity();
            assertNotNull(body);
            assertTrue(body.isEmpty());
        }

        @Test
        @DisplayName("Debe retornar 200 OK con lista de productos")
        void testListProductsWithData() {
            // Arrange
            serviceStub.addProduct(new ProductResponse(1L, "Laptop", "Gaming", new BigDecimal("1500.00"), 5));
            serviceStub.addProduct(new ProductResponse(2L, "Mouse", "Inalambrico", new BigDecimal("50.00"), 30));

            // Act
            Response response = productController.listProducts();

            // Assert
            assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

            @SuppressWarnings("unchecked")
            List<ProductResponse> body = (List<ProductResponse>) response.getEntity();
            assertEquals(2, body.size());
            assertEquals("Laptop", body.get(0).getName());
            assertEquals("Mouse", body.get(1).getName());
        }
    }
}
