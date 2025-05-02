package com.kreditcart.inventory.Repositories;

import com.kreditcart.inventory.Models.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface InventoryRepo extends JpaRepository<Inventory, UUID> {
    Optional<Inventory> findByProductId(UUID productId);
}
