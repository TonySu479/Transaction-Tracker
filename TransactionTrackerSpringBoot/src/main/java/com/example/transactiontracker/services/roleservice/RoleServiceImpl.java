package com.example.transactiontracker.services.roleservice;

import com.example.transactiontracker.models.Role;
import com.example.transactiontracker.models.RoleType;
import com.example.transactiontracker.repositories.RoleRepository;
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
