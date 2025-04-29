package com.kreditcart.productCatalogue.Controllers.Internal;

import com.kreditcart.productCatalogue.Dtos.ProductDto;
import com.kreditcart.productCatalogue.Models.Category;
import com.kreditcart.productCatalogue.Models.Product;
import com.kreditcart.productCatalogue.Services.Internal.InternalProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("internal/api/v1/products")
@RestController
public class InternalProductController {
    @Autowired
    InternalProductService productService;

    @GetMapping("{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") UUID id) {
        try {
            Product product = this.productService.getProduct(id);
            return new ResponseEntity<>(product, HttpStatus.OK);
        }catch (Exception exception) {
            System.out.printf("getProduct error : %s%n", exception.getMessage());
            throw exception;
        }
    }

    private Product getProductFromProductDto(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setImageUrl(productDto.getImage());
        product.setIsSpecial(productDto.getIsSpecial());
        product.setSku(productDto.getSku());

        if(productDto.getCategory() != null) {
            Category category = new Category();
            category.setName(productDto.getCategory());
            product.setCategory(category);
        }

        return product;
    }
}