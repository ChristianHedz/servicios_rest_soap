package mx.com.asteci.model;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public class ProductRequest {

    @NotNull(message = "{product.name.notNull}")
    @NotBlank(message = "{product.name.notBlank}")
    @Size(min = 3, max = 100, message = "{product.name.size}")
    private String name;

    @Size(max = 500, message = "{product.description.size}")
    private String description;

    @NotNull(message = "{product.price.notNull}")
    @DecimalMin(value = "0.01", message = "{product.price.decimalMin}")
    private BigDecimal price;

    @NotNull(message = "{product.stock.notNull}")
    @Min(value = 0, message = "{product.stock.min}")
    private Integer stock;

    public ProductRequest() {
    }

    public ProductRequest(String name, String description, BigDecimal price, Integer stock) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
