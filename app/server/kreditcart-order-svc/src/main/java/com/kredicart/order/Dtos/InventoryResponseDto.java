package com.kredicart.order.Dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@ToString
public class InventoryResponseDto {
    private UUID productId;
    private Integer quantity;
}
