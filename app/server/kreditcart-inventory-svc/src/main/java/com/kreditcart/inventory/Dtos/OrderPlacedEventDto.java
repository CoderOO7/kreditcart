package com.kreditcart.inventory.Dtos;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
public class OrderPlacedEventDto {
    private UUID productId;
    private int quantity;
}