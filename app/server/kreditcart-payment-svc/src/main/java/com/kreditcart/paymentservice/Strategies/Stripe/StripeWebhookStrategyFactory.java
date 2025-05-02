package com.kreditcart.paymentservice.Strategies.Stripe;

import com.kreditcart.paymentservice.Enums.StripeEventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class StripeWebhookStrategyFactory {

    private Map<StripeEventType, StripeWebhookStrategy> strategies;

    @Autowired
    public StripeWebhookStrategyFactory(List<StripeWebhookStrategy> strategyList) {
        this.strategies = new HashMap<>();
        for (StripeWebhookStrategy strategy : strategyList) {
            this.strategies.put(strategy.getType(), strategy);
        }
    }

    public StripeWebhookStrategy getStrategy(String eventType) {
        try {
            return strategies.get(StripeEventType.fromValue(eventType));
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
