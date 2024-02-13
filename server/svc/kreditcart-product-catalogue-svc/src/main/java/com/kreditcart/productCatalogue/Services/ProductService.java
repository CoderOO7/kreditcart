package com.kreditcart.productCatalogue.Services;

import com.kreditcart.productCatalogue.Dtos.ProductDto;
import com.kreditcart.productCatalogue.Models.Category;
import com.kreditcart.productCatalogue.Models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService implements IProductService {
    private RestTemplateBuilder restTemplateBuilder;
    private final String fakeStoreApiBaseUrl = "https://fakestoreapi.com";

    public ProductService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public List<Product> getAllProducts() {
        RestTemplate restTemplate = this.restTemplateBuilder.build();
        ProductDto[] productDtos = restTemplate.getForEntity(this.fakeStoreApiBaseUrl + "/products", ProductDto[].class).getBody();
        List<Product> products = new ArrayList<>();

        for(ProductDto productDto: productDtos) {
            products.add(this.getProductFromProductDto(productDto));
        }

        return products;
    }

    @Override
    public Product getProduct(Long productId) {
        RestTemplate restTemplate = this.restTemplateBuilder.build();
        ProductDto productDto = restTemplate.getForEntity(this.fakeStoreApiBaseUrl + "/products/{id}", ProductDto.class, productId).getBody();

        return this.getProductFromProductDto(productDto);
    }

    @Override
    public Product createProduct(ProductDto productDto) {
        RestTemplate restTemplate = this.restTemplateBuilder.build();
        ResponseEntity<ProductDto> responseEntity = restTemplate.postForEntity(this.fakeStoreApiBaseUrl + "/products", productDto, ProductDto.class);
        return getProductFromProductDto(responseEntity.getBody());
    }

    @Override
    public String updateProduct(ProductDto productDto, String id) {
        return null;
    }

    private Product getProductFromProductDto(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setImageUrl(productDto.getImage());

        Category category = new Category();
        category.setName(productDto.getCategory());
        product.setCategory(category);

        return product;
    }
}
