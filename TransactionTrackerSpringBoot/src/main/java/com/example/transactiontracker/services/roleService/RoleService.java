package com.example.transactiontracker.services.roleService;

import com.example.transactiontracker.models.Role;
import com.example.transactiontracker.models.RoleType;

import java.util.Optional;

public interface RoleService {
    Optional<Role> findByName(RoleType roleUser);
}
