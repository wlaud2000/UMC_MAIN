package com.example.study.service.command;

import com.example.study.converter.UserMissionConverter;
import com.example.study.dto.request.MissionRequestDTO;
import com.example.study.dto.response.MissionResponseDTO;
import com.example.study.entity.Member;
import com.example.study.entity.Mission;
import com.example.study.entity.UserMission;
import com.example.study.repository.MissionRepository;
import com.example.study.repository.UserMissionRepository;
import com.example.study.repository.memberRepository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissionChallengeService {

    private final UserMissionRepository userMissionRepository;
    private final MissionRepository missionRepository;
    private final MemberRepository memberRepository;

    public MissionResponseDTO.ChallengeMissionResponseDto challengeMission(MissionRequestDTO.ChallengeMissionRequestDto request) {
        Mission mission = missionRepository.findById(request.getMissionId())
                .orElseThrow(() -> new IllegalArgumentException("Mission not found"));
        Member member = memberRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        UserMission userMission = UserMissionConverter.toEntity(request, member, mission);
        UserMission savedMission = userMissionRepository.save(userMission);

        return UserMissionConverter.toResponseDto(savedMission);
    }
}
