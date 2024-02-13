package com.kreditcart.productCatalogue.Models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Category extends BaseModel {
    private String name;
    private String description;
}
