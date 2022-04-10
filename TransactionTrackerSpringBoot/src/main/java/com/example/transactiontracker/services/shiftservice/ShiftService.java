package com.example.transactiontracker.services.shiftservice;

import com.example.transactiontracker.models.shift.Shift;

public interface ShiftService {
    public Shift save(Shift shift);
    public Shift createShift() throws Exception;
    public Shift endShift() throws Exception;
    public boolean checkUserInShift(Long id);
}