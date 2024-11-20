package com.example.study.api.service.command;

import com.example.study.api.entity.MemberMission;
import com.example.study.api.entity.Mission;
import com.example.study.api.repository.MemberMissionRepository;
import com.example.study.api.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MemberMissionCommandService {

    private final MemberMissionRepository memberMissionRepository;

    public void completeMission(Long userMissionId) {
        MemberMission memberMission = memberMissionRepository.findById(userMissionId)
                .orElseThrow(() -> new IllegalArgumentException("User mission not found"));

        if (memberMission.getStatus() != MemberMission.Status.IN_PROGRESS) {
            throw new IllegalStateException("Mission is not in progress");
        }

        memberMission.setStatus(MemberMission.Status.SUCCESS);
        memberMission.setCompletedAt(LocalDateTime.now());
        memberMissionRepository.save(memberMission);
    }
}
