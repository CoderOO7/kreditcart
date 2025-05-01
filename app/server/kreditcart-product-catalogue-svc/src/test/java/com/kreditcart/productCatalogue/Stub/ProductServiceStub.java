package com.kreditcart.productCatalogue.Stub;

import com.kreditcart.productCatalogue.Models.Product;
import com.kreditcart.productCatalogue.Services.IProductService;
import org.springframework.stereotype.Service;

import java.util.*;


//@Service
public class ProductServiceStub implements IProductService {
    Map<UUID, Product> products;

    public ProductServiceStub() {
        products = new HashMap<UUID, Product>();
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>(this.products.values());
        return products;
    }

    @Override
    public Product getProduct(UUID productId) {
        return products.get(productId);
    }

    @Override
    public Product createProduct(Product product) {
        products.put(product.getId(), product);
        return products.get(product.getId());
    }

    @Override
    public Product updateProduct(UUID id, Map<String, Object> product) {
        return null;
//        products.put(id, product);
//        return products.get(id);
    }

//    @Override
//    public Product getProductDetails(UUID userId, UUID productId) {
//        return null;
//    }
}
