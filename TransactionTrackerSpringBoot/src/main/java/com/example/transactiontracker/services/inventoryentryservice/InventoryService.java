package com.example.transactiontracker.services.inventoryentryservice;

import com.example.transactiontracker.models.inventory.InventoryEntry;

public interface InventoryService {
    InventoryEntry save(InventoryEntry inventoryEntry);

    InventoryEntry findByProduct_id(long id);
}
