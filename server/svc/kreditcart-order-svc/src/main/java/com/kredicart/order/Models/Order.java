package com.kredicart.order.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "orders")
public class Order extends BaseModel {
    @Column(nullable = false)
    private Long customerId;

    @Column(nullable = false)
    private String orderCode;

    @Column(nullable = false)
    private double itemsTotalAmount;

    @Column(nullable = false)
    private double itemsTotalTax;

    @Column(nullable = false)
    private double serviceCharge; // platform fee

    @Column(nullable = false)
    private double deliveryCharge;

    @Column(nullable = false)
    private double discountRate;

    @Column(nullable = false)
    private double discountAmount;

    @Column(nullable = false)
    private double grandTotal; // itemsTotalAmount + itemsTotalTax + serviceCharge + deliveryCharge - discountAmount

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderState state;

    @ManyToOne
    @JoinColumn(name = "currency_id")
    private Currency currency;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> items;
}
