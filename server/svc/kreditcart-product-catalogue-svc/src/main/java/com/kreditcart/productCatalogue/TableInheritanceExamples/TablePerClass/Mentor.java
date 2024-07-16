package com.kreditcart.productCatalogue.TableInheritanceExamples.TablePerClass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity(name="tpc_mentor")
@Getter
@Setter
public class Mentor extends User {
    private int numberOfHours;
}
