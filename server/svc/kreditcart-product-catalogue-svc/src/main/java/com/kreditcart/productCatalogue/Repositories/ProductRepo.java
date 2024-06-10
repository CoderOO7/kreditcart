package com.kreditcart.productCatalogue.Repositories;

import com.kreditcart.productCatalogue.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    Product save(Product product);
}
