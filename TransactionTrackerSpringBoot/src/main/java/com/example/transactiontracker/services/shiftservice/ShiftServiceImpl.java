package com.example.transactiontracker.services.shiftservice;

import com.example.transactiontracker.services.repositories.ShiftRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ShiftServiceImpl implements ShiftService{
    private final ShiftRepository shiftRepository;
}
