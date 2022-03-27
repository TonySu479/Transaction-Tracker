package com.example.transactiontracker.services.transactiondetailsservice;

import com.example.transactiontracker.models.TransactionDetails;
import com.example.transactiontracker.repositories.TransactionDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionDetailsImpl implements TransactionDetailsService{

    private final TransactionDetailsRepository transactionDetailsRepository;

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
}


