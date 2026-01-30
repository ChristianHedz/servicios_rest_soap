package mx.com.asteci.stub;

import mx.com.asteci.model.ProductRequest;
import mx.com.asteci.model.ProductResponse;
import mx.com.asteci.service.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class ProductServiceStub extends ProductService {

    private final List<ProductResponse> products = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public ProductServiceStub() {
        super(null);
    }

    @Override
    public ProductResponse createProduct(ProductRequest request) {
        ProductResponse response = new ProductResponse(
                idGenerator.getAndIncrement(),
                request.getName(),
                request.getDescription(),
                request.getPrice(),
                request.getStock()
        );
        products.add(response);
        return response;
    }

    @Override
    public List<ProductResponse> listProducts() {
        return new ArrayList<>(products);
    }
  
    public void clear() {
        products.clear();
        idGenerator.set(1);
    }

    public int count() {
        return products.size();
    }

    public void addProduct(ProductResponse product) {
        products.add(product);
    }
}
