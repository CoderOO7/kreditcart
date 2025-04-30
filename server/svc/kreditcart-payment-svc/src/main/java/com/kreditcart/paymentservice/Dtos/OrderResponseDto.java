package com.kreditcart.paymentservice.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDto {
    private UUID id;
    private String orderCode;
    private UUID userId;
    private String currencyCode;
    private double grandTotal;
    private String state;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}