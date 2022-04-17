package com.example.transactiontracker.services.productcategoryservice;

import com.example.transactiontracker.models.product.ProductCategory;

import java.util.List;
import java.util.Optional;

public interface ProductCategoryService {
    List<ProductCategory> findAll();

    ProductCategory save(ProductCategory productCategory);

    void deleteAll();

    void deleteById(long id);

    Optional<ProductCategory> findById(long id);

    List<ProductCategory> findByNameContainingIgnoreCase(String name);

    ProductCategory createProductCategory(ProductCategory productCategory, Optional<ProductCategory> productCategoryData);
}
