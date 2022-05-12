package com.example.transactiontracker.services.inventorycheckdetailsservice;

import com.example.transactiontracker.models.inventorycheck.InventoryCheck;
import com.example.transactiontracker.models.inventorycheck.InventoryCheckDetail;
import com.example.transactiontracker.models.payload.dto.InventoryCheckDTO;
import com.example.transactiontracker.models.product.Product;
import com.example.transactiontracker.services.inventorycheckservice.InventoryCheckService;
import com.example.transactiontracker.services.productservice.ProductService;
import com.example.transactiontracker.services.repositories.InventoryCheckDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryCheckDetailsServiceImpl implements InventoryCheckDetailsService {

    private final InventoryCheckService inventoryCheckService;
    private final InventoryCheckDetailsRepository inventoryCheckDetailsRepository;
    private final ProductService productService;

    @Override
    public void inventoryCheck(List<InventoryCheckDTO> inventoryCheckDTOs) {
        InventoryCheck inventoryCheck = inventoryCheckService.createInventoryCheck();
        for(InventoryCheckDTO inventoryCheckDTO : inventoryCheckDTOs) {
            Product product = productService.getByCode(inventoryCheckDTO.getCode());
            inventoryCheckDetailsRepository.save(new InventoryCheckDetail(inventoryCheck,
                    product, product.getQuantity(), inventoryCheckDTO.getQuantity()));
        }
        productService.updateQuantities(inventoryCheckDTOs);
    }

    @Override
    public List<InventoryCheckDetail> findAllByInventoryCheck_Id(long id) {
        return inventoryCheckDetailsRepository.findAllByInventoryCheck_Id(id);
    }

}
