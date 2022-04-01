package com.example.transactiontracker.services.productcategoryservice;

import com.example.transactiontracker.models.ProductCategory;
import com.example.transactiontracker.repositories.ProductCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductCategoryServiceImpl implements ProductCategoryService {

    private final ProductCategoryRepository productCategoryRepository;

    @Override
    public List<ProductCategory> findAll() {
        return productCategoryRepository.findAll();
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return productCategoryRepository.save(productCategory);
    }

    @Override
    public void deleteAll() {
        productCategoryRepository.deleteAll();
    }

    @Override
    public void deleteById(long id) {
        productCategoryRepository.deleteById(id);
    }

    @Override
    public Optional<ProductCategory> findById(long id) {
        return productCategoryRepository.findById(id);
    }

    @Override
    public List<ProductCategory> findByNameContainingIgnoreCase(String name) {
        return productCategoryRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public ProductCategory setProductCategoryAttributesAndReturnNewEntity(ProductCategory productCategory, Optional<ProductCategory> productCategoryData) {
        ProductCategory productCategoryEntity = productCategoryData.get();
        productCategoryEntity.setName(productCategory.getName());
        return productCategoryEntity;
    }
}
