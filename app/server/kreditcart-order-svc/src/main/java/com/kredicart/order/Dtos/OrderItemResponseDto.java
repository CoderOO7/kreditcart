package com.kredicart.order.Dtos;

import com.kredicart.order.Models.OrderItemState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemResponseDto {
    private UUID id;
    private UUID productId;
    private int quantity;
    private OrderItemState state;
    private double unitPrice;
    private double actualAmount;
    private double discountRate;
    private double discountAmount;
    private double taxRate;
    private double taxAmount;
    private double totalAmount;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
