package com.example.transactiontracker.services.inventorycheckservice;

import com.example.transactiontracker.models.inventorycheck.InventoryCheck;
import com.example.transactiontracker.services.repositories.InventoryCheckRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryCheckImpl implements InventoryCheckService {

    private final InventoryCheckRepository inventoryCheckRepository;

    @Override
    public List<InventoryCheck> findAll() {
        return inventoryCheckRepository.findAll();
    }
}
