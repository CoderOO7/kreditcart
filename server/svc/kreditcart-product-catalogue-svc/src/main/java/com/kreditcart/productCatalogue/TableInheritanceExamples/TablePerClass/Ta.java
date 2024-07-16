package com.kreditcart.productCatalogue.TableInheritanceExamples.TablePerClass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity(name="tpc_ta")
@Getter
@Setter
public class Ta extends  User{
    private int ratings;
}
