package com.kredicart.order.Dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@ToString
public class ProductResponseDto {
    private UUID id;
    private String title;
    private String description;
    private Double price;
    private String image;
    private String category;
    private Boolean isSpecial = false;
    private String sku;
}