package com.kreditcart.productCatalogue.TableInheritanceExamples.SingleTables;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity(name="st_ta")
@Getter
@Setter
@DiscriminatorValue(value="1")
public class Ta extends User {
    private int ratings;
}
