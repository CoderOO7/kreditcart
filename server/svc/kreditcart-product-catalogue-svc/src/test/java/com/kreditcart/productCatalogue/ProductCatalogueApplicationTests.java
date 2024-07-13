package com.kreditcart.productCatalogue;

import com.kreditcart.productCatalogue.Models.Category;
import com.kreditcart.productCatalogue.Models.Product;
import com.kreditcart.productCatalogue.Repositories.CategoryRepo;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@SpringBootTest
class ProductCatalogueApplicationTests {
    @Autowired
    private CategoryRepo categoryRepo;

    @Test
    @Transactional
    @Rollback(value = false)
    void demonstrateLoading(){
        Category category = categoryRepo.findById(2L).get();
        List<Product> productList = category.getProducts();
        for(Product product: productList){
            System.out.println(product.getId());
        }
    }

}
