package com.example.transactiontracker.repositories;

import com.example.transactiontracker.models.Product;
import com.example.transactiontracker.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByNameContaining(String title);
}
