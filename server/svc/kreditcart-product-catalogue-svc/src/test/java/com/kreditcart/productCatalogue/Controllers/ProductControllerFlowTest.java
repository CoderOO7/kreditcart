package com.kreditcart.productCatalogue.Controllers;


import com.kreditcart.productCatalogue.Dtos.ProductDto;
import com.kreditcart.productCatalogue.Models.Product;
import com.kreditcart.productCatalogue.Services.IProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ProductControllerFlowTest {
    @Autowired
    ProductController productController;

    @Autowired
    IProductService productService;

    @Test
    public void Test_ProductCreateReadAndUpdate_RunSuccessfully() {
        // Arrange
        ProductDto productDto = new ProductDto();

        String haryanviSlang = "Mhari gadiya p likha HARYANA chori re, waqt ka kara hoya syana chori re";
        String punjabiSlang = "Gaddi Neevi Jehi Kara Ke, 22 Inchi De Pawa Ke, Tyre Ghummde Ni Tere Shehar Ghummde";

        productDto.setId(19147L);
        productDto.setTitle("1969 Ford Mustang");
        productDto.setDescription(haryanviSlang);
        productDto.setPrice(15000000.0);

        ProductDto productDtoToUpdate = new ProductDto();
        productDtoToUpdate.setDescription(punjabiSlang);

        // act
        productController.createProduct(productDto);
        ResponseEntity<Product> createdProductResponseEntity =  productController.getProduct(productDto.getId());

        productDto.setDescription(punjabiSlang);
        productController.updateProduct(productDto, productDto.getId());
        ResponseEntity<Product> updatedProductResponseEntity = productController.getProduct(productDto.getId());

        //assert
        assertEquals("1969 Ford Mustang", createdProductResponseEntity.getBody().getTitle());
        assertEquals(haryanviSlang, createdProductResponseEntity.getBody().getDescription());
        assertEquals(15000000.0, createdProductResponseEntity.getBody().getPrice());
        assertEquals(punjabiSlang, updatedProductResponseEntity.getBody().getDescription());
    }
}
