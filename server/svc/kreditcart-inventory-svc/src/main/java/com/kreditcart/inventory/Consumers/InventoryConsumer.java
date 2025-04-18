package com.kreditcart.inventory.Consumers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kreditcart.inventory.Dtos.OrderPlacedEventDto;
import com.kreditcart.inventory.Services.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InventoryConsumer {

    private final InventoryService inventoryService;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "orderPlaced", groupId = "inventoryService")
    public void handleOrderPlaced(String message) {
        try {
            OrderPlacedEventDto event = objectMapper.readValue(message, OrderPlacedEventDto.class);
            System.out.printf("Received OrderPlacedEvent: %s\n", event);
            inventoryService.decreaseStock(event.getProductId(), event.getQuantity());
        } catch (Exception e) {
            System.out.printf("Failed to consume event: %s\n", e.getMessage());
        }
    }
}