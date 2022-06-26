package com.example.transactiontracker.services.productservice;

import com.example.transactiontracker.models.payload.dto.InventoryCheckDTO;
import com.example.transactiontracker.models.product.Product;
import com.example.transactiontracker.models.product.ProductCategory;
import com.example.transactiontracker.models.payload.dto.ProductDTO;
import com.example.transactiontracker.services.repositories.ProductRepository;
import com.example.transactiontracker.services.productcategoryservice.ProductCategoryService;
import liquibase.util.file.FilenameUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductCategoryService productCategoryService;
    private static final String IMAGE_PATH = "./TransactionTrackerSpringBoot/assets/images/";
    private static final String IMAGE_BASE_URL = "//localhost:8080/images/";

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
    public Product createProductFromProductDTO(ProductDTO productDTO, Product productEntity) {
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
    public void createInitialProducts() {
        createProduct("1234", "Water", getCategoryFromId("2"), 150, "bottle", "water.jpg", 100);
        createProduct("2345", "Coke", getCategoryFromId("2"), 250, "bottle", "coke.jpg", 32);
        createProduct("2346", "Iced Tea", getCategoryFromId("2"), 250, "bottle", "icedtea.jpg", 40);
        createProduct("4332", "Salsa", getCategoryFromId("3"), 300, "box", "salsa.jpg", 65);
        createProduct("4333", "Cheese Sauce", getCategoryFromId("3"), 300, "box", "cheesesauce.jpg", 80);
        createProduct("4336", "Nachos", getCategoryFromId("1"), 600, "box", "nachos.jpg", 100);
        createProduct("4337", "Popcorn", getCategoryFromId("1"), 600, "box", "popcorn.jpg", 60);
        createProduct("4342", "Sandwich", getCategoryFromId("1"), 400, "piece", "sandwich.jpg", 120);
        createProduct("4488", "Candy", getCategoryFromId("1"), 450, "bag", "candy.jpg", 220);
        createProduct("6623", "3d Glasses", getCategoryFromId("4"), 500, "piece", "3dglasses.jpg", 100);
        createProduct("6629", "Plastic Bag", getCategoryFromId("4"), 200, "piece", "plasticbag.jpg", 500);
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
    public String storeImage(String imageBase64) {
        String uniqueImgName = "";
        if(imageBase64 != ""){
            uniqueImgName = "" + System.currentTimeMillis() + new Random().nextInt(10000) + ".jpg";
            byte[] img = Base64.decodeBase64(imageBase64);
            try (OutputStream stream = new FileOutputStream(IMAGE_PATH + uniqueImgName)) {
                stream.write(img);
            } catch (Exception e) {
                log.error(e.toString());
            }
        }
        return uniqueImgName;
    }

    @Override
    public Product generateImageUrl(Product product) {
        if(!product.getImage().equals("")){
            product.setImage(IMAGE_BASE_URL + product.getImage());
        } else {
            product.setImage(IMAGE_BASE_URL + "noimage.png");
        }
        return product;
    }

    @Override
    public List<InventoryCheckDTO> getInventoryCheckQuantityDifferences(List<InventoryCheckDTO> productInventoryCheckDTOs) {
        List<InventoryCheckDTO> response = new ArrayList<>();
        for (InventoryCheckDTO productInvDTO : productInventoryCheckDTOs) {
            String code = productInvDTO.getCode();
            InventoryCheckDTO pic = new InventoryCheckDTO(code, productInvDTO.getName(), productInvDTO.getQuantity() - productRepository.getByCode(code).getQuantity());
            response.add(pic);
        }
        return response;
    }

    @Override
    public void updateQuantities(List<InventoryCheckDTO> productInventoryCheckDTOs) {
        for(InventoryCheckDTO productInvDTO : productInventoryCheckDTOs) {
            String productCode = productInvDTO.getCode();
            Product product = productRepository.getByCode(productCode);
            product.setQuantity(productInvDTO.getQuantity());
            productRepository.save(product);
        }
    }

    @Override
    public Product getByCode(String code) {
        return productRepository.getByCode(code);
    }

    @Override
    public List<Product> getAllProducts(String name) {
        List<Product> products = new ArrayList<>();
        if (name == null)
            products.addAll(findAll());
        else {
            products.addAll(findByNameContaining(name));
        }
        for (Product product : products) {
            generateImageUrl(product);
        }
        return products;
    }
}
