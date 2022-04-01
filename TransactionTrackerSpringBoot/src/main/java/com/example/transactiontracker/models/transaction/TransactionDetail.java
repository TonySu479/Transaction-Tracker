package com.example.transactiontracker.models.transaction;

import com.example.transactiontracker.models.base.BaseEntity;
import com.example.transactiontracker.models.product.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Data
@Entity
@Table(name = "transaction_details")
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDetail extends BaseEntity {

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Transaction transaction;

    @ManyToOne
    private Product product;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private int price;

}
