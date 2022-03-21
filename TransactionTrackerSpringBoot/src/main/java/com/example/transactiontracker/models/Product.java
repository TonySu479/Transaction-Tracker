package com.example.transactiontracker.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "products")
public class Product extends BaseEntity {

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    private ProductCategory category;

    @Column(name = "price")
    private int price;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "unit")
    private String unit;

    @Column(name = "image")
    private String image;

    public Product() {

    }

    public Product(String code, String name, ProductCategory category, int price, int quantity, String unit, String image) {
        this.code = code;
        this.name = name;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.unit = unit;
        this.image = image;
    }
}
