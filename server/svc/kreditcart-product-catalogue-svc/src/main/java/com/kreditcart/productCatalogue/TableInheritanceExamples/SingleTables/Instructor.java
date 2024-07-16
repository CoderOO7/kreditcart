package com.kreditcart.productCatalogue.TableInheritanceExamples.SingleTables;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity(name="st_instructor")
@Getter
@Setter
@DiscriminatorValue(value="3")
public class Instructor extends User {
    private String company;
}
