package com.kreditcart.inventory.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCreatedEventDto {
    private UUID productId;
    private Integer initialStock;
}
