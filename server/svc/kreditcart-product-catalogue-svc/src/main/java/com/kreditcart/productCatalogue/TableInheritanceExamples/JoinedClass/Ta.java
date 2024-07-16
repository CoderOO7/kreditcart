package com.kreditcart.productCatalogue.TableInheritanceExamples.JoinedClass;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;

@Entity(name="jc_ta")
@Getter
@Setter
@PrimaryKeyJoinColumn(name="user_id")
public class Ta extends User {
    private int ratings;
}
