package com.example.transactiontracker.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;

@Data
@Entity
@Table(name = "transactions")
@NoArgsConstructor
public class Transaction extends BaseEntity{

    @Column(name = "created_at")
    private Calendar createdAt;

    public Transaction(Calendar createdAt){
        this.createdAt = createdAt;
    }
}
