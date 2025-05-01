package com.kreditcart.inventory.Dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@ToString
public class InventoryDto {
    private UUID productId;
    private Integer quantity;
}
