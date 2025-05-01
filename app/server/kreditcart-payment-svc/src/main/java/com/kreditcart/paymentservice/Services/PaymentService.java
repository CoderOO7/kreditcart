package com.kreditcart.paymentservice.Services;

import com.kreditcart.paymentservice.Dtos.InitiatePaymentDto;
import com.kreditcart.paymentservice.Dtos.OrderResponseDto;
import com.kreditcart.paymentservice.Dtos.UserResponseDto;
import com.kreditcart.paymentservice.Exceptions.FeignClientExceptionHandler;
import com.kreditcart.paymentservice.PaymentGateway.PaymentGateway;
import com.kreditcart.paymentservice.PaymentGateway.PaymentGatewayStrategyChooser;
import com.kreditcart.paymentservice.PaymentGateway.StripePaymentGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PaymentService {
    @Autowired
    private UserServiceClient userServiceClient;
    @Autowired
    private OrderServiceClient orderServiceClient;
    @Autowired
    private PaymentGatewayStrategyChooser paymentGatewayStrategyChooser;
    @Autowired
    private StripePaymentGateway stripePaymentGateway;


    public String initiatePaymentLink(InitiatePaymentDto initiatePaymentDto) {
        // Todo: for now using stripePaymentGateway, will see other one later
//        PaymentGateway paymentGateway = paymentGatewayStrategyChooser.getBestPaymentGateway();
        PaymentGateway paymentGateway = this.stripePaymentGateway;

        UUID orderId = initiatePaymentDto.getOrderId();
        OrderResponseDto order = FeignClientExceptionHandler.execute(()-> orderServiceClient.gerOrderById(orderId),"Order",orderId);
        if(!order.getState().equals("PENDING")) {
            throw new IllegalStateException("Order must be in CONFIRMED state to initiate payment");
        }

        UUID userId = order.getUserId();
        UserResponseDto user = FeignClientExceptionHandler.execute(()-> userServiceClient.getUserById(userId),"User",userId);
        double amount = order.getGrandTotal();
        String email = user.getEmail();

        return paymentGateway.generatePaymentLink(orderId, email, null, amount);
    }
}
