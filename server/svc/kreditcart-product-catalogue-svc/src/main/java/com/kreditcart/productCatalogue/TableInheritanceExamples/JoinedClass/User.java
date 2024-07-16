package com.kreditcart.productCatalogue.TableInheritanceExamples.JoinedClass;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name="jc_user")
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long name;
    private Long email;
}
