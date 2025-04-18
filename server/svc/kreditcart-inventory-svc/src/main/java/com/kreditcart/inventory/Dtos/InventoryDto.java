package com.kreditcart.inventory.Dtos;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class InventoryDto {
    private Long productId;
    private Integer quantity;
}
