package com.kredicart.order.Services;

import com.kredicart.order.Dtos.ProductResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "kreditcart-product-svc")
public interface ProductServiceClient {
    @GetMapping("/product-svc/internal/api/v1/products/{id}")
    ProductResponseDto getProductById(@PathVariable("id") UUID id);
}
