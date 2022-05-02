package com.example.transactiontracker.services.inventorycheckservice;

import com.example.transactiontracker.models.inventorycheck.InventoryCheck;
import com.example.transactiontracker.models.inventorycheck.InventoryCheckDetail;
import com.example.transactiontracker.models.transaction.TransactionDetail;
import com.example.transactiontracker.services.repositories.InventoryCheckRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InventoryCheckImpl implements InventoryCheckService {

    private final InventoryCheckRepository inventoryCheckRepository;

    @Override
    public InventoryCheck save(InventoryCheck inventoryCheck) {
        return inventoryCheckRepository.save(inventoryCheck);
    }

    @Override
    public List<InventoryCheck> findAll() {
        return inventoryCheckRepository.findAll();
    }

    @Override
    public InventoryCheck create() {
        return inventoryCheckRepository.save(new InventoryCheck(new Date()));
    }

    @Override
    public Optional<InventoryCheck> findById(long id) {
        return inventoryCheckRepository.findById(id);
    }
}
