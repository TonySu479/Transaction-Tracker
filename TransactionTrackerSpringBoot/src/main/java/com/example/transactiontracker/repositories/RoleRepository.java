package com.example.transactiontracker.repositories;

import com.example.transactiontracker.models.ERole;
import com.example.transactiontracker.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}