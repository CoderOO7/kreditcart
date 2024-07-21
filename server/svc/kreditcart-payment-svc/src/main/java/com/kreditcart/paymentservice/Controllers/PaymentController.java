package com.kreditcart.paymentservice.Controllers;

import com.kreditcart.paymentservice.Dtos.InitiatePaymentDto;
import com.kreditcart.paymentservice.Services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// @RestController is the combination of @Controller and @ResponseBody.
// It handles web request and ensures that the response send to the client will always be in JSON or XML format.
@RestController
@RequestMapping("/kreditcart/payments")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping("/payment-link")
    public String initiatePaymentLink(@RequestBody InitiatePaymentDto initiatePaymentDto){
        return paymentService.initiatePaymentLink();
    }
}
