package com.example.transactiontracker.services.inventoryentryservice;

import com.example.transactiontracker.models.inventory.InventoryEntry;
import com.example.transactiontracker.repositories.InventoryEntryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService{

    private final InventoryEntryRepository inventoryEntryRepository;

    @Override
    public InventoryEntry save(InventoryEntry inventoryEntry) {
        return inventoryEntryRepository.save(inventoryEntry);
    }

    @Override
    public InventoryEntry findByProduct_id(long id) {
        return inventoryEntryRepository.findByProduct_id(id);
    }
}
