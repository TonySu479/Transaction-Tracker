package com.example.transactiontracker.models.product;

import com.example.transactiontracker.models.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "categories", uniqueConstraints=@UniqueConstraint(columnNames="name"))
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategory extends BaseEntity {
    @Column(length = 20)
    private String name;
}
