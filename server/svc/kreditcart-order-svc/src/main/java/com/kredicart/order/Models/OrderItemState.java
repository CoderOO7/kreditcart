package com.kredicart.order.Models;

public enum OrderItemState {
    PENDING,         // Item added to order but not processed
    RESERVED,        // Inventory reserved for this item
    UNAVAILABLE,     // Inventory could not be reserved
    SHIPPED,         // Item shipped
    DELIVERED,       // Item delivered
    CANCELLED        // Item cancelled
}

