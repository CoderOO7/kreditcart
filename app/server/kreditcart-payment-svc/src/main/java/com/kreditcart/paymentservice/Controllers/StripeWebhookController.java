package com.kreditcart.paymentservice.Controllers;

import com.kreditcart.paymentservice.Strategies.Stripe.StripeWebhookStrategy;
import com.kreditcart.paymentservice.Strategies.Stripe.StripeWebhookStrategyFactory;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.Event;
import com.stripe.net.Webhook;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;


import java.io.BufferedReader;
import java.io.IOException;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/webhooks/stripe")
public class StripeWebhookController {

    private static final Logger logger = LoggerFactory.getLogger(StripeWebhookController.class);

    @Value("${stripe.webhook.endpoint.secret}")
    private String endpointSecret;

    @Autowired
    private StripeWebhookStrategyFactory strategyFactory ;

    @PostMapping
    public ResponseEntity<String> receiveWebhookEvents(HttpServletRequest request) {
        logger.info("Received webhook event request");

        String payload;
        String sigHeader = request.getHeader("Stripe-Signature");

        logger.debug("Stripe Signature Header: {}", sigHeader);

        // Read request payload
        try (BufferedReader reader = request.getReader()) {
            payload = reader.lines().collect(Collectors.joining(System.lineSeparator()));
            logger.debug("Received payload: {}", payload); // Logs the raw payload
        } catch (IOException e) {
            logger.error("Error reading the request payload: {}", e.getMessage());
            return ResponseEntity.badRequest().body("Unable to read request payload");
        }

        Event event;
        try {
            logger.debug("Constructing Stripe event from payload...");
            event = Webhook.constructEvent(payload, sigHeader, endpointSecret);
            logger.info("Successfully constructed event of type: {}", event.getType());
        } catch (SignatureVerificationException e) {
            logger.error("Invalid signature verification: {}", e.getMessage());
            return ResponseEntity.badRequest().body("Invalid signature");
        } catch (Exception e) {
            logger.error("Error constructing event from payload: {}", e.getMessage());
            return ResponseEntity.badRequest().body("Invalid payload");
        }

        StripeWebhookStrategy strategy = strategyFactory.getStrategy(event.getType());
        if (strategy != null) {
            logger.info("Handling event with strategy: {}", event.getType());
            String response = strategy.handle(event);
            logger.info("Event handled successfully: {}", response);
            return ResponseEntity.ok(response);
        }

        logger.warn("Unhandled event type: {}", event.getType());
        return ResponseEntity.ok("Unhandled event type");
    }
}
