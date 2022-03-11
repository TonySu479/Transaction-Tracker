package com.example.transactiontracker.services.productservice;

import com.example.transactiontracker.models.Product;
import com.example.transactiontracker.models.Transaction;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product save(Product product);
    void deleteAll();
    void deleteById(long id);
    Optional<Product> findById(long id);
    List<Product> findAll();
}
