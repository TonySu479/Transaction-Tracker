package com.example.transactiontracker.repositories;

import com.example.transactiontracker.models.inventory.InventoryEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryEntryRepository extends JpaRepository<InventoryEntry, Long> {
    InventoryEntry findByProduct_id(long id);
}
