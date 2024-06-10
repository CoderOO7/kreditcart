package com.kreditcart.productCatalogue.Models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Category extends BaseModel {
    private String name;
    private String description;
    @JsonManagedReference()
    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
