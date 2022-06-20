package com.example.transactiontracker.models.transaction;

import com.example.transactiontracker.models.base.BaseEntity;
import com.example.transactiontracker.models.shift.Shift;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "transactions")
@NoArgsConstructor
@AllArgsConstructor
public class Transaction extends BaseEntity {

    @Column(name = "created_at", columnDefinition = "DATE")
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @ManyToOne
    private Shift shift;

    public Transaction(LocalDateTime createdAt, TransactionType transactionType){
        this.createdAt = createdAt;
        this.transactionType = transactionType;
    }

}
