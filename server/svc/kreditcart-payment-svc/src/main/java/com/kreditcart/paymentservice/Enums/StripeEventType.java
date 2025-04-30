package com.kreditcart.paymentservice.Enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StripeEventType {
    PAYMENT_INTENT_SUCCEEDED("payment_intent.succeeded"),
    CHECKOUT_SESSION_COMPLETED("checkout.session.completed");

    private final String value;

    public static StripeEventType fromValue(String value) {
        for (StripeEventType type : values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown Stripe event type: " + value);
    }
}