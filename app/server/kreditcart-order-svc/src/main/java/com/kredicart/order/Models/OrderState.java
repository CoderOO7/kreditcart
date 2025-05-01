package com.kredicart.order.Models;

public enum OrderState {
    PENDING,        // Order created, pending further processing
    CONFIRMED,      // Order validated successfully
    INVENTORY_RESERVED, // Inventory reserved successfully
    SHIPPED,         // Order shipped
    DELIVERED,       // Order delivered
    CANCELLED        // Order cancelled
}
