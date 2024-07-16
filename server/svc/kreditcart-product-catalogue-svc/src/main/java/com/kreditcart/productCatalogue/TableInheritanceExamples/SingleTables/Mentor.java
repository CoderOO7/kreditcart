package com.kreditcart.productCatalogue.TableInheritanceExamples.SingleTables;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity(name="st_mentor")
@Getter
@Setter
@DiscriminatorValue(value="2")
public class Mentor extends User {
    private int numberOfHours;
}
