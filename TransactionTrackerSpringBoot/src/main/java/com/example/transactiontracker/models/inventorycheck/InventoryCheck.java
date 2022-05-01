package com.example.transactiontracker.models.inventorycheck;

import com.example.transactiontracker.models.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "inventory_checks")
@NoArgsConstructor
@AllArgsConstructor
public class InventoryCheck extends BaseEntity {

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

}


