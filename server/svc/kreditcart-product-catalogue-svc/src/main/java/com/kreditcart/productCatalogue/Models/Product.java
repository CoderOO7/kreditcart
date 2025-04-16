package com.kreditcart.productCatalogue.Models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Getter
@Setter
@ToString
@Entity
public class Product extends BaseModel {
    @Column(nullable = false)
    private String title;

    private String description;

    @Column(nullable = false)
    private Double price;

    private String imageUrl;

    @JsonBackReference()
    @ManyToOne(cascade = CascadeType.ALL)
    private Category category;

    private Boolean isSpecial = false;

    @Column(nullable = false)
    private int stock = 0;
}
