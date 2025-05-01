package com.kreditcart.productCatalogue.Repositories;

import com.kreditcart.productCatalogue.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepo extends JpaRepository<Product, UUID> {
    Product save(Product product);

    Product findProductById(UUID id);

    List<Product> findProductByPriceBetween(double low, double high);

    List<Product> findAllByOrderByIdDesc();

    List<Product> findAllByIsSpecial(Boolean flag);

    @Query("Select p.title from Product p where p.id=?1")
    String getProductTitleFromId(Long id);

    @Query("Select distinct c.name from Category c join Product p on p.id=:id")
    List<String> getCategoryNameFromProductId(@Param("id") Long id);
}
