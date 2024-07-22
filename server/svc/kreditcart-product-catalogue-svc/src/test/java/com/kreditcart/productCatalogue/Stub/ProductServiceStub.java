package com.kreditcart.productCatalogue.Stub;

import com.kreditcart.productCatalogue.Models.Product;
import com.kreditcart.productCatalogue.Services.IProductService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//@Service
public class ProductServiceStub implements IProductService {
    Map<Long, Product> products;

    public ProductServiceStub() {
        products = new HashMap<Long, Product>();
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>(this.products.values());
        return products;
    }

    @Override
    public Product getProduct(Long productId) {
        return products.get(productId);
    }

    @Override
    public Product createProduct(Product product) {
        products.put(product.getId(), product);
        return products.get(product.getId());
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        products.put(id, product);
        return products.get(id);
    }

    @Override
    public Product getProductDetails(Long userId, Long productId) {
        return null;
    }
}
