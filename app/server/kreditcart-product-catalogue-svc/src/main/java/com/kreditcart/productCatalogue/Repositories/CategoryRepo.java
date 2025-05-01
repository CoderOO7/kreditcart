package com.kreditcart.productCatalogue.Repositories;

import com.kreditcart.productCatalogue.Models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CategoryRepo extends JpaRepository<Category, UUID> {
    Category save(Category category);
}
