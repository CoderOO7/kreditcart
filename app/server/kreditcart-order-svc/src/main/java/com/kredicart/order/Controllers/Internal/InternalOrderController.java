package com.kredicart.order.Controllers.Internal;

import com.kredicart.order.Dtos.OrderResponseDto;
import com.kredicart.order.Models.OrderState;
import com.kredicart.order.Services.Internal.InternalOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("internal/api/v1/orders")
public class InternalOrderController {
    @Autowired
    private InternalOrderService orderService;

    @GetMapping("{id}")
    public ResponseEntity<OrderResponseDto> getOrderById(@PathVariable("id") UUID orderId) throws Exception {
        OrderResponseDto orderRep = this.orderService.getOrderById(orderId);
        return new ResponseEntity(orderRep, HttpStatus.OK);
    }

    @PutMapping("{id}/state")
    public ResponseEntity<Void> updateOrderState(@PathVariable("id") UUID orderId, @RequestParam OrderState state) throws Exception {
        this.orderService.updateOrderState(orderId, state);
        return ResponseEntity.noContent().build();
    }
}
