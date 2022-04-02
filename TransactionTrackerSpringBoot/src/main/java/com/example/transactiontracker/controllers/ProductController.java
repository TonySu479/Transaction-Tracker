package com.example.transactiontracker.controllers;

import com.example.transactiontracker.models.product.Product;
import com.example.transactiontracker.payload.dto.ProductDTO;
import com.example.transactiontracker.services.productservice.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final String imageBaseURL = "//localhost:8080/images/";

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") long id) {
        Optional<Product> productData = productService.findById(id);
        return productData.map(product -> new ResponseEntity<>(product, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/products")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Product> createProduct(@RequestBody ProductDTO productDTO) {
        try {
            String uniqueImgName = productService.storeImage(productDTO.getImage());
            Product productEntity = productService
                    .save(new Product(productDTO.getCode(), productDTO.getName(), productDTO.getCategory(), productDTO.getPrice(), productDTO.getUnit(), uniqueImgName, productDTO.getQuantity()));
            productService.generateImageUrl(productEntity);
            return new ResponseEntity<>(productEntity, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/products/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") long id, @RequestBody ProductDTO productDTO) {
        Optional<Product> productData = productService.findById(id);
        if (productData.isPresent()) {
            Product productEntity = productService.setProductAttributesAndReturnNewEntity(productDTO, productData);
            productService.save(productEntity);
            productService.generateImageUrl(productEntity);
            return new ResponseEntity<>(productEntity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/products/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("id") long id) {
        try {
            productService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(@RequestParam(required = false) String name) {
        try {
            List<Product> products = new ArrayList<>();
            if (name == null)
                products.addAll(productService.findAll());
            else {
                products.addAll(productService.findByNameContaining(name));
            }
            if (products.isEmpty()) {
                return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
            }
            for (Product product : products) {
                productService.generateImageUrl(product);
            }
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/products/delete-products")
    public ResponseEntity<HttpStatus> deleteProducts(@RequestBody List<String> listOfIds) {
        try {
            for (String id : listOfIds) {
                productService.deleteById(Long.parseLong(id));
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
