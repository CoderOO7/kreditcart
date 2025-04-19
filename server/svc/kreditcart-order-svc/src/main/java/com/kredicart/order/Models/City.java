package com.kredicart.order.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "city", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name", "state_id"})
})
public class City extends BaseModel {
    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "state_id")
    private State state;

    @PrePersist
    @PreUpdate
    public void capitalizeFields() {
        if (this.name != null) {
            this.name = this.name.toUpperCase();
        }
    }
}