package com.study.cheeper.repository;

import com.study.cheeper.model.Cheep;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheepRepository extends JpaRepository<Cheep, Long> {
}
