package com.example.transactiontracker.services.inventorycheckservice;

import com.example.transactiontracker.models.inventorycheck.InventoryCheck;

import java.util.List;
import java.util.Optional;

public interface InventoryCheckService {
    InventoryCheck save(InventoryCheck inventoryCheck);

    List<InventoryCheck> findAll();

    InventoryCheck createInventoryCheck();

    Optional<InventoryCheck> findById(long id);
}
