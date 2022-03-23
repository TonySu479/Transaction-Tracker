package com.example.transactiontracker.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "categories")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler"})
@NoArgsConstructor
public class ProductCategory extends BaseEntity {
    @Column(length = 20)
    private String name;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<Product> products;

    public ProductCategory(String name){
        this.name = name;
    }

}
