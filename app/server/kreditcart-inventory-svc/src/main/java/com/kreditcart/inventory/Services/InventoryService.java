package com.kreditcart.inventory.Services;

import com.kreditcart.inventory.Exceptions.ResourceNotFoundException;
import com.kreditcart.inventory.Models.Inventory;
import com.kreditcart.inventory.Repositories.InventoryRepo;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class InventoryService  {

    private final InventoryRepo inventoryRepository;

    public InventoryService(InventoryRepo inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }


    public Inventory createInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    public Inventory getInventory(UUID productId) {
        return inventoryRepository.findByProductId(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory not found for product id: " + productId));
    }

    public Inventory updateInventory(UUID productId, int quantity) {
        Inventory inventory = getInventory(productId);
        inventory.setQuantity(quantity);
        return inventoryRepository.save(inventory);
    }

    public Inventory increaseStock(UUID productId, int quantity) {
        Inventory inventory = getInventory(productId);
        inventory.setQuantity(inventory.getQuantity() + quantity);
        return inventoryRepository.save(inventory);
    }

    public Inventory decreaseStock(UUID productId, int quantity) {
        Inventory inventory = getInventory(productId);
        if (inventory.getQuantity() < quantity) {
            throw new IllegalStateException("Insufficient stock");
        }
        inventory.setQuantity(inventory.getQuantity() - quantity);
        return inventoryRepository.save(inventory);
    }

    public boolean isInStock(UUID productId, int quantity) {
        return inventoryRepository.findByProductId(productId)
                .map(inv -> inv.getQuantity() >= quantity)
                .orElse(false);
    }
}
