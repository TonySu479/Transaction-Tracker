package com.example.transactiontracker.services.productservice;

import com.example.transactiontracker.models.product.Product;
import com.example.transactiontracker.models.product.ProductCategory;
import com.example.transactiontracker.models.payload.dto.ProductDTO;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product save(Product product);

    void deleteAll();

    void deleteById(long id);

    Optional<Product> findById(long id);

    List<Product> findByNameContaining(String name);

    List<Product> findAll();

    Product setProductAttributesAndReturnNewEntity(ProductDTO productDTO, Optional<Product> productData);

    void creatInitialProducts();

    void createProduct(String code, String name, ProductCategory category, int price, String unit, String image, int quantity);

    ProductCategory getCategoryFromId(String id);

    String storeImage(String image);

    Product generateImageUrl(Product product);

    public List<Product> getAllProducts(String name);
}
