package mx.com.asteci.bean;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import mx.com.asteci.exception.ValidationException;
import mx.com.asteci.model.Product;
import mx.com.asteci.service.ProductRestClient;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class ProductBean implements Serializable {

    @Inject
    private ProductRestClient restClient;

    private Product product = new Product();
    private List<Product> products;

    @PostConstruct
    public void init() {
        products = restClient.listProducts();
    }

    public void createProduct() {
        try {
            restClient.createProduct(product);
            product = new Product();
            products = restClient.listProducts();
            addMessage(FacesMessage.SEVERITY_INFO, "Producto creado");
        } catch (ValidationException e) {
            e.getErrorResponse().getErrors().forEach(error -> 
                addMessage(FacesMessage.SEVERITY_ERROR, error));
        }
    }

    private void addMessage(FacesMessage.Severity severity, String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, message, null));
    }

    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }
    public List<Product> getProducts() { return products; }
}
