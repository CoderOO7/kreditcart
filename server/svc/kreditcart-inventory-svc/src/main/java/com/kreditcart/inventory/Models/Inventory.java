package com.kreditcart.inventory.Models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@Table(name = "inventories")
public class Inventory extends BaseModel {
    @Column(nullable = false, unique = true)
    private UUID productId;

    @Column(nullable = false, columnDefinition = "INTEGER DEFAULT 0")
    private Integer quantity = 0;
}

