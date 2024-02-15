package com.kreditcart.productCatalogue.Clients.FakeStore.Client;

import com.kreditcart.productCatalogue.Clients.FakeStore.Dtos.FakeStoreProductDto;
import com.kreditcart.productCatalogue.Models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class FakeStoreAPIClient {
    RestTemplateBuilder restTemplateBuilder;
    private final String fakeStoreApiBaseUrl = "https://fakestoreapi.com";

    public FakeStoreAPIClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public FakeStoreProductDto[] getAllProducts() {
        RestTemplate restTemplate = this.restTemplateBuilder.build();
        FakeStoreProductDto[] fakeStoreProductDtos = restTemplate.getForEntity(this.fakeStoreApiBaseUrl + "/products", FakeStoreProductDto[].class).getBody();
        return fakeStoreProductDtos;
    }

    public FakeStoreProductDto getProduct(Long productId) {
        RestTemplate restTemplate = this.restTemplateBuilder.build();
        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForEntity(this.fakeStoreApiBaseUrl + "/products/{id}", FakeStoreProductDto.class, productId).getBody();
        return fakeStoreProductDto;
    }

    public FakeStoreProductDto createProduct(FakeStoreProductDto product) {
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity = this.requestForEntity( HttpMethod.POST, this.fakeStoreApiBaseUrl + "/products", product, FakeStoreProductDto.class);
        return fakeStoreProductDtoResponseEntity.getBody();
    }

    public FakeStoreProductDto updateProduct(Long id, FakeStoreProductDto product) {
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity =  requestForEntity(HttpMethod.PATCH, this.fakeStoreApiBaseUrl + "/products/{id}", product, FakeStoreProductDto.class, id);
        return fakeStoreProductDtoResponseEntity.getBody();
    }

    private <T> ResponseEntity<T> requestForEntity(HttpMethod httpMethod, String url, @Nullable Object requestEntity, Class<T> responseType, Object... uriVariables) {
        RestTemplate restTemplate = this.restTemplateBuilder.requestFactory(HttpComponentsClientHttpRequestFactory.class).build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(requestEntity, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }
}
