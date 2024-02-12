package com.kreditcart.productCatalogue.Controllers;

import com.kreditcart.productCatalogue.Dtos.ProductDto;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/products")
@RestController
public class ProductController {

    @GetMapping("")
    public String getAllProducts() {
        return "Get all products";
    }

    @GetMapping("{id}")
    public String getProduct(@PathVariable("id") String id1) {
        return "Product with id: " + id1;
    }

    @PostMapping("")
    public String createProduct(@RequestBody ProductDto productDto) {
        return "create Product: " + productDto;
    }

    @PatchMapping("{id}")
    public String updateProduct(@RequestBody ProductDto productDto, @PathVariable("id") String id) {
        return "update Product with id " + id + "----" + productDto;
    }
}
