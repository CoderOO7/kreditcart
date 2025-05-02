package com.kredicart.order.Services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kredicart.order.Dtos.*;
import com.kredicart.order.Exceptions.*;
import com.kredicart.order.Factories.OrderFactory;
import com.kredicart.order.Factories.OrderItemFactory;
import com.kredicart.order.Models.*;
import com.kredicart.order.Repositories.CurrencyRepository;
import com.kredicart.order.Repositories.OrderItemRepository;
import com.kredicart.order.Repositories.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
    @Autowired
    private ProductServiceClient productClientService;
    @Autowired
    private InventoryServiceClient inventoryServiceClient;

    @Autowired
    private OrderItemFactory orderItemFactory;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    @Transactional
    public OrderResponseDto getOrderById(UUID orderId) throws ResourceNotFoundException {
        Order order = this.orderRepository.findById(orderId)
                .orElseThrow(()-> new ResourceNotFoundException(String.format("Order not found with given id %s", orderId)));
        return this.getOrderResponseDtoFromOrder(order);
    }

    @Override
    public OrderResponseDto placeOrder(PlaceOrderRequestDto placeOrderRequestDto) throws ResourceNotFoundException, OutOfStockException {

        UUID userId = placeOrderRequestDto.getCustomerId();
        FeignClientExceptionHandler.execute(()->this.userServiceClient.getUserById(userId),"User",userId);

        UUID shippingAddressId = placeOrderRequestDto.getShippingAddressId();

        FeignClientExceptionHandler.execute(()->this.userServiceClient.getUserAddressById(userId, shippingAddressId),"Shipping Address", shippingAddressId);

        UUID billingAddressId = placeOrderRequestDto.getBillingAddressId();
        FeignClientExceptionHandler.execute(()->this.userServiceClient.getUserAddressById(userId, billingAddressId),"Billing Address", billingAddressId);

        UUID currencyId = placeOrderRequestDto.getCurrencyId();
        Currency currency = this.currencyRepository.findById(currencyId)
                .orElseThrow(()-> new ResourceNotFoundException(String.format("Currency not found with given id %s", currencyId)));

        List<OrderItem> orderItemList = new ArrayList<>();

        // todo: handle case if from frontend receive multiple entries for same product, then groupByProductId and sum all the qty
        // or throw error for duplicate entries
        for(PlaceOrderRequestDto.Item item : placeOrderRequestDto.getItems()) {
            UUID productId =  item.getProductId();
            int productQty = item.getQuantity();

            ProductResponseDto product = FeignClientExceptionHandler.execute(()-> this.productClientService.getProductById(productId), "Product", productId);
            InventoryResponseDto inventory =  FeignClientExceptionHandler.execute(()-> this.inventoryServiceClient.getInventoryByProductId(productId),"Inventory", productId);

            if(inventory == null || inventory.getQuantity() < productQty) {
                throw new OutOfStockException(String.format("%s is out of stock", product.getTitle()));
            }

            OrderItem orderItem = orderItemFactory.create(productId, productQty, product.getPrice());
            orderItemList.add(orderItem);
        }

        Order order = OrderFactory.create(
                userId,
                shippingAddressId,
                billingAddressId,
                currency,
                orderItemList
        );

        Order savedOrder = this.orderRepository.save(order);
        // Publish orderPlaced event
        for (OrderItem item : savedOrder.getItems()) {
            OrderPlacedEventDto event = new OrderPlacedEventDto(item.getProductId(), item.getQuantity());
            try {
                String eventJson = objectMapper.writeValueAsString(event);
                kafkaTemplate.send("orderPlaced", eventJson);
            } catch (JsonProcessingException e) {
                System.out.printf("placeOrder: orderPlacedEventError: %s\n", e.getMessage());
            }
        }

        return this.getOrderResponseDtoFromOrder(savedOrder);
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
