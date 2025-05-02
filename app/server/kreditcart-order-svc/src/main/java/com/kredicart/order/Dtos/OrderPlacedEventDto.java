package com.kredicart.order.Dtos;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderPlacedEventDto {
    private UUID productId;
    private int quantity;
}