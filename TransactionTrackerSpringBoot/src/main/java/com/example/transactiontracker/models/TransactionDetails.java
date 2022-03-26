package com.example.transactiontracker.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "transaction_details")
@NoArgsConstructor
public class TransactionDetails extends BaseEntity {

    @ManyToOne
    private Transaction Transaction;

    @ManyToOne
    private Product product;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private int price;

}
