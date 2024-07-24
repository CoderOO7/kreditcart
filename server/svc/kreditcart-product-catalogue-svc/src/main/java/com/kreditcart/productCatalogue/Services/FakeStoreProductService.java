package com.kreditcart.productCatalogue.Services;

import com.kreditcart.productCatalogue.Clients.FakeStore.Client.FakeStoreAPIClient;
import com.kreditcart.productCatalogue.Clients.FakeStore.Dtos.FakeStoreProductDto;
import com.kreditcart.productCatalogue.Models.Category;
import com.kreditcart.productCatalogue.Models.Product;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements IProductService {
    private FakeStoreAPIClient fakeStoreAPIClient;
    private RedisTemplate<String, Object> redisTemplate;

    public FakeStoreProductService(FakeStoreAPIClient fakeStoreAPIClient, RedisTemplate<String, Object> redisTemplate) {
        this.fakeStoreAPIClient = fakeStoreAPIClient;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductDto[] fakeStoreProductDtos = this.fakeStoreAPIClient.getAllProducts();
        List<Product> products = new ArrayList<>();

        for(FakeStoreProductDto productDto: fakeStoreProductDtos) {
            products.add(this.getProductFromFakeStoreProductDto(productDto));
        }

        return products;
    }

    @Override
    public Product getProduct(Long productId) {
        // check if product is in cache
        // if true: ---cache hit
        //      read from cache
        // else: -- cache miss
        //      call fakestore and get result
        //      store in cache

        FakeStoreProductDto fakeStoreProductDto = null;
        fakeStoreProductDto = (FakeStoreProductDto)redisTemplate.opsForHash().get("PRODUCTS_",productId);
        if(fakeStoreProductDto != null) {
            System.out.println("cache hit");
            return this.getProductFromFakeStoreProductDto(fakeStoreProductDto);
        }

        System.out.println("cache miss");
        fakeStoreProductDto = this.fakeStoreAPIClient.getProduct(productId);
        redisTemplate.opsForHash().put("PRODUCTS_", productId, fakeStoreProductDto);
        return this.getProductFromFakeStoreProductDto(fakeStoreProductDto);
    }

    @Override
    public Product createProduct(Product product) {
        FakeStoreProductDto fakeStoreProductDto = this.getFakeStoreProductDtoFromProduct(product);
        return getProductFromFakeStoreProductDto(this.fakeStoreAPIClient.createProduct(fakeStoreProductDto));
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        FakeStoreProductDto fakeStoreProductDto = this.getFakeStoreProductDtoFromProduct(product);
        return getProductFromFakeStoreProductDto(this.fakeStoreAPIClient.updateProduct(id, fakeStoreProductDto));
    }

    @Override
    public Product getProductDetails(Long userId, Long productId) {
        return null;
    }

    private Product getProductFromFakeStoreProductDto(FakeStoreProductDto productDto) {
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

    private FakeStoreProductDto getFakeStoreProductDtoFromProduct(Product product) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setId(product.getId());
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setImage(product.getImageUrl());
        if(product.getCategory() != null) {
            fakeStoreProductDto.setCategory(product.getCategory().getName());
        }

        return fakeStoreProductDto;
    }
}
