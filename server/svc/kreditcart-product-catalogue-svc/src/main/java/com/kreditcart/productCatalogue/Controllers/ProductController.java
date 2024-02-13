package com.kreditcart.productCatalogue.Controllers;

import com.kreditcart.productCatalogue.Dtos.ProductDto;
import com.kreditcart.productCatalogue.Models.Product;
import com.kreditcart.productCatalogue.Services.IProductService;
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
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public ResponseEntity<Product> createProduct(@RequestBody ProductDto productDto) {
        try {
            Product product = this.productService.createProduct(productDto);
            return new ResponseEntity<>(product, HttpStatus.OK);
        }catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("{id}")
    public String updateProduct(@RequestBody ProductDto productDto, @PathVariable("id") String id) {
        return "update Product with id " + id + "----" + productDto;
    }
}
