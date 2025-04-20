package com.kreditcart.productCatalogue.Dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@ToString
public class ProductDto {
    private UUID id;
    private String title;
    private String description;
    private Double price;
    private String image;
    private String category;
    private RatingDto ratingDto;
    private Boolean isSpecial = false;
    private String sku;
    private int stock = 0;
}
