package com.example.transactiontracker.services.repositories;

import com.example.transactiontracker.models.product.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
    List<ProductCategory> findByNameContainingIgnoreCase(String name);
}
