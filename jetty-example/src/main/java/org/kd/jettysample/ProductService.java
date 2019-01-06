package org.kd.jettysample;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private List<Product> products;

    public List<Product> getProducts() {

        for (int i = 0; i < 5; i++) {
            Product product = new Product("Package " + i, Double.valueOf(10 + i));
            products.add(product);
        }

        return products;
    }
}
