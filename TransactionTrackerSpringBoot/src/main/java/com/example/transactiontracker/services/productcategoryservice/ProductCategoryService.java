package com.example.transactiontracker.services.productcategoryservice;

import com.example.transactiontracker.models.Product;
import com.example.transactiontracker.models.ProductCategory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface ProductCategoryService {
    List<ProductCategory> findAll();
    ProductCategory save(ProductCategory productCategory);
    void deleteAll();
    void deleteById(long id);
    Optional<ProductCategory> findById(long id);
}
