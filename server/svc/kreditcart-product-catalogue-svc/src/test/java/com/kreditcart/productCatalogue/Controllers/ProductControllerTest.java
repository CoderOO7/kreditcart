package com.kreditcart.productCatalogue.Controllers;

import com.kreditcart.productCatalogue.Models.Product;
import com.kreditcart.productCatalogue.Services.IProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductControllerTest {
    @Autowired
    private ProductController productController;

//    @Autowired
    @MockBean  // it will instruct spring that this productService is mocked, and pass it wherever it's needed
    private IProductService productService;
    @Captor
    private ArgumentCaptor<Long> idCaptor;

    @Test
    @DisplayName("GetProduct")
    public void Test_GetProduct_ReturnProduct() {
        Product product = new Product();
        product.setTitle("Thar 5 Door");
        product.setPrice((double)2000000);

        // Arrange
        when(productService.getProduct(anyLong())).thenReturn(product);

        // Act
        ResponseEntity<Product> productResp = productController.getProduct(1L);

        // assert
        assertNotNull(productResp.getBody());
        assertEquals(2000000, productResp.getBody().getPrice());
        assertEquals("Thar 5 Door", productResp.getBody().getTitle());
    }

    @Test
    @DisplayName("GetProductInternalDependency")
    public void Test_GetProduct_InternalDependencyThrowsException() {

        // arrange
        when(productService.getProduct(anyLong())).thenThrow(new RuntimeException("Something went very wrong"));

        // act and assert
        assertThrows(RuntimeException.class, ()->productController.getProduct(1L));
    }

    @Test
    @DisplayName("GetProductWithInvalidId")
    public void Test_GetProductWithInvalidId_ThrowsException() {
        assertThrows(RuntimeException.class, ()->productController.getProduct(0L));
    }

    @Test
    public void Test_ProductControllerCallsProductServiceWithSameId() {
        // Act
        Long id = 2L;
        productController.getProduct(id);

        //Assert
        verify(productService).getProduct(idCaptor.capture()); // it capture the id value when passed to service from controller
        assertEquals(id, idCaptor.getValue());
    }
}