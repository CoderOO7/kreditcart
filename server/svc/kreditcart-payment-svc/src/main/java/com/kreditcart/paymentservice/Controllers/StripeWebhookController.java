package com.kreditcart.paymentservice.Controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("kreditcart-payment-svc/webhooks/stripe")
public class StripeWebhookController {
    @PostMapping("")
    public void receiveWebhookEvents() {
        System.out.println("receiveWebhookEvents start-----");
    }

}
