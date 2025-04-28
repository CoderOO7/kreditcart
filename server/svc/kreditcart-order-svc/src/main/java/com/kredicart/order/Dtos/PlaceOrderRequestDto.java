package com.kredicart.order.Dtos;

import com.kredicart.order.Models.OrderItem;
import lombok.Data;
import org.springframework.data.util.Pair;

import java.util.List;
import java.util.UUID;

@Data
public class PlaceOrderRequestDto {
    private UUID customerId;
    private UUID currencyId;
    private UUID shippingAddressId;
    private UUID billingAddressId;
    private List<Item> items;
    @Data
    public static class Item {
        private UUID productId;
        private Integer quantity;
    }

}

