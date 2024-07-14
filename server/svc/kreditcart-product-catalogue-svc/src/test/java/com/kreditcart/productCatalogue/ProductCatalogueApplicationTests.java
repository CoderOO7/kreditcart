package com.kreditcart.productCatalogue;

import com.kreditcart.productCatalogue.Models.Category;
import com.kreditcart.productCatalogue.Models.Product;
import com.kreditcart.productCatalogue.Repositories.CategoryRepo;
import com.kreditcart.productCatalogue.Repositories.ProductRepo;
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

    @Autowired
    private ProductRepo productRepo;

    @Test
    @Transactional
    @Rollback(value = false)
    void demonstrateLoading(){
        Category category = categoryRepo.findById(1L).get();
        List<Product> productList = category.getProducts();
        for(Product product: productList){
            System.out.println("id----" + product.getId());
        }
    }

    @Test
    @Transactional
    @Rollback(value = false)
    void demonstrateNPlusOneProblem(){
        List<Category> categoryList = categoryRepo.findAll();
        for(Category category: categoryList) {
            List<Product> productList = category.getProducts();
            for(Product product: productList){
                System.out.println("id----" + product.getId());
            }
        }
    }

    @Test
    @Transactional
    @Rollback(value = false)
    void howJpaGeneratesQueries(){
//        Product product = productRepo.findProductById(2L);
//        System.out.println("productId---" +product.getId());

//        List<Product> productList  = productRepo.findProductByPriceBetween(1800000, 2000000);
//        for(Product product: productList) {
//            System.out.println("productId---" + product.getId());
//        }


        List<Product> productList  = productRepo.findAllByOrderByIdDesc();
        for(Product product: productList) {
            System.out.println("productId---" + product.getId());
        }


        List<Product> specialProductList  = productRepo.findAllByIsSpecial(true);
        for(Product product: specialProductList) {
            System.out.println("specialProductId---" + product.getId());
        }
    }
}
