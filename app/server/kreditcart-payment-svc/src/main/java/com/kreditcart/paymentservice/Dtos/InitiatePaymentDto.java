package com.kreditcart.paymentservice.Dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class InitiatePaymentDto {
    private UUID orderId;
}
