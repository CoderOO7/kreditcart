package com.kreditcart.paymentservice.Services;

import com.kreditcart.paymentservice.Dtos.UserResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "kreditcart-user-svc")
public interface UserServiceClient {
    @GetMapping("/user-svc/internal/api/v1/users/{userId}")
    UserResponseDto getUserById(@PathVariable("userId") UUID userId);
}
