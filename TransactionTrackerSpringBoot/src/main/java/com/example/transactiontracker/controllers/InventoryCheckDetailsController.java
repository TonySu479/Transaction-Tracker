package com.example.transactiontracker.controllers;

import com.example.transactiontracker.models.inventorycheck.InventoryCheckDetail;
import com.example.transactiontracker.models.payload.dto.InventoryCheckDTO;

import com.example.transactiontracker.services.inventorycheckdetailsservice.InventoryCheckDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/inventory-check-details")
@RequiredArgsConstructor
public class InventoryCheckDetailsController {

    private final InventoryCheckDetailsService inventoryCheckDetailsService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<HttpStatus> inventoryCheck(@RequestBody List<InventoryCheckDTO> inventoryCheckDTOS) {
        try {
            inventoryCheckDetailsService.inventoryCheck(inventoryCheckDTOS);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<InventoryCheckDetail>> getInventoryCheckDetailsByInventoryCheckId(@RequestParam long id) {
        try {
            List<InventoryCheckDetail> inventoryCheckDetails = new ArrayList<>(inventoryCheckDetailsService.findAllByInventoryCheck_Id(id));
            if (inventoryCheckDetails.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(inventoryCheckDetails, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
