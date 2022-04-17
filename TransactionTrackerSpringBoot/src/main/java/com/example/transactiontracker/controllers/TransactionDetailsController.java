package com.example.transactiontracker.controllers;
import com.example.transactiontracker.models.payload.dto.TransactionDetailsListDTO;
import com.example.transactiontracker.models.transaction.TransactionDetail;
import com.example.transactiontracker.models.payload.dto.TransactionDetailsDTO;
import com.example.transactiontracker.models.payload.response.TransactionDetailResponse;
import com.example.transactiontracker.services.repositories.TransactionDetailsRepository;
import com.example.transactiontracker.services.productservice.ProductService;
import com.example.transactiontracker.services.transactiondetailsservice.TransactionDetailsService;
import com.example.transactiontracker.services.transactionservice.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/transaction-details")
@RequiredArgsConstructor
public class TransactionDetailsController {

    private final TransactionDetailsService transactionDetailsService;
    private final TransactionService transactionService;
    private final ProductService productService;

    @PostMapping()
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<TransactionDetailResponse> createTransactionDetail(@RequestBody TransactionDetailsDTO transactionDetails) {
        try {
            TransactionDetail transactionDetailEntity = transactionDetailsService
                    .save(new TransactionDetail(transactionService.findById(transactionDetails.getTransactionId()).orElse(null), productService.findById(transactionDetails.getProductId()).orElse(null),
                            transactionDetails.getQuantity(), transactionDetails.getPrice()));
            TransactionDetailResponse response = new TransactionDetailResponse(transactionDetailEntity.getId(), transactionDetailEntity.getTransaction(),
                    transactionDetailEntity.getProduct(), transactionDetailEntity.getQuantity(),
                    transactionDetailEntity.getPrice());
            transactionDetailsService.updateProductInventory(transactionDetails);
            transactionDetailsService.generateImageUrl(response);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/cashier")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<HttpStatus> createTransactionDetails(@RequestBody TransactionDetailsListDTO transactionDetailsDTOs) {
        try {

            Long transactionId = transactionDetailsService.saveAll(transactionDetailsDTOs);
            for(var transactionDetailsDTO : transactionDetailsDTOs.getTransactionDetailsDTOS()){
                transactionDetailsDTO.setTransactionId(transactionId);
                transactionDetailsService.updateProductInventory(transactionDetailsDTO);
            }
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TransactionDetailResponse> updateTransactionDetail(@PathVariable("id") long id, @RequestBody TransactionDetailsDTO transactionDetailsDTO) {
        Optional<TransactionDetail> TransactionDetailsData = transactionDetailsService.findById(id);
        if (TransactionDetailsData.isPresent()) {
            return new ResponseEntity<>(transactionDetailsService.update(TransactionDetailsData.get(), transactionDetailsDTO), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<HttpStatus> deleteTransactionDetail(@PathVariable("id") long id) {
        try {
            transactionDetailsService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<TransactionDetailResponse>> getTransactionDetailsByTransactionId(@RequestParam long id) {
        try {
            List<TransactionDetail> transactionDetails = new ArrayList<>(transactionDetailsService.findAllByTransaction_Id(id));
            if (transactionDetails.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            List<TransactionDetailResponse> transactionDetailResponses = transactionDetailsService.createTransactionDetailsResponses(transactionDetails);
            return new ResponseEntity<>(transactionDetailResponses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
