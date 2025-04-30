package com.kreditcart.paymentservice.Strategies.Stripe;

import com.kreditcart.paymentservice.Enums.StripeEventType;
import com.kreditcart.paymentservice.Exceptions.FeignClientExceptionHandler;
import com.kreditcart.paymentservice.Services.OrderServiceClient;
import com.stripe.model.Event;
import com.stripe.model.checkout.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CheckoutSessionCompletedStrategy implements StripeWebhookStrategy {
    @Autowired
    OrderServiceClient orderServiceClient;

    @Override
    public String handle(Event event) {
        Session session = (Session) event.getDataObjectDeserializer().getObject().orElse(null);
        if (session != null) {
            String orderId = session.getMetadata().get("order_id");
            if (orderId != null) {
                FeignClientExceptionHandler.execute(()-> orderServiceClient.updateOrderState(UUID.fromString(orderId), "CONFIRMED"), "Order", UUID.fromString(orderId));
                return "Checkout session completed";
            }
        }
        return "No action taken";
    }

    @Override
    public StripeEventType getType() {
        return StripeEventType.CHECKOUT_SESSION_COMPLETED;
    }
}
