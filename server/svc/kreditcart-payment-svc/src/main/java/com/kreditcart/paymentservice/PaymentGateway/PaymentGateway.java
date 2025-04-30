package com.kreditcart.paymentservice.PaymentGateway;


import java.util.UUID;

public interface PaymentGateway {
    String generatePaymentLink(UUID orderId, String email, String phoneNumber, double amount);
}
