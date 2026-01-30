package mx.com.asteci.controller;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import mx.com.asteci.model.ProductRequest;
import mx.com.asteci.service.ProductService;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductController {

    private ProductService productService;

    public ProductController() {
    }

    @Inject
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @POST
    public Response createProduct(@Valid ProductRequest request) {
        return Response.status(Response.Status.CREATED)
                .entity(productService.createProduct(request))
                .build();
    }

    @GET
    public Response listProducts() {
        return Response.ok(productService.listProducts()).build();
    }
}
