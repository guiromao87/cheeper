package com.study.cheeper.repository;

import com.study.cheeper.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);

    boolean existsByProfileName(String profileName);
}
