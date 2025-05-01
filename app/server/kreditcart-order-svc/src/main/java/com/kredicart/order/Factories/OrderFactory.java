package com.kredicart.order.Factories;

import com.kredicart.order.Models.Currency;
import com.kredicart.order.Models.Order;
import com.kredicart.order.Models.OrderItem;
import com.kredicart.order.Models.OrderState;
import com.kredicart.order.Utils.OrderCodeGenerator;

import java.util.List;
import java.util.UUID;

public class OrderFactory {
    private static final double DEFAULT_SERVICE_CHARGE = 20.0;
    private static final double DEFAULT_DELIVERY_CHARGE = 40.0;
    private static final double DEFAULT_DISCOUNT_RATE = 0.0;

    public static Order create(UUID userId,
                               UUID shippingAddressId,
                               UUID billingAddressId,
                               Currency currency,
                               List<OrderItem> items) {

        double itemsTotalAmount = items.stream().mapToDouble(OrderItem::getActualAmount).sum();
        double itemsTotalTax = items.stream().mapToDouble(OrderItem::getTaxAmount).sum();
        // Todo: create strategy to apply discount like totalAmount is greater than 1k, give 5% disc
        double discountAmount = (itemsTotalAmount * (DEFAULT_DISCOUNT_RATE / 100));

        double grandTotal = itemsTotalAmount
                + itemsTotalTax
                + DEFAULT_SERVICE_CHARGE
                + DEFAULT_DELIVERY_CHARGE
                - discountAmount;

        Order order = new Order();
        order.setUserId(userId);
        order.setOrderCode(OrderCodeGenerator.generate());
        order.setItemsTotalAmount(itemsTotalAmount);
        order.setItemsTotalTax(itemsTotalTax);
        order.setServiceCharge(DEFAULT_SERVICE_CHARGE);
        order.setDeliveryCharge(DEFAULT_DELIVERY_CHARGE);
        order.setDiscountRate(DEFAULT_DISCOUNT_RATE);
        order.setDiscountAmount(discountAmount);
        order.setGrandTotal(grandTotal);
        order.setShippingAddressId(shippingAddressId);
        order.setBillingAddressId(billingAddressId);
        order.setCurrency(currency);
        order.setState(OrderState.PENDING);

        // Set order in each item (bidirectional mapping)
        for (OrderItem item : items) {
            item.setOrder(order);
        }

        order.setItems(items);

        return order;
    }
}
