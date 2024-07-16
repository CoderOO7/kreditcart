package com.kreditcart.productCatalogue.TableInheritanceExamples.TablePerClass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity(name="tpc_instructor")
@Getter
@Setter
public class Instructor extends User {
    private String company;
}
