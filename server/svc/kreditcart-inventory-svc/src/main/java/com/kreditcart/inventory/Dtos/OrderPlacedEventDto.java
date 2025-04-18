package com.kreditcart.inventory.Dtos;

import lombok.*;

@Getter
@Setter
@ToString
public class OrderPlacedEventDto {
    private Long productId;
    private int quantity;
}