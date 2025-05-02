package com.kredicart.order.Services.Internal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kredicart.order.Dtos.*;
import com.kredicart.order.Exceptions.FeignClientExceptionHandler;
import com.kredicart.order.Exceptions.OutOfStockException;
import com.kredicart.order.Exceptions.ResourceNotFoundException;
import com.kredicart.order.Factories.OrderFactory;
import com.kredicart.order.Factories.OrderItemFactory;
import com.kredicart.order.Models.Currency;
import com.kredicart.order.Models.Order;
import com.kredicart.order.Models.OrderItem;
import com.kredicart.order.Models.OrderState;
import com.kredicart.order.Repositories.CurrencyRepository;
import com.kredicart.order.Repositories.OrderItemRepository;
import com.kredicart.order.Repositories.OrderRepository;
import com.kredicart.order.Services.InventoryServiceClient;
import com.kredicart.order.Services.OrderService;
import com.kredicart.order.Services.ProductServiceClient;
import com.kredicart.order.Services.UserServiceClient;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class InternalOrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    public OrderResponseDto getOrderById(UUID orderId) throws ResourceNotFoundException {
        Order order = this.orderRepository.findById(orderId)
                .orElseThrow(()-> new ResourceNotFoundException(String.format("Order not found with given id %s", orderId)));
        return this.getOrderResponseDtoFromOrder(order);
    }

    public void updateOrderState(UUID orderId, OrderState orderState) throws ResourceNotFoundException {
        Order order = this.orderRepository.findById(orderId)
                .orElseThrow(()-> new ResourceNotFoundException(String.format("Order not found with given id %s", orderId)));
        order.setState(orderState);
        this.orderRepository.save(order);
    }

    private OrderResponseDto getOrderResponseDtoFromOrder(Order order) {
        List<OrderItemResponseDto> itemDtos = order.getItems().stream().map(item -> {
            OrderItemResponseDto dto = new OrderItemResponseDto();
            dto.setId(item.getId());
            dto.setState(item.getState());
            dto.setProductId(item.getProductId());
            dto.setQuantity(item.getQuantity());
            dto.setUnitPrice(item.getUnitPrice());
            dto.setActualAmount(item.getActualAmount());
            dto.setDiscountRate(item.getDiscountRate());
            dto.setDiscountAmount(item.getDiscountAmount());
            dto.setTaxRate(item.getTaxRate());
            dto.setTaxAmount(item.getTaxAmount());
            dto.setTotalAmount(item.getTotalAmount());
            dto.setCreatedAt(item.getCreatedAt());
            dto.setUpdatedAt(item.getUpdatedAt());
            return dto;
        }).collect(Collectors.toList());

        return new OrderResponseDto(
                order.getId(),
                order.getOrderCode(),
                order.getUserId(),
                order.getShippingAddressId(),
                order.getBillingAddressId(),
                order.getCurrency().getCode(),
                order.getItemsTotalAmount(),
                order.getItemsTotalTax(),
                order.getServiceCharge(),
                order.getDeliveryCharge(),
                order.getDiscountRate(),
                order.getDiscountAmount(),
                order.getGrandTotal(),
                order.getState(),
                itemDtos,
                order.getCreatedAt(),
                order.getUpdatedAt()
        );
    }
}
