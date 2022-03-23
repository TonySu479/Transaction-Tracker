package com.example.transactiontracker.controllers;

import com.example.transactiontracker.models.Product;
import com.example.transactiontracker.models.ProductCategory;
import com.example.transactiontracker.payload.dto.ProductDTO;
import com.example.transactiontracker.services.productcategoryservice.ProductCategoryService;
import com.example.transactiontracker.services.productservice.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
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
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<ProductCategory> updateProductCategory(@PathVariable("id") long id, @RequestBody ProductCategory productCategory) {
        Optional<ProductCategory> productCategoryData = productCategoryService.findById(id);
        if (productCategoryData.isPresent()) {
            ProductCategory productEntity = productCategoryService.getProductCategoryAndSetAttributes(productCategory, productCategoryData);
            return new ResponseEntity<>(productCategoryService.save(productEntity), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/product-categories/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<HttpStatus> deleteProductCategory(@PathVariable("id") long id) {
        try {
            productCategoryService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/product-categories")
    public ResponseEntity<List<ProductCategory>> getAllProducts(@RequestParam(required = false) String name) {
        try {
            List<ProductCategory> products = new ArrayList<>();
            if (name == null)
                products.addAll(productCategoryService.findAll());
            else{
                products.addAll(productCategoryService.findByNameContaining(name));
            }
            if (products.isEmpty()) {
                return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
            }
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/product-categories/delete-product-categories")
    public ResponseEntity<HttpStatus> deleteProductCategories(@RequestBody List<String> listOfIds){
        try {
            for(String id : listOfIds){
                productCategoryService.deleteById(Long.parseLong(id));
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
