package com.example.transactiontracker.services.productservice;

import com.example.transactiontracker.models.Product;
import com.example.transactiontracker.models.ProductCategory;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product save(Product product);
    void deleteAll();
    void deleteById(long id);
    Optional<Product> findById(long id);
    List<Product> findByNameContaining(String name);
    List<Product> findAll();
    Product getProductAndSetAttributes(Product product, Optional<Product> productData);
    void creatInitialProducts();
    void createProduct(String code, String name, ProductCategory category, int price, String unit, String image);
    ProductCategory getCategoryFromId(String id);
}
