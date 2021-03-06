package com.example.transactiontracker.controllers;

import com.example.transactiontracker.models.payload.response.MessageResponse;
import com.example.transactiontracker.models.product.ProductCategory;
import com.example.transactiontracker.services.productcategoryservice.ProductCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.*;

@RestController
@RequestMapping("/api/product-categories")
@RequiredArgsConstructor
public class ProductCategoryController {
    private final ProductCategoryService productCategoryService;

    @GetMapping("/{id}")
    public ResponseEntity<ProductCategory> getProductCategoryById(@PathVariable("id") long id) {
        Optional<ProductCategory> productData = productCategoryService.findById(id);
        return productData.map(productCategory -> new ResponseEntity<>(productCategory, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping()
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

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductCategory> updateProductCategory(@PathVariable("id") long id, @RequestBody ProductCategory productCategory) {
        Optional<ProductCategory> productCategoryData = productCategoryService.findById(id);
        if (productCategoryData.isPresent()) {
            ProductCategory productEntity = productCategoryService.createProductCategory(productCategory, productCategoryData);
            return new ResponseEntity<>(productCategoryService.save(productEntity), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MessageResponse> deleteProductCategory(@PathVariable("id") long id) {
        try {
            productCategoryService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>(new MessageResponse("ForeignKey Constraint error, Category is not empty"), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/delete-product-categories")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MessageResponse> deleteProductCategories(@RequestBody List<String> listOfIds) {
        try {
            for (String id : listOfIds) {
                productCategoryService.deleteById(Long.parseLong(id));
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>(new MessageResponse("ForeignKey Constraint error, the Categories are not empty"), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping()
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
