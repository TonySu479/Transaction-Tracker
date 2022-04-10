package com.example.transactiontracker.services.shiftservice;

import com.example.transactiontracker.models.shift.Shift;
import com.example.transactiontracker.models.user.User;
import com.example.transactiontracker.services.repositories.ShiftRepository;
import com.example.transactiontracker.services.userservice.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@AllArgsConstructor
public class ShiftServiceImpl implements ShiftService {
    private final ShiftRepository shiftRepository;
    private final UserService userService;

    @Override
    public Shift save(Shift shift) {
        return shiftRepository.save(shift);
    }

    @Override
    public boolean checkUserInShift(Long id) {
        return shiftRepository.findByUser_IdAndShiftEndIsNull(id) != null;
    }

    @Override
    public Shift createShift() throws Exception {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        User user = userService.findByUsername(userDetails.getUsername()).orElseThrow(Exception::new);
        if (checkUserInShift(user.getId())) {
            throw new Exception();
        }
        return save(new Shift(new Date(), null, user, 0));
    }
}
