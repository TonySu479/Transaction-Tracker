package com.example.transactiontracker.services.roleservice;

import com.example.transactiontracker.models.user.role.Role;
import com.example.transactiontracker.models.user.role.RoleType;
import com.example.transactiontracker.services.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Optional<Role> findByName(RoleType roleUser) {
        return roleRepository.findByName(roleUser);
    }
}
