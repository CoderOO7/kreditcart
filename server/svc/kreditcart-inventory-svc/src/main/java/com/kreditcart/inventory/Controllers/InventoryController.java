package com.kreditcart.inventory.Controllers;

import com.kreditcart.inventory.Dtos.InventoryDto;
import com.kreditcart.inventory.Services.InventoryService;
import com.kreditcart.inventory.Models.Inventory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/inventory-svc/inventories")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping
    public ResponseEntity<Inventory> createInventory(@RequestBody InventoryDto request) {
        Inventory inventory = inventoryService.createInventory(this.getInventoryFromInventoryDto(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(inventory);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Inventory> getInventory(@PathVariable UUID productId) {
        return ResponseEntity.ok(inventoryService.getInventory(productId));
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Inventory> updateInventory(@PathVariable UUID productId,
                                                     @RequestParam int quantity) {
        return ResponseEntity.ok(inventoryService.updateInventory(productId, quantity));
    }

    @PatchMapping("/{productId}/decrease")
    public ResponseEntity<Inventory> decreaseInventory(@PathVariable UUID productId,
                                                       @RequestParam int quantity) {
        return ResponseEntity.ok(inventoryService.decreaseStock(productId, quantity));
    }

    @PatchMapping("/{productId}/increase")
    public ResponseEntity<Inventory> increaseInventory(@PathVariable UUID productId,
                                                       @RequestParam int quantity) {
        return ResponseEntity.ok(inventoryService.increaseStock(productId, quantity));
    }

    private Inventory getInventoryFromInventoryDto(InventoryDto inventoryDto) {
        Inventory inventory = new Inventory();
        inventory.setQuantity(inventoryDto.getQuantity());
        inventory.setProductId(inventoryDto.getProductId());

        return inventory;
    }
}