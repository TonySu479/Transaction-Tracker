package com.example.transactiontracker.models;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "transactions")
public class Transaction extends BaseEntity{

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;


    public Transaction(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Transaction() {

    }
}
