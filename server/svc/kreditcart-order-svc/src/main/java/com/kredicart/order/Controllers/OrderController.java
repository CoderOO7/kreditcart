package com.kredicart.order.Controllers;

import com.kredicart.order.Dtos.PlaceOrderRequestDto;
import com.kredicart.order.Models.Order;
import com.kredicart.order.Services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/order-svc/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Order> placeOrder(@RequestBody PlaceOrderRequestDto order) throws Exception {
        Order orderRep = this.orderService.placeOrder(order);
        return new ResponseEntity(orderRep, HttpStatus.OK);
    }
}
