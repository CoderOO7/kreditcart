package com.kredicart.order.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "order_items")
public class OrderItem extends BaseModel {
    @Column(nullable = false)
    private UUID productId;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private double unitPrice;

    @Column(nullable = false)
    private double actualAmount; // unitPrice * quantity

    @Column(nullable = false)
    private double discountRate;

    @Column(nullable = false)
    private double discountAmount;  // actualAmount * (discountRate / 100);

    @Column(nullable = false)
    private double taxRate;

    @Column(nullable = false)
    private double taxAmount; // (actualAmount - discountAmount) * (taxRate / 100)

    @Column(nullable = false)
    private double totalAmount; // (actualAmount - discountAmount + taxAmount);

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaxType taxType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderItemState state;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
}
