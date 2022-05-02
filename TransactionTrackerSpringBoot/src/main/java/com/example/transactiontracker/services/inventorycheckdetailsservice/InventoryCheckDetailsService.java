package com.example.transactiontracker.services.inventorycheckdetailsservice;

import com.example.transactiontracker.models.inventorycheck.InventoryCheckDetail;
import com.example.transactiontracker.models.payload.dto.InventoryCheckDTO;
import java.util.List;

public interface InventoryCheckDetailsService {
    void inventoryCheck(List<InventoryCheckDTO> inventoryCheckDTOs);
    List<InventoryCheckDetail> findAllByInventoryCheck_Id(long id);

}
