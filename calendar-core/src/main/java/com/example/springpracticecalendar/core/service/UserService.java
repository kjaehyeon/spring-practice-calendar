package com.example.springpracticecalendar.core.service;

import com.example.springpracticecalendar.core.domain.entity.User;
import com.example.springpracticecalendar.core.domain.entity.repository.UserRepository;
import com.example.springpracticecalendar.core.dto.UserCreateReq;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User create(UserCreateReq userCreateReq){
        userRepository.findByEmail(userCreateReq.getEmail())
                .ifPresent((user) ->{
                    throw new RuntimeException("user already exists!");
                });
        return userRepository.save(
                User.fromUserCreateReq(userCreateReq, passwordEncoder)
        );
    }

    @Transactional
    public Optional<User> findPwMatchUser(String email, String password){
        return userRepository.findByEmail(email)
                .map(user -> user.isMatch(passwordEncoder, password) ? user : null);
    }

    @Transactional
    public User findById(Long userId){
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("There is no user with this Id"));
    }
}
