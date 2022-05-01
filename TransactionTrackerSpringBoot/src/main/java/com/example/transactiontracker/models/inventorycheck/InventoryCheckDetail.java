package com.example.transactiontracker.models.inventorycheck;

import com.example.transactiontracker.models.base.BaseEntity;
import com.example.transactiontracker.models.product.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "inventory_check_details")
@NoArgsConstructor
@AllArgsConstructor
public class InventoryCheckDetail extends BaseEntity {

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private InventoryCheck inventoryCheck;

    @ManyToOne
    private Product product;

    @Column(name = "difference")
    private int difference;

}