package com.example.transactiontracker.services.roleservice;

import com.example.transactiontracker.models.Role;
import com.example.transactiontracker.models.RoleType;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface RoleService {
    Optional<Role> findByName(RoleType roleUser);
}
