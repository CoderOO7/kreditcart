package com.kredicart.order.Services;

import com.kredicart.order.Dtos.OrderResponseDto;
import com.kredicart.order.Dtos.PlaceOrderRequestDto;
import com.kredicart.order.Exceptions.*;


public interface OrderService {
    OrderResponseDto placeOrder(PlaceOrderRequestDto placeOrderRequestDto) throws ResourceNotFoundException, OutOfStockException;
}
