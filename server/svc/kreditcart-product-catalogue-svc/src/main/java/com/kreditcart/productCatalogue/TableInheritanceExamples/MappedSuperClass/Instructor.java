package com.kreditcart.productCatalogue.TableInheritanceExamples.MappedSuperClass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity(name="msc_instructor")
@Getter
@Setter
public class Instructor extends User {
    private String company;
}
