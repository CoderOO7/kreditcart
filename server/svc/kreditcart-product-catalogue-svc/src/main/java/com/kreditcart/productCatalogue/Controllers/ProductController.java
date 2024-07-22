package com.kreditcart.productCatalogue.Controllers;

import com.kreditcart.productCatalogue.Dtos.ProductDto;
import com.kreditcart.productCatalogue.Models.Category;
import com.kreditcart.productCatalogue.Models.Product;
import com.kreditcart.productCatalogue.Services.IProductService;
import jakarta.ws.rs.Path;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/products")
@RestController
public class ProductController {

    IProductService productService;

    ProductController(IProductService productService){
        this.productService = productService;
    }

    @GetMapping("")
    public ResponseEntity<List<Product>> getAllProducts() {
        try {
            List<Product> products = this.productService.getAllProducts();
            return new ResponseEntity<>(products, HttpStatus.OK);
        }catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long id) {
        try {
            if(id < 1) {
                throw new IllegalArgumentException("Product id is invalid");
            }
            Product product = this.productService.getProduct(id);
            return new ResponseEntity<>(product, HttpStatus.OK);
        }catch (Exception exception) {
            throw exception;
        }
    }

    @GetMapping("{id1}/{id2}")
    public ResponseEntity<Product> getProductDetails(@PathVariable("id1") Long id1, @PathVariable("id2") Long id2) {
        try {
            if(id1 < 1) {
                throw new IllegalArgumentException("Product id is invalid");
            }
            Product product = this.productService.getProductDetails(id1, id2);
            return new ResponseEntity<>(product, HttpStatus.OK);
        }catch (Exception exception) {
            throw exception;
        }
    }

    @PostMapping("")
    public ResponseEntity<Product> createProduct(@RequestBody ProductDto productDto) {
        try {
            Product payloads = this.getProductFromProductDto(productDto);
            Product product = this.productService.createProduct(payloads);
            return new ResponseEntity<>(product, HttpStatus.OK);
        }catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody ProductDto productDto, @PathVariable("id") Long id) {
        try{
            Product payloads = this.getProductFromProductDto(productDto);
            Product product = this.productService.updateProduct(id, payloads);
            return new ResponseEntity<>(product, HttpStatus.OK);
        }catch (Exception exception) {
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

        if(productDto.getCategory() != null) {
            Category category = new Category();
            category.setName(productDto.getCategory());
            product.setCategory(category);
        }

        return product;
    }
}
