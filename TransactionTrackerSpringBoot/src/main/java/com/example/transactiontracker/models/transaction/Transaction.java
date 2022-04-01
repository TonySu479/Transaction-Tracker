package com.example.transactiontracker.models.transaction;

import com.example.transactiontracker.models.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Calendar;

@Data
@Entity
@Table(name = "transactions")
@NoArgsConstructor
@AllArgsConstructor
public class Transaction extends BaseEntity {

    @Column(name = "created_at")
    private Calendar createdAt;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

}
