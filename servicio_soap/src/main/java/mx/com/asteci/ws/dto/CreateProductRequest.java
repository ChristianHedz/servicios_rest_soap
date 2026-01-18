package mx.com.asteci.ws.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

@XmlRootElement(name = "createProductRequest")
@XmlType(name = "CreateProductRequestType", namespace = "http://ws.asteci.com.mx/")
@XmlAccessorType(XmlAccessType.FIELD)
public class CreateProductRequest {

    @NotBlank(message = "{product.name.notBlank}")
    @Size(min = 3, max = 100, message = "{product.name.size}")
    @XmlElement(required = true)
    private String name;

    @Size(max = 500, message = "{product.description.size}")
    @XmlElement
    private String description;

    @NotNull(message = "{product.price.notNull}")
    @DecimalMin(value = "0.01", message = "{product.price.decimalMin}")
    @XmlElement(required = true)
    private BigDecimal price;

    @NotNull(message = "{product.stock.notNull}")
    @Min(value = 0, message = "{product.stock.min}")
    @XmlElement(required = true)
    private Integer stock;

    public CreateProductRequest() {
    }

    public CreateProductRequest(String name, String description, BigDecimal price, Integer stock) {
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
