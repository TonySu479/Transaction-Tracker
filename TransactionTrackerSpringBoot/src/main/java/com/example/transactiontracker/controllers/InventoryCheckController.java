package com.example.transactiontracker.controllers;

import com.example.transactiontracker.models.inventorycheck.InventoryCheck;
import com.example.transactiontracker.models.transaction.Transaction;
import com.example.transactiontracker.services.inventorycheckservice.InventoryCheckService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/inventory-check")
@RequiredArgsConstructor
public class InventoryCheckController {

    private final InventoryCheckService inventoryCheckService;

    @GetMapping("/{id}")
    public ResponseEntity<InventoryCheck> getInventoryCheckById(@PathVariable("id") long id) {
        Optional<InventoryCheck> inventoryCheckData = inventoryCheckService.findById(id);
        return inventoryCheckData.map(inventoryCheck -> new ResponseEntity<>(inventoryCheck, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<InventoryCheck> createInventoryCheck(@RequestBody InventoryCheck inventoryCheck) {
        try {
            return new ResponseEntity<>(inventoryCheckService
                    .save(new InventoryCheck(inventoryCheck.getCreatedAt())), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

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
