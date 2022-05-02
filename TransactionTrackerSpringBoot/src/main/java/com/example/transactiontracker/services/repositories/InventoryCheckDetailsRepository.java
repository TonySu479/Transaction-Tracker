package com.example.transactiontracker.services.repositories;

import com.example.transactiontracker.models.inventorycheck.InventoryCheckDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryCheckDetailsRepository extends JpaRepository<InventoryCheckDetail, Long> {
    List<InventoryCheckDetail> findAllByInventoryCheck_Id(long id);
}
