package com.example.transactiontracker.services.productservice;

import com.example.transactiontracker.models.Product;
import com.example.transactiontracker.models.ProductCategory;
import com.example.transactiontracker.repositories.ProductRepository;
import com.example.transactiontracker.services.productcategoryservice.ProductCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductCategoryService productCategoryService;

    @Override
    public List<Product> findByNameContaining(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> findById(long id) {
        return productRepository.findById(id);
    }

    @Override
    public void deleteAll() {
        productRepository.deleteAll();
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public void deleteById(long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product getProductAndSetAttributes(Product product, Optional<Product> productData) {
        Product productEntity = productData.get();
        productEntity.setCode(product.getCode());
        productEntity.setName(product.getName());
        productEntity.setCategory(product.getCategory());
        productEntity.setPrice(product.getPrice());
        productEntity.setUnit(product.getUnit());
        productEntity.setImage(product.getImage());
        return productEntity;
    }

    @Override
    public void creatInitialProducts() {
        createProduct("1234", "Water", getCategoryFromId("1"), 250, "bottle", "water.image");
        createProduct("2345", "Coke", getCategoryFromId("1"), 300, "bottle", "coke.image");
        createProduct("9999", "Nachos", getCategoryFromId("2"), 500, "box", "nachos.image");
    }

    @Override
    public void createProduct(String code, String name, ProductCategory category, int price, String unit, String image) {
        Product product = new Product(code, name, category, price, unit, image);
        save(product);
    }

    @Override
    public ProductCategory getCategoryFromId(String id) {
        return productCategoryService.findById(Long.parseLong(id)).orElse(null);
    }
}
