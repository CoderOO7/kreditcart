package com.kreditcart.userservice.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

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
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Embedded
    private GeoLocation location;
}