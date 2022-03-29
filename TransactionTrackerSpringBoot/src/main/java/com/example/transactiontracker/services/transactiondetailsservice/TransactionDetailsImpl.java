package com.example.transactiontracker.services.transactiondetailsservice;

import com.example.transactiontracker.models.TransactionDetails;
import com.example.transactiontracker.payload.dto.TransactionDetailsDTO;
import com.example.transactiontracker.repositories.ProductRepository;
import com.example.transactiontracker.repositories.TransactionDetailsRepository;
import com.example.transactiontracker.repositories.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionDetailsImpl implements TransactionDetailsService{

    private final TransactionDetailsRepository transactionDetailsRepository;
    private final TransactionRepository transactionRepository;
    private final ProductRepository productRepository;

    @Override
    public TransactionDetails save(TransactionDetails transactionDetails) {
        return transactionDetailsRepository.save(transactionDetails);
    }

    @Override
    public void deleteById(long id) {
        transactionDetailsRepository.deleteById(id);
    }

    @Override
    public Optional<TransactionDetails> findById(long id) {
        return transactionDetailsRepository.findById(id);
    }

    @Override
    public List<TransactionDetails> findAll() {
        return transactionDetailsRepository.findAll();
    }

    @Override
    public TransactionDetails setTransactionDetailsAttributesAndReturnNewEntity(TransactionDetailsDTO transactionDetailsDTO, Optional<TransactionDetails> transactionDetailsData) {
        TransactionDetails transactionDetailsEntity = transactionDetailsData.get();
        transactionDetailsEntity.setTransaction(transactionRepository.findById(transactionDetailsDTO.getTransactionId()).orElse(null));
        transactionDetailsEntity.setProduct(productRepository.findById(transactionDetailsDTO.getProductId()).orElse(null));
        transactionDetailsEntity.setPrice(transactionDetailsDTO.getPrice());
        transactionDetailsEntity.setQuantity(transactionDetailsDTO.getQuantity());
        return transactionDetailsEntity;
    }
}


