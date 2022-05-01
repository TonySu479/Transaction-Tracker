package com.example.transactiontracker.services.repositories;

import com.example.transactiontracker.models.inventorycheck.InventoryCheck;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryCheckRepository extends JpaRepository<InventoryCheck, Long> {

}
