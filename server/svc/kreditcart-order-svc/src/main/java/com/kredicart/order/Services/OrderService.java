package com.kredicart.order.Services;

import com.kredicart.order.Dtos.PlaceOrderRequestDto;
import com.kredicart.order.Exceptions.*;
import com.kredicart.order.Models.Order;
import org.springframework.data.util.Pair;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    Order placeOrder(PlaceOrderRequestDto placeOrderRequestDto) throws ResourceNotFoundException, OutOfStockException;
}
