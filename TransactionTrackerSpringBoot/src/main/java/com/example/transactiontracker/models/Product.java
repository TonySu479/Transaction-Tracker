package com.example.transactiontracker.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "products")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler"})
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseEntity {

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "category_id",
            referencedColumnName = "id"
    )
    private ProductCategory category;

    @Column(name = "price")
    private int price;

    @Column(name = "unit")
    private String unit;

    @Column(name = "image")
    private String image;

}
