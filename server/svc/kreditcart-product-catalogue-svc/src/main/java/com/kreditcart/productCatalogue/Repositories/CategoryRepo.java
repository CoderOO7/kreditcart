package com.kreditcart.productCatalogue.Repositories;

import com.kreditcart.productCatalogue.Models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {
    Category save(Category category);
}
