package com.kreditcart.paymentservice.Strategies.Stripe;

import com.kreditcart.paymentservice.Enums.StripeEventType;
import com.stripe.model.Event;

public interface StripeWebhookStrategy {
    String handle(Event event);
    StripeEventType getType();

}
