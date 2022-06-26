package com.example.transactiontracker.services.transactionservice;

import com.example.transactiontracker.models.payload.dto.TransactionDetailsDTO;
import com.example.transactiontracker.models.product.Product;
import com.example.transactiontracker.models.transaction.Transaction;
import com.example.transactiontracker.models.transaction.TransactionDetail;
import com.example.transactiontracker.models.transaction.TransactionType;
import com.example.transactiontracker.services.repositories.ProductRepository;
import com.example.transactiontracker.services.repositories.TransactionDetailsRepository;
import com.example.transactiontracker.services.repositories.TransactionRepository;
import com.example.transactiontracker.services.transactiondetailsservice.TransactionDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionDetailsRepository transactionDetailsRepository;
    private final TransactionDetailsService transactionDetailsService;
    private final ProductRepository productRepository;

    @Override
    public Transaction save(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public Optional<Transaction> findById(long id) {
        return transactionRepository.findById(id);
    }

    @Override
    public void deleteAll() {
        transactionRepository.deleteAll();
    }

    @Override
    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    @Override
    public void deleteById(long id) {
        System.out.println("in delete by id");
        updateProductInventoryOnDelete(id);
        transactionRepository.deleteById(id);
    }

    @Override
    public Transaction createTransaction(Transaction transaction, Optional<Transaction> transactionData) {
        Transaction transactionEntity = transactionData.get();
        transactionEntity.setCreatedAt(transaction.getCreatedAt());
        return transactionEntity;
    }

    @Override
    public List<TransactionDetail> findAllByTransaction_Id(long id) {
        return transactionDetailsRepository.findAllByTransaction_Id(id);
    }

    @Override
    public int getTransactionTotalFromTransaction(Transaction transaction){
        List<TransactionDetail> transactionDetailList = transactionDetailsRepository.findAllByTransaction_Id(transaction.getId());
        return transactionDetailList.stream().reduce(0, (partial, transactionDetail) -> partial + transactionDetail.getQuantity() * transactionDetail.getPrice(), Integer::sum);
    }

    @Override
    public List<Transaction> findAllByCreatedAt(LocalDateTime createdAt) {
        return transactionRepository.findAllByCreatedAt(createdAt);
    }

    @Override
    public List<Transaction> findTransactionsByCurrentDay() {
        return transactionRepository.findAllByCreatedAt(LocalDateTime.now());
    }

    @Override
    public List<Transaction> findTransactionsByShiftId(Long id) {
        return transactionRepository.findAllByShift_Id(id);
    }

    @Override
    public void updateProductInventoryOnDelete(long transactionId) {
        Transaction transaction = transactionRepository.getById(transactionId);
        List<TransactionDetail> transactionDetails = transactionDetailsRepository.findAllByTransaction_Id(transactionId);
        for(TransactionDetail transactionDetail : transactionDetails) {
            Product product = productRepository.getById(transactionDetail.getProduct().getId());
            if(transaction.getTransactionType().equals(TransactionType.SALE)) {
                product.setQuantity(product.getQuantity() + transactionDetail.getQuantity());
            } else if (transaction.getTransactionType().equals(TransactionType.RECEIVE)) {
                product.setQuantity(product.getQuantity() - transactionDetail.getQuantity());
            }

            productRepository.save(product);
        }
    }
}

