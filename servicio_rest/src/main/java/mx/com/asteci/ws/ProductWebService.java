package mx.com.asteci.ws;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.xml.bind.annotation.XmlSeeAlso;

@WebService(name = "ProductWebService", targetNamespace = "http://ws.asteci.com.mx/")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface ProductWebService {

    @WebMethod
    @WebResult(name = "createProductResponse", targetNamespace = "http://ws.asteci.com.mx/", partName = "parameters")
    CreateProductResponse createProduct(
        @WebParam(name = "createProduct", targetNamespace = "http://ws.asteci.com.mx/", partName = "parameters")
        CreateProduct parameters)
        throws ValidationException;

    @WebMethod
    @WebResult(name = "listProductsResponse", targetNamespace = "http://ws.asteci.com.mx/", partName = "parameters")
    ListProductsResponse listProducts(
        @WebParam(name = "listProducts", targetNamespace = "http://ws.asteci.com.mx/", partName = "parameters")
        ListProducts parameters);
}
