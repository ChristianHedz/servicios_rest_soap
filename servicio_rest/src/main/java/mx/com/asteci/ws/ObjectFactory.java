
package mx.com.asteci.ws;

import javax.xml.namespace.QName;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.com.asteci.ws package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _CreateProduct_QNAME = new QName("http://ws.asteci.com.mx/", "createProduct");
    private final static QName _CreateProductRequest_QNAME = new QName("http://ws.asteci.com.mx/", "createProductRequest");
    private final static QName _CreateProductResponse_QNAME = new QName("http://ws.asteci.com.mx/", "createProductResponse");
    private final static QName _ListProducts_QNAME = new QName("http://ws.asteci.com.mx/", "listProducts");
    private final static QName _ListProductsResponse_QNAME = new QName("http://ws.asteci.com.mx/", "listProductsResponse");
    private final static QName _Product_QNAME = new QName("http://ws.asteci.com.mx/", "product");
    private final static QName _ValidationFault_QNAME = new QName("http://ws.asteci.com.mx/", "ValidationFault");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.com.asteci.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CreateProduct }
     * 
     */
    public CreateProduct createCreateProduct() {
        return new CreateProduct();
    }

    /**
     * Create an instance of {@link CreateProductRequestType }
     * 
     */
    public CreateProductRequestType createCreateProductRequestType() {
        return new CreateProductRequestType();
    }

    /**
     * Create an instance of {@link CreateProductResponse }
     * 
     */
    public CreateProductResponse createCreateProductResponse() {
        return new CreateProductResponse();
    }

    /**
     * Create an instance of {@link ListProducts }
     * 
     */
    public ListProducts createListProducts() {
        return new ListProducts();
    }

    /**
     * Create an instance of {@link ListProductsResponse }
     * 
     */
    public ListProductsResponse createListProductsResponse() {
        return new ListProductsResponse();
    }

    /**
     * Create an instance of {@link ProductType }
     * 
     */
    public ProductType createProductType() {
        return new ProductType();
    }

    /**
     * Create an instance of {@link ValidationFault }
     * 
     */
    public ValidationFault createValidationFault() {
        return new ValidationFault();
    }

    /**
     * Create an instance of {@link CreateProductResponseType }
     * 
     */
    public CreateProductResponseType createCreateProductResponseType() {
        return new CreateProductResponseType();
    }

    /**
     * Create an instance of {@link ListProductsResponseType }
     * 
     */
    public ListProductsResponseType createListProductsResponseType() {
        return new ListProductsResponseType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateProduct }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CreateProduct }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.asteci.com.mx/", name = "createProduct")
    public JAXBElement<CreateProduct> createCreateProduct(CreateProduct value) {
        return new JAXBElement<CreateProduct>(_CreateProduct_QNAME, CreateProduct.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateProductRequestType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CreateProductRequestType }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.asteci.com.mx/", name = "createProductRequest")
    public JAXBElement<CreateProductRequestType> createCreateProductRequest(CreateProductRequestType value) {
        return new JAXBElement<CreateProductRequestType>(_CreateProductRequest_QNAME, CreateProductRequestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateProductResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CreateProductResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.asteci.com.mx/", name = "createProductResponse")
    public JAXBElement<CreateProductResponse> createCreateProductResponse(CreateProductResponse value) {
        return new JAXBElement<CreateProductResponse>(_CreateProductResponse_QNAME, CreateProductResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListProducts }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ListProducts }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.asteci.com.mx/", name = "listProducts")
    public JAXBElement<ListProducts> createListProducts(ListProducts value) {
        return new JAXBElement<ListProducts>(_ListProducts_QNAME, ListProducts.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListProductsResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ListProductsResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.asteci.com.mx/", name = "listProductsResponse")
    public JAXBElement<ListProductsResponse> createListProductsResponse(ListProductsResponse value) {
        return new JAXBElement<ListProductsResponse>(_ListProductsResponse_QNAME, ListProductsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProductType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ProductType }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.asteci.com.mx/", name = "product")
    public JAXBElement<ProductType> createProduct(ProductType value) {
        return new JAXBElement<ProductType>(_Product_QNAME, ProductType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValidationFault }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ValidationFault }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.asteci.com.mx/", name = "ValidationFault")
    public JAXBElement<ValidationFault> createValidationFault(ValidationFault value) {
        return new JAXBElement<ValidationFault>(_ValidationFault_QNAME, ValidationFault.class, null, value);
    }

}
