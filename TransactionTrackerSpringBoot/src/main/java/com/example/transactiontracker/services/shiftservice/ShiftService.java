package com.example.transactiontracker.services.shiftservice;

import com.example.transactiontracker.models.product.Product;
import com.example.transactiontracker.models.shift.Shift;

public interface ShiftService {
    public Shift save(Shift shift);
    public boolean checkUserInShift(Long id);
    public Shift createShift() throws Exception;
}