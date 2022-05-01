package com.example.transactiontracker.services.userservice;

import com.example.transactiontracker.models.user.role.Role;
import com.example.transactiontracker.models.user.role.RoleType;
import com.example.transactiontracker.models.user.User;
import com.example.transactiontracker.services.repositories.UserRepository;
import com.example.transactiontracker.services.roleservice.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder encoder;

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void createInitialUsers() {
        createUser("admin", "password", RoleType.ROLE_ADMIN);
        createUser("employee", "password", RoleType.ROLE_USER);
    }

    @Override
    public void createUser(String name, String password, RoleType roleType) {
        User user = new User(name, name + "@email.com", encoder.encode(password));
        HashSet<Role> roles = new HashSet<>();
        Role userRole = roleService.findByName(roleType)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(userRole);
        user.setRoles(roles);
        userRepository.save(user);
    }
}
