package com.kredicart.order.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "addresses")
public class Address extends BaseModel {
    @Column(nullable = false)
    private String line1;

    private String line2;

    private String landMark;

    private String zipCode;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @Embedded
    private GeoLocation location;
}