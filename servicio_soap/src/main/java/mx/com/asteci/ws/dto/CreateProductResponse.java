package mx.com.asteci.ws.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;


@XmlRootElement(name = "createProductResponse")
@XmlType(name = "CreateProductResponseType", namespace = "http://ws.asteci.com.mx/")
@XmlAccessorType(XmlAccessType.FIELD)
public class CreateProductResponse {

    @XmlElement(required = true)
    private ProductDto product;

    public CreateProductResponse() {
    }

    public CreateProductResponse(ProductDto product) {
        this.product = product;
    }

    public ProductDto getProduct() {
        return product;
    }

    public void setProduct(ProductDto product) {
        this.product = product;
    }
}
