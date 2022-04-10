package com.example.transactiontracker.services.shiftservice;

import com.example.transactiontracker.models.shift.Shift;

public interface ShiftService {
    Shift save(Shift shift);
    Shift createShift() throws Exception;
    Shift endShift() throws Exception;
    boolean checkUserInShift(Long id);
}