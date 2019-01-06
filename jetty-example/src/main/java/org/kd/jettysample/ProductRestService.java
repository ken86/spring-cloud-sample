package org.kd.jettysample;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.Collection;

@Path("/product")
public class ProductRestService {

    @Inject
    private ProductService productService;

    @Produces("application/json")
    @GET
    public Collection<Product> getProducts() {
        return productService.getProducts();
    }

}
