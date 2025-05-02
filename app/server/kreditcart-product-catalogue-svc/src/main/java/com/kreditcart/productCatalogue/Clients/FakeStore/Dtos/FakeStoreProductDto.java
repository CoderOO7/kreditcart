package com.kreditcart.productCatalogue.Clients.FakeStore.Dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@ToString
public class FakeStoreProductDto implements Serializable {
    private UUID id;
    private String title;
    private String description;
    private Double price;
    private String image;
    private String category;
    private FakeStoreRatingDto ratingDto;
}
