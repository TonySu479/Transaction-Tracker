package com.example.transactiontracker.models.shift;

import com.example.transactiontracker.models.base.BaseEntity;
import com.example.transactiontracker.models.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@Table(name = "shifts")
@NoArgsConstructor
@AllArgsConstructor
public class Shift extends BaseEntity {

    @Column(name = "shift_start", columnDefinition = "DATE")
    private LocalDateTime shiftStart;

    @Column(name = "shift_end", columnDefinition = "DATE")
    private LocalDateTime shiftEnd;

    @ManyToOne
    private User user;

    @Column(name = "total")
    private int total;

}
