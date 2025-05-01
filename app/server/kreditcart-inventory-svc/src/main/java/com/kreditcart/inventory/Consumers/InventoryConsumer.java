package com.kreditcart.inventory.Consumers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kreditcart.inventory.Dtos.OrderPlacedEventDto;
import com.kreditcart.inventory.Dtos.ProductCreatedEventDto;
import com.kreditcart.inventory.Models.Inventory;
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

    @KafkaListener(topics = "productCreated", groupId = "productService")
    public void handleProductCreated(String message) {
        try {
            ProductCreatedEventDto event = objectMapper.readValue(message, ProductCreatedEventDto.class);
            System.out.printf("Received productCreatedEvent: %s\n", event);
            Inventory inventory = new Inventory();
            inventory.setProductId(event.getProductId());
            inventory.setQuantity(event.getInitialStock());
            inventoryService.createInventory(inventory);
        } catch (Exception e) {
            System.out.printf("Failed to consume event: %s\n", e.getMessage());
        }
    }
}