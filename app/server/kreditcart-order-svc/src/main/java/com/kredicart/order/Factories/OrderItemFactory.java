package com.kredicart.order.Factories;

import com.kredicart.order.Models.OrderItem;
import com.kredicart.order.Models.OrderItemState;
import com.kredicart.order.Models.TaxType;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class OrderItemFactory {
    private static final double DEFAULT_TAX_RATE = 5.0;       // 5%
    private static final double DEFAULT_DISCOUNT_RATE = 0.0;   // 0%
    public OrderItem create(UUID productId, int quantity, double unitPrice) {
        double actualAmount = unitPrice * quantity;
        double discountAmount = actualAmount * (DEFAULT_DISCOUNT_RATE / 100);
        double taxAmount = (actualAmount - discountAmount) * (DEFAULT_TAX_RATE / 100);
        double totalAmount = actualAmount - discountAmount + taxAmount;

        OrderItem item = new OrderItem();
        item.setProductId(productId);
        item.setQuantity(quantity);
        item.setUnitPrice(unitPrice);
        item.setDiscountRate(DEFAULT_DISCOUNT_RATE);
        item.setTaxRate(DEFAULT_TAX_RATE);
        item.setState(OrderItemState.PENDING);
        item.setActualAmount(actualAmount);
        item.setDiscountAmount(discountAmount);
        item.setTaxAmount(taxAmount);
        item.setTotalAmount(totalAmount);
        // todo: create separate strategy for taxes, currently only supporting india so no need
        item.setTaxType(TaxType.GST);

        return item;
    }
}
