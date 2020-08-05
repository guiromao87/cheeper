package com.study.cheeper.cheep;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CheepRepository extends JpaRepository<Cheep, Long> {
    
    List<Cheep> findByProfileId(Integer id);
}
