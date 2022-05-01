package com.example.transactiontracker.controllers;

import com.example.transactiontracker.models.inventorycheck.InventoryCheck;
import com.example.transactiontracker.models.payload.dto.TransactionDTO;
import com.example.transactiontracker.models.transaction.Transaction;
import com.example.transactiontracker.services.inventorycheckservice.InventoryCheckService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/inventory-check")
@RequiredArgsConstructor
public class InventoryCheckController {

    private final InventoryCheckService inventoryCheckService;

    @GetMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<InventoryCheck>> getAllInventoryChecks() {
        try {
            List<InventoryCheck> inventoryChecks = inventoryCheckService.findAll();
            if (inventoryChecks.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(inventoryChecks, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
