package com.example.study.api.repository;

import com.example.study.api.entity.MemberMission;
import com.example.study.api.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
    boolean existsByMissionId(Long missionId); // 이미 도전 중인 미션인지 확인

    Page<MemberMission> findByMemberIdAndStatus(Long userId, MemberMission.Status status, Pageable pageable);
}
