package com.example.transactiontracker.services.inventorycheckservice;

import com.example.transactiontracker.models.inventorycheck.InventoryCheck;

import java.util.List;

public interface InventoryCheckService {
    List<InventoryCheck> findAll();
}
