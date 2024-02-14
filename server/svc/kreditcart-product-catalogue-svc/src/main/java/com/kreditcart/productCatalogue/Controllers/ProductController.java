package com.kreditcart.productCatalogue.Controllers;

import com.kreditcart.productCatalogue.Clients.FakeStore.Dtos.FakeStoreProductDto;
import com.kreditcart.productCatalogue.Dtos.ProductDto;
import com.kreditcart.productCatalogue.Models.Category;
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
            Product payloads = this.getProductFromProductDto(productDto);
            Product product = this.productService.createProduct(payloads);
            return new ResponseEntity<>(product, HttpStatus.OK);
        }catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody ProductDto productDto, @PathVariable("id") Long id) {
//        try{
            Product payloads = this.getProductFromProductDto(productDto);
            Product product = this.productService.updateProduct(id, payloads);
            return new ResponseEntity<>(product, HttpStatus.OK);
//        }catch (Exception exception) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
    }

    private Product getProductFromProductDto(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setImageUrl(productDto.getImage());

//        Category category = new Category();
//        category.setName(productDto.getCategory());
//        product.setCategory(category);

        return product;
    }
}
