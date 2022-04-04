package com.example.transactiontracker.services.productservice;

import com.example.transactiontracker.models.product.Product;
import com.example.transactiontracker.models.product.ProductCategory;
import com.example.transactiontracker.models.payload.dto.ProductDTO;
import com.example.transactiontracker.repositories.ProductRepository;
import com.example.transactiontracker.services.productcategoryservice.ProductCategoryService;
import liquibase.util.file.FilenameUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductCategoryService productCategoryService;
    private final String imagePath = "./TransactionTrackerSpringBoot/assets/images/";
    private final String imageBaseURL = "//localhost:8080/images/";

    Date date = new Date();
    Random rand = new Random();

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
    public Product setProductAttributesAndReturnNewEntity(ProductDTO productDTO, Optional<Product> productData) {
        Product productEntity = productData.get();
        if (productDTO.getImage().startsWith("//")) {
            String url = productDTO.getImage();
            productEntity.setImage(FilenameUtils.getName(url.substring(url.lastIndexOf('/') + 1)));
        } else {
            productEntity.setImage(storeImage(productDTO.getImage()));
        }
        productEntity.setCode(productDTO.getCode());
        productEntity.setName(productDTO.getName());
        productEntity.setCategory(productDTO.getCategory());
        productEntity.setPrice(productDTO.getPrice());
        productEntity.setUnit(productDTO.getUnit());
        productEntity.setQuantity(productDTO.getQuantity());

        return productEntity;
    }

    @Override
    public void creatInitialProducts() {
        createProduct("1234", "Water", getCategoryFromId("1"), 5, "bottle", "water.jpg", 10);
        createProduct("2345", "Coke", getCategoryFromId("1"), 10, "bottle", "coke.jpg", 20);
        createProduct("9999", "Nachos", getCategoryFromId("2"), 15, "box", "nachos.jpg", 30);
    }

    @Override
    public void createProduct(String code, String name, ProductCategory category, int price, String unit, String image, int quantity) {
        Product product = new Product(code, name, category, price, unit, image, quantity);
        save(product);
    }

    @Override
    public ProductCategory getCategoryFromId(String id) {
        return productCategoryService.findById(Long.parseLong(id)).orElse(null);
    }

    @Override
    public String storeImage(String image) {
        String uniqueImgName = "" + date.getTime() + rand.nextInt(10000) + ".jpg";
        byte[] img = Base64.decodeBase64(image);
        try (OutputStream stream = new FileOutputStream(imagePath + uniqueImgName)) {
            stream.write(img);
        } catch (Exception e) {
            log.error(e.toString());
        }
        return uniqueImgName;
    }

    @Override
    public Product generateImageUrl(Product product) {
        product.setImage(imageBaseURL + product.getImage());
        return product;
    }
}
