package com.example.transactiontracker.models;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;


    public Transaction(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Transaction() {

    }
}
