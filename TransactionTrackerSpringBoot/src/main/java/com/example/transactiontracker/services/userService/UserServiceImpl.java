package com.example.transactiontracker.services.userService;

import com.example.transactiontracker.models.Role;
import com.example.transactiontracker.models.RoleType;
import com.example.transactiontracker.models.User;
import com.example.transactiontracker.repositories.UserRepository;
import com.example.transactiontracker.services.roleService.RoleService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    final private PasswordEncoder encoder;

    public UserServiceImpl(UserRepository userRepository, RoleService roleService, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.encoder = encoder;
    }

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
        createUser("basic", "password", RoleType.ROLE_USER);
    }

    @Override
    public void createUser(String name, String password, RoleType roleType) {
        User user = new User(name, name + "@gmail.com", encoder.encode(password));
        HashSet<Role> roles = new HashSet<>();
        Role userRole = roleService.findByName(roleType)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(userRole);
        user.setRoles(roles);
        userRepository.save(user);
    }
}
