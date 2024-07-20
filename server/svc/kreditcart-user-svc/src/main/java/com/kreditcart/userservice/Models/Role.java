package com.kreditcart.userservice.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Role extends BaseModel {
    @Column(unique = true)
    private String value;
}
