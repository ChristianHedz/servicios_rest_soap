package mx.com.asteci.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.json.bind.JsonbBuilder;
import mx.com.asteci.constants.Constants;
import mx.com.asteci.exception.ValidationException;
import mx.com.asteci.model.ErrorResponse;
import mx.com.asteci.model.Product;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class ProductRestClient {

    private static final String URL = "http://localhost:8081/api/products";
    private final HttpClient client = HttpClient.newHttpClient();

    public Product createProduct(Product product) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL))
                .header(Constants.CONTENT_TYPE, Constants.APPLICATION_JSON)
                .POST(HttpRequest.BodyPublishers.ofString(JsonbBuilder.create().toJson(product)))
                .build();
        
        HttpResponse<String> response = send(request);
        if (response.statusCode() == 400) {
            throw new ValidationException(JsonbBuilder.create().fromJson(response.body(), ErrorResponse.class));
        }
        return JsonbBuilder.create().fromJson(response.body(), Product.class);
    }

    public List<Product> listProducts() {
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(URL)).GET().build();
        HttpResponse<String> response = send(request);
        if (response.body() == null || response.body().equals("[]")) {
            return Collections.emptyList();
        }
        return Arrays.asList(JsonbBuilder.create().fromJson(response.body(), Product[].class));
    }

    private HttpResponse<String> send(HttpRequest request) {
        try {
            return client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException(Constants.REQUEST_INTERRUPTED, e);
        }
    }
}
