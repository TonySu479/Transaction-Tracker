package com.example.transactiontracker.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Set;

@Data
@Entity
@Table(name = "transactions")
@NoArgsConstructor
@AllArgsConstructor
public class Transaction extends BaseEntity{

    @Column(name = "name")
    private String name;

    @Column(name = "created_at")
    private Calendar createdAt;

    @OneToMany(fetch = FetchType.LAZY)
    Set<Product> products;
    
}
