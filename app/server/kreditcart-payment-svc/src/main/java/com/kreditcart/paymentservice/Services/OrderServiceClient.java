package com.kreditcart.paymentservice.Services;

import com.kreditcart.paymentservice.Dtos.OrderResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@FeignClient(name = "kreditcart-order-svc")
public interface OrderServiceClient {
    @GetMapping("/order-svc/internal/api/v1/orders/{orderId}")
    OrderResponseDto gerOrderById(@PathVariable("orderId") UUID userId);

    @PutMapping("/order-svc/internal/api/v1/orders/{orderId}/state")
    Void updateOrderState(@PathVariable("orderId") UUID userId, @RequestParam String state);
}
