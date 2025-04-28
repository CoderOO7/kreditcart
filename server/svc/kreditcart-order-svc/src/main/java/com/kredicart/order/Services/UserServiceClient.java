package com.kredicart.order.Services;

import com.kredicart.order.Dtos.AddressResponseDto;
import com.kredicart.order.Dtos.UserResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "kreditcart-user-svc")
public interface UserServiceClient {
    @GetMapping("/api/v1/user-svc/internal/users/{userId}")
    UserResponseDto getUserById(@PathVariable("userId") UUID userId);
    @GetMapping("/api/v1/user-svc/internal/users/{userId}/addresses/{addressId}")
    AddressResponseDto getUserAddressById(@PathVariable("userId") UUID userId, @PathVariable("addressId") UUID addressId);
}
