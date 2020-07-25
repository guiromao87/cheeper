package com.study.cheeper.repository;

import com.study.cheeper.model.Cheep;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CheepRepository extends JpaRepository<Cheep, Long> {

    List<Cheep> findByAutorId(Integer id);

    long countByAutorId(Integer id);

}
