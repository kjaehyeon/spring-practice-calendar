package com.example.springpracticecalendar.core.domain.entity.repository;

import com.example.springpracticecalendar.core.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);
    Optional<User> findById(Long userId);
}