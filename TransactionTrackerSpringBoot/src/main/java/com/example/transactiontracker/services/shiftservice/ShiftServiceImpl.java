package com.example.transactiontracker.services.shiftservice;

import com.example.transactiontracker.models.shift.Shift;
import com.example.transactiontracker.models.transaction.Transaction;
import com.example.transactiontracker.models.user.User;
import com.example.transactiontracker.services.repositories.ShiftRepository;
import com.example.transactiontracker.services.transactionservice.TransactionService;
import com.example.transactiontracker.services.userservice.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class ShiftServiceImpl implements ShiftService {
    private final ShiftRepository shiftRepository;
    private final UserService userService;
    private final TransactionService transactionService;

    @Override
    public Shift save(Shift shift) {
        return shiftRepository.save(shift);
    }

    @Override
    public Shift createShift() throws Exception {
        User user = getCurrentUser();
        if (checkUserInShift(user.getId())) {
            return shiftRepository.findByUser_IdAndShiftEndIsNull(user.getId());
        }
        return save(new Shift(LocalDate.now(), null, user, 0));
    }

    @Override
    public Shift endShift() throws Exception {
        User user = getCurrentUser();
        if (!checkUserInShift(user.getId())) {
            throw new Exception("User is not in a shift");
        }
        return getShift(user);
    }

    @Override
    public boolean checkUserInShift(Long id) {
        return shiftRepository.findByUser_IdAndShiftEndIsNull(id) != null;
    }

    private User getCurrentUser() throws Exception {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        return userService.findByUsername(userDetails.getUsername()).orElseThrow(Exception::new);
    }

    private Shift getShift(User user) {
        Shift shift = shiftRepository.findByUser_IdAndShiftEndIsNull(user.getId());
        shift.setShiftEnd(LocalDate.now());
        setShiftTotal(shift);
        shiftRepository.save(shift);
        return shift;
    }

    private void setShiftTotal(Shift shift) {
        List<Transaction> transactions = transactionService.findTransactionsByShiftId(shift.getId());
        int total = 0;
        for (Transaction transaction :transactions) {
            total += transactionService.getTransactionTotalFromTransaction(transaction);
        }
        shift.setTotal(total);
    }
}
