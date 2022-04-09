package com.example.transactiontracker.services.shiftservice;

import com.example.transactiontracker.models.shift.Shift;
import com.example.transactiontracker.services.repositories.ShiftRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ShiftServiceImpl implements ShiftService {
    private final ShiftRepository shiftRepository;

    @Override
    public Shift save(Shift shift) {
        return shiftRepository.save(shift);
    }

    @Override
    public boolean checkUserInShift(Long id) {
        return shiftRepository.findByUser_IdAndShiftEndIsNull(id) != null;
    }
}
