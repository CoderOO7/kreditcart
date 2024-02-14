package com.kreditcart.productCatalogue.Services;

import com.kreditcart.productCatalogue.Clients.FakeStore.Dtos.FakeStoreProductDto;
import com.kreditcart.productCatalogue.Models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements IProductService {
    private RestTemplateBuilder restTemplateBuilder;
    private final String fakeStoreApiBaseUrl = "https://fakestoreapi.com";

    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public List<Product> getAllProducts() {
        RestTemplate restTemplate = this.restTemplateBuilder.build();
        FakeStoreProductDto[] fakeStoreProductDtos = restTemplate.getForEntity(this.fakeStoreApiBaseUrl + "/products", FakeStoreProductDto[].class).getBody();
        List<Product> products = new ArrayList<>();

        for(FakeStoreProductDto productDto: fakeStoreProductDtos) {
            products.add(this.getProductFromFakeStoreProductDto(productDto));
        }

        return products;
    }

    @Override
    public Product getProduct(Long productId) {
        RestTemplate restTemplate = this.restTemplateBuilder.build();
        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForEntity(this.fakeStoreApiBaseUrl + "/products/{id}", FakeStoreProductDto.class, productId).getBody();

        return this.getProductFromFakeStoreProductDto(fakeStoreProductDto);
    }

    @Override
    public Product createProduct(Product product) {
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity = this.requestForEntity( HttpMethod.POST, this.fakeStoreApiBaseUrl + "/products", product, FakeStoreProductDto.class);
        return getProductFromFakeStoreProductDto(fakeStoreProductDtoResponseEntity.getBody());
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity =  requestForEntity(HttpMethod.PATCH, this.fakeStoreApiBaseUrl + "/products/{id}", product, FakeStoreProductDto.class, id);
        return getProductFromFakeStoreProductDto(fakeStoreProductDtoResponseEntity.getBody());
    }


    private <T> ResponseEntity<T> requestForEntity(HttpMethod httpMethod, String url, @Nullable Object requestEntity, Class<T> responseType, Object... uriVariables) {
        RestTemplate restTemplate = this.restTemplateBuilder.requestFactory(HttpComponentsClientHttpRequestFactory.class).build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(requestEntity, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }

    private Product getProductFromFakeStoreProductDto(FakeStoreProductDto productDto) {
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
