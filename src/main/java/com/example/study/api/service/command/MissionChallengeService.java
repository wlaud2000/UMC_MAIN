package com.example.study.api.service.command;

import com.example.study.api.converter.MemberMissionConverter;
import com.example.study.api.dto.request.MissionRequestDTO;
import com.example.study.api.dto.response.MissionResponseDTO;
import com.example.study.api.entity.Member;
import com.example.study.api.entity.MemberMission;
import com.example.study.api.entity.Mission;
import com.example.study.api.repository.MissionRepository;
import com.example.study.api.repository.MemberMissionRepository;
import com.example.study.api.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissionChallengeService {

    private final MemberMissionRepository memberMissionRepository;
    private final MissionRepository missionRepository;
    private final MemberRepository memberRepository;

    public MissionResponseDTO.ChallengeMissionResponseDto challengeMission(MissionRequestDTO.ChallengeMissionRequestDto request) {
        Mission mission = missionRepository.findById(request.getMissionId())
                .orElseThrow(() -> new IllegalArgumentException("Mission not found"));
        Member member = memberRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        MemberMission memberMission = MemberMissionConverter.toEntity(request, member, mission);
        MemberMission savedMission = memberMissionRepository.save(memberMission);

        return MemberMissionConverter.toResponseDto(savedMission);
    }
}
