package com.example.transactiontracker.models;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "products")
public class Product extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "unit")
    private String unit;

    @Column(name = "amount")
    private int amount;

    public Product(String name, String unit, int amount) {
        this.name = name;
        this.unit = unit;
        this.amount = amount;
    }

    public Product() {

    }
}
