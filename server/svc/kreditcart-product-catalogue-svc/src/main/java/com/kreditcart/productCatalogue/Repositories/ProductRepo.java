package com.kreditcart.productCatalogue.Repositories;

import com.kreditcart.productCatalogue.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    Product save(Product product);

    Product findProductById(Long id);

    List<Product> findProductByPriceBetween(double low, double high);

    List<Product> findAllByOrderByIdDesc();

    List<Product> findAllByIsSpecial(Boolean flag);
}
