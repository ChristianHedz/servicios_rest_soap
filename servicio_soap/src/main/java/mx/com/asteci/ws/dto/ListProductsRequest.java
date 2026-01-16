package mx.com.asteci.ws.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "listProductsRequest")
@XmlType(name = "ListProductsRequestType", namespace = "http://ws.asteci.com.mx/")
@XmlAccessorType(XmlAccessType.FIELD)
public class ListProductsRequest {

    public ListProductsRequest() {
    }
}
