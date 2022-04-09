package com.example.transactiontracker.models.shift;

import com.example.transactiontracker.models.base.BaseEntity;
import com.example.transactiontracker.models.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "shifts")
@NoArgsConstructor
@AllArgsConstructor
public class Shift extends BaseEntity {

    @Column(name = "shift_start")
    @Temporal(TemporalType.TIMESTAMP)
    private Date shiftStart;

    @Column(name = "shift_end")
    @Temporal(TemporalType.TIMESTAMP)
    private Date shiftEnd;

    @ManyToOne
    private User user;

    @Column(name = "total")
    private int total;

}
