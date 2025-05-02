package com.kredicart.order.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "currencies")
public class Currency extends BaseModel {
    @Column(length = 50, nullable = false)
    private String name; // eg: 'Indian Rupee, US Dollar'

    @Column(length = 10, nullable = false, unique = true)
    private String code; // e.g. INR, USD

    @Column(length = 10, nullable = false)
    private String symbol; // ₹, $, etc.

    @PrePersist
    @PreUpdate
    public void capitalizeFields() {
        if (this.name != null) {
            this.name = this.name.toUpperCase();
        }

        if(this.code != null) {
            this.code = this.code.toUpperCase();
        }
    }
}
