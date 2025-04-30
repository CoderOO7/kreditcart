package com.kreditcart.paymentservice.Strategies.Stripe;

import com.kreditcart.paymentservice.Enums.StripeEventType;
import com.stripe.model.Event;
import com.stripe.model.PaymentIntent;
import org.springframework.stereotype.Component;

@Component
public class PaymentIntentSucceededStrategy implements StripeWebhookStrategy {

    @Override
    public String handle(Event event) {
        PaymentIntent intent = (PaymentIntent) event.getDataObjectDeserializer().getObject().orElse(null);
        if (intent != null) {
            // handle your logic here
        }
        return "No action taken";
    }

    @Override
    public StripeEventType getType() {
        return StripeEventType.PAYMENT_INTENT_SUCCEEDED;
    }
}
