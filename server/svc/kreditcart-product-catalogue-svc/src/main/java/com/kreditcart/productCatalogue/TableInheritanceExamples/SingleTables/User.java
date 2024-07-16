package com.kreditcart.productCatalogue.TableInheritanceExamples.SingleTables;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name="st_user")
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="user_type", discriminatorType = DiscriminatorType.INTEGER)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long name;
    private Long email;
}
