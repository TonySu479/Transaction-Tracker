package com.example.transactiontracker.controllers;

import com.example.transactiontracker.models.product.ProductCategory;
import com.example.transactiontracker.services.productcategoryservice.ProductCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductCategoryController {
    private final ProductCategoryService productCategoryService;

    @GetMapping("/product-categories/{id}")
    public ResponseEntity<ProductCategory> getProductCategoryById(@PathVariable("id") long id) {
        Optional<ProductCategory> productData = productCategoryService.findById(id);
        return productData.map(productCategory -> new ResponseEntity<>(productCategory, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/product-categories")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductCategory> createProductCategory(@RequestBody ProductCategory productCategory) {
        try {
            ProductCategory productCategoryEntity = productCategoryService
                    .save(new ProductCategory(productCategory.getName()));
            return new ResponseEntity<>(productCategoryEntity, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/product-categories/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductCategory> updateProductCategory(@PathVariable("id") long id, @RequestBody ProductCategory productCategory) {
        Optional<ProductCategory> productCategoryData = productCategoryService.findById(id);
        if (productCategoryData.isPresent()) {
            ProductCategory productEntity = productCategoryService.setProductCategoryAttributesAndReturnNewEntity(productCategory, productCategoryData);
            return new ResponseEntity<>(productCategoryService.save(productEntity), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/product-categories/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<HttpStatus> deleteProductCategory(@PathVariable("id") long id) {
        try {
            productCategoryService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/product-categories/delete-product-categories")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<HttpStatus> deleteProductCategories(@RequestBody List<String> listOfIds) {
        try {
            for (String id : listOfIds) {
                productCategoryService.deleteById(Long.parseLong(id));
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/product-categories")
    public ResponseEntity<List<ProductCategory>> getAllProductsCategories(@RequestParam(required = false) String name) {
        try {
            List<ProductCategory> products = new ArrayList<>();
            if (name == null)
                products.addAll(productCategoryService.findAll());
            else {
                products.addAll(productCategoryService.findByNameContainingIgnoreCase(name));
            }
            if (products.isEmpty()) {
                return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
            }
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
