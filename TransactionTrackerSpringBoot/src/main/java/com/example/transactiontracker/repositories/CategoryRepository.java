package com.example.transactiontracker.repositories;

import com.example.transactiontracker.models.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryRepository extends JpaRepository<ProductCategory, Long> {

}