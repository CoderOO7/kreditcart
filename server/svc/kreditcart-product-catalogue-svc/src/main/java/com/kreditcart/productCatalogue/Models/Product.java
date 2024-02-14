package com.kreditcart.productCatalogue.Models;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Product extends BaseModel {
    private String title;
    private String description;
    private Double price;
    private String imageUrl;
//    private Category category;
}
