package com.wanderhungerbuhler.microwh.repositories;

import com.wanderhungerbuhler.microwh.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);
}
