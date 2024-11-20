package com.example.study.api.repository;

import com.example.study.api.entity.MemberMission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
    boolean existsByMissionId(Long missionId); // 이미 도전 중인 미션인지 확인
}
