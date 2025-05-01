package com.kreditcart.productCatalogue.Models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "products")
public class Product extends BaseModel {
    @Column(nullable = false)
    private String title;

    private String description;

    @Column(nullable = false, unique = true)
    private String sku;

    @Column(nullable = false)
    private Double price;

    private String imageUrl;

    @JsonBackReference()
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    private Boolean isSpecial = false;
}
