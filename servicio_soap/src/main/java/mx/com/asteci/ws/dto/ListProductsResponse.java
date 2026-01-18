package mx.com.asteci.ws.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

import java.util.List;


@XmlRootElement(name = "listProductsResponse")
@XmlType(name = "ListProductsResponseType", namespace = "http://ws.asteci.com.mx/")
@XmlAccessorType(XmlAccessType.FIELD)
public class ListProductsResponse {

    @XmlElement(required = true)
    private List<ProductDto> products;

    public ListProductsResponse() {
    }

    public ListProductsResponse(List<ProductDto> products) {
        this.products = products;
    }

    public List<ProductDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDto> products) {
        this.products = products;
    }
}
