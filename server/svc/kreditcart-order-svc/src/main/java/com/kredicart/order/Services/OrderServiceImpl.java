package com.kredicart.order.Services;

import com.kredicart.order.Dtos.PlaceOrderRequestDto;
import com.kredicart.order.Exceptions.*;
import com.kredicart.order.Models.*;
import com.kredicart.order.Repositories.CurrencyRepository;
import com.kredicart.order.Repositories.OrderItemRepository;
import com.kredicart.order.Repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private UserServiceClient userServiceClient;
    @Autowired
    private CurrencyRepository currencyRepository;

    @Override
    public Order placeOrder(PlaceOrderRequestDto placeOrderRequestDto) throws ResourceNotFoundException, OutOfStockException {

        UUID userId = placeOrderRequestDto.getCustomerId();
        FeignClientExceptionHandler.execute(()->this.userServiceClient.getUserById(userId),"User",userId);

        UUID shippingAddressId = placeOrderRequestDto.getShippingAddressId();
        FeignClientExceptionHandler.execute(()->this.userServiceClient.getUserAddressById(userId, shippingAddressId),"Shipping Address", shippingAddressId);

        UUID billingAddressId = placeOrderRequestDto.getBillingAddressId();
        FeignClientExceptionHandler.execute(()->this.userServiceClient.getUserAddressById(userId, billingAddressId),"Billing Address", billingAddressId);

        UUID currencyId = placeOrderRequestDto.getCurrencyId();
        Currency currency = this.currencyRepository.findById(currencyId)
                .orElseThrow(()-> new ResourceNotFoundException(String.format("Currency not found with given id %s", currencyId)));

        return null;
    }
}
