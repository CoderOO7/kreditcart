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
import java.util.Map;
import java.util.UUID;

@RequestMapping("/api/v1/products")
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
    public ResponseEntity<Product> getProduct(@PathVariable("id") UUID id) {
        try {
            Product product = this.productService.getProduct(id);
            return new ResponseEntity<>(product, HttpStatus.OK);
        }catch (Exception exception) {
            System.out.printf("getProduct error : %s%n", exception.getMessage());
            throw exception;
        }
    }

    @GetMapping("{id1}/{id2}")
    public ResponseEntity<Product> getProductDetails(@PathVariable("id1") UUID id1, @PathVariable("id2") UUID id2) {
        try {
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
            System.out.printf("createProduct error : %s%n", exception.getMessage());
            throw exception;
        }
    }

    @PatchMapping("{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody Map<String, Object> payloads, @PathVariable("id") UUID id) {
        try{
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
