package mx.com.asteci.converter;

import mx.com.asteci.model.ProductRequest;
import mx.com.asteci.model.ProductResponse;
import mx.com.asteci.ws.CreateProductRequestType;
import mx.com.asteci.ws.ProductType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests de ProductConverter")
class ProductConverterTest {

    private ProductConverter converter;

    @BeforeEach
    void setUp() {
        converter = new ProductConverter();
    }

    @Nested
    @DisplayName("Tests de toSoapRequest()")
    class ToSoapRequestTests {

        @Test
        @DisplayName("Debe convertir ProductRequest a CreateProductRequestType correctamente")
        void testToSoapRequestComplete() {
            // Arrange
            ProductRequest request = new ProductRequest(
                    "Laptop Gaming",
                    "Laptop de alta gama",
                    new BigDecimal("1599.99"),
                    15);

            // Act
            CreateProductRequestType soapRequest = converter.toSoapRequest(request);

            // Assert
            assertAll("Verificar conversión completa",
                    () -> assertEquals("Laptop Gaming", soapRequest.getName()),
                    () -> assertEquals("Laptop de alta gama", soapRequest.getDescription()),
                    () -> assertEquals(new BigDecimal("1599.99"), soapRequest.getPrice()),
                    () -> assertEquals(15, soapRequest.getStock()));
        }
    }

    @Nested
    @DisplayName("Tests de toResponse()")
    class ToResponseTests {

        @Test
        @DisplayName("Debe convertir ProductType a ProductResponse correctamente")
        void testToResponseComplete() {
            // Arrange
            ProductType productType = createProductType(1L, "Teclado Mecánico", "Teclado con switches Cherry MX",
                    new BigDecimal("150.00"), 25);

            // Act
            ProductResponse response = converter.toResponse(productType);

            // Assert
            assertAll("Verificar conversión completa",
                    () -> assertEquals(1L, response.getId()),
                    () -> assertEquals("Teclado Mecánico", response.getName()),
                    () -> assertEquals("Teclado con switches Cherry MX", response.getDescription()),
                    () -> assertEquals(new BigDecimal("150.00"), response.getPrice()),
                    () -> assertEquals(25, response.getStock()));
        }

        @Test
        @DisplayName("Debe manejar id null correctamente")
        void testToResponseWithNullId() {
            // Arrange
            ProductType productType = createProductType(null, "Producto sin ID", "Descripción", new BigDecimal("50.00"),10);

            // Act
            ProductResponse response = converter.toResponse(productType);

            // Assert
            assertNull(response.getId());
            assertEquals("Producto sin ID", response.getName());
        }

        @Test
        @DisplayName("Debe manejar descripción null correctamente")
        void testToResponseWithNullDescription() {
            // Arrange
            ProductType productType = createProductType(5L, "Producto", null, new BigDecimal("75.00"), 20);

            // Act
            ProductResponse response = converter.toResponse(productType);

            // Assert
            assertNull(response.getDescription());
        }
    }

    @Nested
    @DisplayName("Tests de toResponseList()")
    class ToResponseListTests {

        @Test
        @DisplayName("Debe retornar lista vacía cuando se pasa lista vacía")
        void testToResponseListEmpty() {
            // Arrange
            List<ProductType> emptyList = Collections.emptyList();

            // Act
            List<ProductResponse> responses = converter.toResponseList(emptyList);

            // Assert
            assertNotNull(responses);
            assertTrue(responses.isEmpty());
        }

        @Test
        @DisplayName("Debe preservar tamaño y orden de los elementos")
        void testToResponseListPreservesSizeAndOrder() {
            // Arrange
            List<ProductType> products = Arrays.asList(
                    createProductType(1L, "Primero", null, new BigDecimal("10.00"), 1),
                    createProductType(2L, "Segundo", null, new BigDecimal("20.00"), 2),
                    createProductType(3L, "Tercero", null, new BigDecimal("30.00"), 3));

            // Act
            List<ProductResponse> responses = converter.toResponseList(products);

            // Assert
            assertAll("Verificar tamaño, orden y contenido",
                    () -> assertEquals(products.size(), responses.size()),
                    () -> assertEquals(1L, responses.get(0).getId()),
                    () -> assertEquals("Primero", responses.get(0).getName()),
                    () -> assertEquals(2L, responses.get(1).getId()),
                    () -> assertEquals("Segundo", responses.get(1).getName()),
                    () -> assertEquals(3L, responses.get(2).getId()),
                    () -> assertEquals("Tercero", responses.get(2).getName()));
        }
    }

    private ProductType createProductType(Long id, String name, String description, BigDecimal price, int stock) {
        ProductType product = new ProductType();
        product.setId(id);
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setStock(stock);
        return product;
    }
}
