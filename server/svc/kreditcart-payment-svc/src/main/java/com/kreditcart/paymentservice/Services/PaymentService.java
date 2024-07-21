package com.kreditcart.paymentservice.Services;

import com.kreditcart.paymentservice.PaymentGateway.PaymentGateway;
import com.kreditcart.paymentservice.PaymentGateway.PaymentGatewayStrategyChooser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    @Autowired
    private PaymentGatewayStrategyChooser paymentGatewayStrategyChooser;

    public String initiatePaymentLink(String orderId, String email, String phoneNumber, Long amount) {
        PaymentGateway paymentGateway = paymentGatewayStrategyChooser.getBestPaymentGateway();
        return paymentGateway.generatePaymentLink(orderId, email, phoneNumber, amount);
    }
}
