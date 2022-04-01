package com.example.transactiontracker.repositories;

import com.example.transactiontracker.models.user.role.RoleType;
import com.example.transactiontracker.models.user.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleType name);
}