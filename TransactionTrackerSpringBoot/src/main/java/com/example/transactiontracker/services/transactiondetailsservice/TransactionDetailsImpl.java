package com.example.transactiontracker.services.transactiondetailsservice;

import com.example.transactiontracker.models.product.Product;
import com.example.transactiontracker.models.transaction.Transaction;
import com.example.transactiontracker.models.transaction.TransactionDetail;
import com.example.transactiontracker.models.transaction.TransactionType;
import com.example.transactiontracker.models.payload.dto.TransactionDetailsDTO;
import com.example.transactiontracker.models.payload.response.TransactionDetailResponse;
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
    public TransactionDetail setTransactionDetailsAttributesAndReturnNewEntity(TransactionDetailsDTO transactionDetailsDTO, TransactionDetail transactionDetailsData) {
        transactionDetailsData.setTransaction(transactionRepository.findById(transactionDetailsDTO.getTransactionId()).orElse(null));
        transactionDetailsData.setProduct(productRepository.findById(transactionDetailsDTO.getProductId()).orElse(null));
        transactionDetailsData.setPrice(transactionDetailsDTO.getPrice());
        transactionDetailsData.setQuantity(transactionDetailsDTO.getQuantity());
        return transactionDetailsData;
    }

    @Override
    public TransactionDetailResponse generateImageUrl(TransactionDetailResponse transactionDetailResponse) {
        transactionDetailResponse.getProduct().setImage(imageBaseURL + transactionDetailResponse.getProduct().getImage());
        return transactionDetailResponse;
    }

    @Override
    public void updateProductInventory(TransactionDetailsDTO transactionDetailsDTO) {
        Transaction transaction = transactionRepository.getById(transactionDetailsDTO.getTransactionId());
        Product product = productRepository.getById(transactionDetailsDTO.getProductId());

        if (transaction.getTransactionType() == TransactionType.SALE) {
            product.setQuantity(product.getQuantity() - transactionDetailsDTO.getQuantity());
        } else if (transaction.getTransactionType() == TransactionType.RECEIVE) {
            product.setQuantity(product.getQuantity() + transactionDetailsDTO.getQuantity());
        }
        productRepository.save(product);
    }

    @Override
    public TransactionDetailResponse update(TransactionDetail transactionDetailsData, TransactionDetailsDTO transactionDetailsDTO) {
        TransactionDetail transactionDetailEntity = setTransactionDetailsAttributesAndReturnNewEntity(transactionDetailsDTO, transactionDetailsData);
        save(transactionDetailEntity);
        TransactionDetailResponse response = new TransactionDetailResponse(transactionDetailEntity.getTransaction(),
                transactionDetailEntity.getProduct(), transactionDetailEntity.getQuantity(),
                transactionDetailEntity.getPrice());
        updateProductInventory(transactionDetailsDTO);
        generateImageUrl(response);
        return response;
    }
}


