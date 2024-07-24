package com.kreditcart.productCatalogue.Services;

import com.kreditcart.productCatalogue.Dtos.UserDto;
import com.kreditcart.productCatalogue.Models.Product;
import com.kreditcart.productCatalogue.Repositories.ProductRepo;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

// Comment below annotation to use StubProductService for test
//@Service
public class StorageProductService implements IProductService {
    private ProductRepo productRepo;
    private RestTemplate restTemplate;

    public StorageProductService(RestTemplate restTemplate,  ProductRepo productRepo){
        this.restTemplate = restTemplate;
        this.productRepo = productRepo;
    }

    @Override
    public List<Product> getAllProducts() {
        return this.productRepo.findAll();
    }

    // created to test service to service api call using service discovery
    @Override
    public Product getProductDetails(Long userId, Long productId) {
        Product product = productRepo.findProductById(productId);
//        RestTemplate restTemplate =  new RestTemplate();
        UserDto userDto = restTemplate.getForEntity("http://userservice/kreditcart-user-svc/users/{id}", UserDto.class, userId).getBody();
        System.out.println("userEmail:" + userDto.getEmail());
        return product;
    }

    @Override
    public Product getProduct(Long productId) {
        return this.productRepo.findById(productId).orElse(null);
    }

    @Override
    public Product createProduct(Product product) {
        return this.productRepo.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        return null;
    }
}
