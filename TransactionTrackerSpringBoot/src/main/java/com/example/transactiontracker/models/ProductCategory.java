package com.example.transactiontracker.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "categories")
public class ProductCategory extends BaseEntity {
    @Column(length = 20)
    private String name;

    public ProductCategory(String productCategoryName) {
        this.name = productCategoryName;
    }

    public ProductCategory() {

    }
}
