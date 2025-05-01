package com.kreditcart.productCatalogue.Services.Internal;

import com.kreditcart.productCatalogue.Exceptions.ProductNotFoundException;
import com.kreditcart.productCatalogue.Models.Product;
import com.kreditcart.productCatalogue.Repositories.CategoryRepo;
import com.kreditcart.productCatalogue.Repositories.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
public class InternalProductService {
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private RestTemplate restTemplate;

    public InternalProductService(RestTemplate restTemplate, ProductRepo productRepo, CategoryRepo categoryRepo){
        this.restTemplate = restTemplate;
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
    }

    public Product getProduct(UUID productId) {
        return  this.productRepo
                .findById(productId)
                .orElseThrow(()-> new ProductNotFoundException(String.format("Product not found with given id %s", productId)));
    }
}

