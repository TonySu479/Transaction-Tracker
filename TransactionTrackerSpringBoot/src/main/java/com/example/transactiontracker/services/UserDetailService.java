package com.example.transactiontracker.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

public interface UserDetailService {
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
