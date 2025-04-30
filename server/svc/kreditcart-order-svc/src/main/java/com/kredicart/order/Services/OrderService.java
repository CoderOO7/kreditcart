package com.kredicart.order.Services;

import com.kredicart.order.Dtos.OrderResponseDto;
import com.kredicart.order.Dtos.PlaceOrderRequestDto;
import com.kredicart.order.Exceptions.*;

import java.util.UUID;


public interface OrderService {
    OrderResponseDto getOrderById(UUID orderId) throws ResourceNotFoundException;
    OrderResponseDto placeOrder(PlaceOrderRequestDto placeOrderRequestDto) throws ResourceNotFoundException, OutOfStockException;
}
