package com.kreditcart.productCatalogue.Models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "product_categories")
public class Category extends BaseModel {
    @Column(nullable = false)
    private String name;

    private String description;
    @JsonManagedReference()
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    private List<Product> products;
}
