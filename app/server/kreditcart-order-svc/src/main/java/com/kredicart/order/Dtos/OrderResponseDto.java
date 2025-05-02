package com.kredicart.order.Dtos;

import com.kredicart.order.Models.OrderState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDto {
    private UUID id;
    private String orderCode;
    private UUID userId;

    private UUID shippingAddressId;
    private UUID billingAddressId;

    private String currencyCode;

    private double itemsTotalAmount;
    private double itemsTotalTax;
    private double serviceCharge;
    private double deliveryCharge;
    private double discountRate;
    private double discountAmount;
    private double grandTotal;

    private OrderState state;

    private List<OrderItemResponseDto> items;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
