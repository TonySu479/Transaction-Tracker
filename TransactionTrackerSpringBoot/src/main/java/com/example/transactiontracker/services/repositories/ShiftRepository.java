package com.example.transactiontracker.services.repositories;

import com.example.transactiontracker.models.shift.Shift;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShiftRepository extends JpaRepository<Shift, Long> {
    Shift findByUser_IdAndShiftEndIsNull(Long user_id);
}
