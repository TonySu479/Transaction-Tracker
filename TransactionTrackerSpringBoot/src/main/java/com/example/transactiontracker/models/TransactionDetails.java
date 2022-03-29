package com.example.transactiontracker.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "transaction_details")
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDetails extends BaseEntity {

    @ManyToOne
    private Transaction transaction;

    @ManyToOne
    private Product product;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private int price;

}
