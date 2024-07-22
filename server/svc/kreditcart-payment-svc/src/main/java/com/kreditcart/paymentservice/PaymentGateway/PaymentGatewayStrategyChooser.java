package com.kreditcart.paymentservice.PaymentGateway;

import org.springframework.stereotype.Service;

@Service
public class PaymentGatewayStrategyChooser {
    private RazorpayPaymentGateway razorpayPaymentGateway;
    private StripePaymentGateway stripePaymentGateway;

    public PaymentGatewayStrategyChooser(RazorpayPaymentGateway razorpayPaymentGateway, StripePaymentGateway stripePaymentGateway) {
        this.razorpayPaymentGateway = razorpayPaymentGateway;
        this.stripePaymentGateway = stripePaymentGateway;
    }

    public PaymentGateway getBestPaymentGateway() {
        return Math.random() >= 0.5 ? this.stripePaymentGateway : this.razorpayPaymentGateway;
//        return this.stripePaymentGateway
    }

}
