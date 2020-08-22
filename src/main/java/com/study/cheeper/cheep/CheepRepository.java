package com.study.cheeper.cheep;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CheepRepository extends PagingAndSortingRepository<Cheep, Long> {
    
    Page<Cheep> findByProfileId(Integer id, Pageable pageable);
}
