package com.study.cheeper.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends PagingAndSortingRepository<User, Integer> {
    boolean existsByProfileName(String profileName);

    Optional<User> findByProfileName(String profileName);

    @Query("select f.followed from Follower f where f.follower = ?1")
    Page<User> followed(User follower, Pageable pageable);

    @Query("select f.follower from Follower f where f.followed = ?1")
    Page<User> follower(User followed, Pageable pageable);


    @Transactional
    @Modifying
    @Query(value = "insert into relationship(follower_id, followed_id) values (:follower_id, :followed_id)", nativeQuery = true)
    void insert(@Param("follower_id") Integer followerId, @Param("followed_id") Integer followedId);

    @Transactional
    @Modifying
    @Query(value = "delete from relationship where follower_id = :follower_id and followed_id = :followed_id", nativeQuery = true)
    void remove(@Param("follower_id") Integer followerId, @Param("followed_id") Integer followedId);

    //trocar para jpql
    @Query(value = "select count(*) from relationship where follower_id = :follower_id and followed_id = :followed_id", nativeQuery = true)
    int isFollowing(@Param("follower_id") int current, @Param("followed_id")int profile);

    @Query(value = "select count(*) from relationship where follower_id = :follower_id", nativeQuery = true)
    int numberOfIfollow(@Param("follower_id") User follower);

    @Query(value = "select count(*) from relationship where followed_id = :followed_id", nativeQuery = true)
    int numberOfFollowsMe(@Param("followed_id") User followed);
}
