package com.kreditcart.productCatalogue.Services;

import com.kreditcart.productCatalogue.Clients.FakeStore.Client.FakeStoreAPIClient;
import com.kreditcart.productCatalogue.Clients.FakeStore.Dtos.FakeStoreProductDto;
import com.kreditcart.productCatalogue.Models.Category;
import com.kreditcart.productCatalogue.Models.Product;

import java.util.ArrayList;
import java.util.List;

//@Service
public class FakeStoreProductService implements IProductService {
    private FakeStoreAPIClient fakeStoreAPIClient;

    public FakeStoreProductService(FakeStoreAPIClient fakeStoreAPIClient) {
        this.fakeStoreAPIClient = fakeStoreAPIClient;
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
        return this.getProductFromFakeStoreProductDto(this.fakeStoreAPIClient.getProduct(productId));
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
