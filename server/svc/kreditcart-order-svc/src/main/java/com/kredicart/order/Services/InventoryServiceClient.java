package com.kredicart.order.Services;

import com.kredicart.order.Dtos.InventoryResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "kreditcart-inventory-svc")
public interface InventoryServiceClient {
    @GetMapping("inventory-svc/internal/api/v1/inventories/{productId}")
    InventoryResponseDto getInventoryByProductId(@PathVariable UUID productId);
}

