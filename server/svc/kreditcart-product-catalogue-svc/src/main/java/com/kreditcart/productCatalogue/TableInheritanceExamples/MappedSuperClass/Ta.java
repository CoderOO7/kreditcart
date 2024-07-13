package com.kreditcart.productCatalogue.TableInheritanceExamples.MappedSuperClass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity(name="msc_ta")
@Getter
@Setter
public class Ta extends User {
    private int ratings;
}
