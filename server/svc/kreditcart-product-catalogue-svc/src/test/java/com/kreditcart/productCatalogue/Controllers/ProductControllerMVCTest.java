package com.kreditcart.productCatalogue.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kreditcart.productCatalogue.Models.Product;
import com.kreditcart.productCatalogue.Services.IProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
public class ProductControllerMVCTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void Test_GetProducts_ReceiveSuccessfulResponse() throws Exception {
        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setTitle("Iphone13");

        Product product2 = new Product();
        product2.setTitle("MackBook");

        products.add(product1);
        products.add(product2);

        when(productService.getAllProducts()).thenReturn(products);

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(products)))
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].title").value("Iphone13"));
    }

    @Test
    public void Test_CreateProduct_ReceiveSuccessfulResponse() throws Exception {
        Product productToCreate = new Product();
        productToCreate.setTitle("Defender");
        productToCreate.setPrice(30000000.0);

        Product expectedProduct = new Product();
        productToCreate.setId(19902L);
        productToCreate.setTitle("Defender");
        productToCreate.setPrice(30000000.0);

        when(productService.createProduct(any(Product.class))).thenReturn(expectedProduct);

        mockMvc.perform(post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(productToCreate)))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(expectedProduct)))

                // Todo: in case of post getting product in response with all fields as null ?
                .andExpect(jsonPath("$.length()").value(9))
                .andExpect(jsonPath("$.title").value("Defender"));
    }
}
