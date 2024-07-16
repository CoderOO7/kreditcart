package com.kreditcart.productCatalogue.TableInheritanceExamples.MappedSuperClass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity(name="msc_mentor")
@Getter
@Setter
public class Mentor extends User {
    private int numberOfHours;
}
