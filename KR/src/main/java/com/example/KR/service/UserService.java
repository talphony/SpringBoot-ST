package com.example.KR.service;
import com.example.KR.models.User;
import com.example.KR.models.enums.Role;
import com.example.KR.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserService {
    private final UserRepository userRep;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public boolean createUsers(User user) {
        if (userRep.findByUsername(user.getUsername()).isPresent() ||
                userRep.findByEmail(user.getEmail()) != null) {
            return false;
        }

        user.setActive(true);
        user.getRole().add(Role.ROLE_USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRep.save(user);
        log.info("Пользователь сохранен: {}", user.getUsername());
        return true;
    }
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    public Optional<User> findById(Long userId) {
        return userRepository.findById(userId);
    }

}