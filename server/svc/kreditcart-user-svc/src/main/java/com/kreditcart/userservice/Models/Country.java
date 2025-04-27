package com.kreditcart.userservice.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "countries")
public class Country extends BaseModel {
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String isoCode;

    @PrePersist
    @PreUpdate
    public void capitalizeFields() {
        if (this.name != null) {
            this.name = this.name.toUpperCase();
        }

        if(this.isoCode != null) {
            this.isoCode = this.isoCode.toUpperCase();
        }
    }
}
