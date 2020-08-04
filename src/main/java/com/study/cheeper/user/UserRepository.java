package com.study.cheeper.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);

    boolean existsByProfileName(String profileName);

    Optional<User> findByProfileName(String profileName);
}
