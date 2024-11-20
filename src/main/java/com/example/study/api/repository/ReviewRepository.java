package com.example.study.api.repository;

import com.example.study.api.entity.Review;
import com.example.study.api.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @EntityGraph(attributePaths = {"member"})
    @Query("SELECT r FROM Review r WHERE r.store.id = :storeId")
    Page<Review> findAllByStore(@Param("storeId") Long storeId, Pageable pageable);

    Page<Review> findByMemberId(Long memberId, Pageable pageable);
}
