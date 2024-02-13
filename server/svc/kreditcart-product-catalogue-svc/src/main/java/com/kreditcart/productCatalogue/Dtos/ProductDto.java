package com.kreditcart.productCatalogue.Dtos;

import com.kreditcart.productCatalogue.Models.Category;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductDto {
    private Long id;
    private String title;
    private String description;
    private Double price;
    private String image;
    private String category;
    private RatingDto ratingDto;
}
