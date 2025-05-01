package com.kreditcart.productCatalogue.Services;

import com.kreditcart.productCatalogue.Dtos.ProductDto;
import com.kreditcart.productCatalogue.Models.Product;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface IProductService {
    List<Product> getAllProducts();

    Product getProduct(UUID productId);

    Product createProduct(Product product);

    Product updateProduct(UUID id, Map<String, Object> product);

//    Product getProductDetails(UUID userId, UUID productId);
}
