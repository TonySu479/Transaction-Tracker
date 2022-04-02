package com.example.transactiontracker.models.inventory;

import com.example.transactiontracker.models.base.BaseEntity;
import com.example.transactiontracker.models.product.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "inventory")
@NoArgsConstructor
@AllArgsConstructor
public class InventoryEntry extends BaseEntity {

    @OneToOne
    Product product;

    @Column
    int quantity;
}

