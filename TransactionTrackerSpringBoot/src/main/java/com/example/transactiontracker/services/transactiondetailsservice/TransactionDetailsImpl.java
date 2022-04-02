package com.example.transactiontracker.services.transactiondetailsservice;

import com.example.transactiontracker.models.transaction.TransactionDetail;
import com.example.transactiontracker.payload.dto.TransactionDetailsDTO;
import com.example.transactiontracker.repositories.ProductRepository;
import com.example.transactiontracker.repositories.TransactionDetailsRepository;
import com.example.transactiontracker.repositories.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionDetailsImpl implements TransactionDetailsService {

    private final TransactionDetailsRepository transactionDetailsRepository;
    private final TransactionRepository transactionRepository;
    private final ProductRepository productRepository;
    private final String imageBaseURL = "//localhost:8080/images/";

    @Override
    public TransactionDetail save(TransactionDetail transactionDetail) {
        return transactionDetailsRepository.save(transactionDetail);
    }

    @Override
    public void deleteById(long id) {
        transactionDetailsRepository.deleteById(id);
    }

    @Override
    public Optional<TransactionDetail> findById(long id) {
        return transactionDetailsRepository.findById(id);
    }

    @Override
    public TransactionDetail setTransactionDetailsAttributesAndReturnNewEntity(TransactionDetailsDTO transactionDetailsDTO, Optional<TransactionDetail> transactionDetailsData) {
        TransactionDetail transactionDetailEntity = transactionDetailsData.get();
        transactionDetailEntity.setTransaction(transactionRepository.findById(transactionDetailsDTO.getTransactionId()).orElse(null));
        transactionDetailEntity.setProduct(productRepository.findById(transactionDetailsDTO.getProductId()).orElse(null));
        transactionDetailEntity.setPrice(transactionDetailsDTO.getPrice());
        transactionDetailEntity.setQuantity(transactionDetailsDTO.getQuantity());
        return transactionDetailEntity;
    }

    @Override
    public TransactionDetail generateImageUrl(TransactionDetail transactionDetail) {
        if(!transactionDetail.getProduct().getImage().startsWith(imageBaseURL)){
            transactionDetail.getProduct().setImage(imageBaseURL + transactionDetail.getProduct().getImage());
        }
        return transactionDetail;
    }
}


