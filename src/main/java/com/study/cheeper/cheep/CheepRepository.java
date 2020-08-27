package com.study.cheeper.cheep;

import com.study.cheeper.timeline.TimelineProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CheepRepository extends PagingAndSortingRepository<Cheep, Long> {
    
    Page<Cheep> findByProfileId(Integer id, Pageable pageable);

    @Query(value = "select u.name as name, u.email as email, c.id as cheepId, c.message as message, c.creation as creation from user u inner join cheep c on c.profile_id = u.id left join relationship r on r.followed_id = u.id where r.follower_id = :id UNION select u.name as name, u.email as email,c.id as cheepId, c.message as message, c.creation as creation from user u inner join cheep c on c.profile_id = u.id where u.id = :id", nativeQuery = true)
    List<TimelineProjection> allCheepsWhomIFollow(@Param("id") int id);
}
