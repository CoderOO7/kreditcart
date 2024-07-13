package com.kreditcart.productCatalogue.TableInheritanceExamples.JoinedClass;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;

@Entity(name="jc_mentor")
@Getter
@Setter
@PrimaryKeyJoinColumn(name="user_id")
public class Mentor extends User {
    private int numberOfHours;
}
