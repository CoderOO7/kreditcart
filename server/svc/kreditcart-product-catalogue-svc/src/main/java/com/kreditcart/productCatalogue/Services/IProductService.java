package com.kreditcart.productCatalogue.Services;

import com.kreditcart.productCatalogue.Dtos.ProductDto;
import com.kreditcart.productCatalogue.Models.Product;

import java.util.List;

public interface IProductService {
    List<Product> getAllProducts();

    Product getProduct(Long productId);

    Product createProduct( ProductDto productDto);

    String updateProduct(ProductDto productDto, String id);
}
