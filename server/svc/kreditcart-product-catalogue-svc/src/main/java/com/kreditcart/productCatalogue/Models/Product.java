package com.kreditcart.productCatalogue.Models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class Product extends BaseModel {
    private String title;
    private String description;
    private Double price;
    private String imageUrl;
    @JsonBackReference()
    @ManyToOne(cascade = CascadeType.ALL)
    private Category category;
}
