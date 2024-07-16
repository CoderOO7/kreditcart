package com.kreditcart.productCatalogue.TableInheritanceExamples.TablePerClass;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name="tpc_user")
@Getter
@Setter
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long name;
    private Long email;
}
