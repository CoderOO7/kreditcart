package com.kreditcart.productCatalogue.Services;

import com.kreditcart.productCatalogue.Models.Product;
import com.kreditcart.productCatalogue.Repositories.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StorageProductService implements IProductService {
    private ProductRepo productRepo;

    public StorageProductService(ProductRepo productRepo){
        this.productRepo = productRepo;
    }

    @Override
    public List<Product> getAllProducts() {
        return this.productRepo.findAll();
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
