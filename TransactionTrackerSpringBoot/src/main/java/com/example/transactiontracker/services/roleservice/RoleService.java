package com.example.transactiontracker.services.roleservice;

import com.example.transactiontracker.models.user.role.Role;
import com.example.transactiontracker.models.user.role.RoleType;

import java.util.Optional;

public interface RoleService {
    Optional<Role> findByName(RoleType roleUser);
}
