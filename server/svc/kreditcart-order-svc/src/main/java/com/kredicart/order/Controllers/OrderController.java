package com.kredicart.order.Controllers;

import com.kredicart.order.Dtos.OrderResponseDto;
import com.kredicart.order.Dtos.PlaceOrderRequestDto;
import com.kredicart.order.Services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderResponseDto> placeOrder(@RequestBody PlaceOrderRequestDto order) throws Exception {
        OrderResponseDto orderRep = this.orderService.placeOrder(order);
        return new ResponseEntity(orderRep, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<OrderResponseDto> getOrderById(@PathVariable("id") UUID orderId) throws Exception {
        OrderResponseDto orderRep = this.orderService.getOrderById(orderId);
        return new ResponseEntity(orderRep, HttpStatus.OK);
    }
}
