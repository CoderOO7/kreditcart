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
        //Todo: return random payment gateway
        return this.stripePaymentGateway;
    }

}
