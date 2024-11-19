package com.example.study.repository;

import com.example.study.entity.UserMission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {
    boolean existsByMissionId(Long missionId); // 이미 도전 중인 미션인지 확인
}
