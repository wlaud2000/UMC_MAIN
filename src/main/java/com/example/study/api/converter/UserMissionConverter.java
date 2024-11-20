package com.example.study.api.converter;

import com.example.study.api.dto.request.MissionRequestDTO;
import com.example.study.api.dto.response.MissionResponseDTO;
import com.example.study.api.entity.Member;
import com.example.study.api.entity.MemberMission;
import com.example.study.api.entity.Mission;

public class UserMissionConverter {

    public static MemberMission toEntity(MissionRequestDTO.ChallengeMissionRequestDto request, Member member, Mission mission) {
        return MemberMission.builder()
                .member(member)
                .mission(mission)
                .status(MemberMission.Status.IN_PROGRESS)
                .completedAt(null)
                .build();
    }

    public static MissionResponseDTO.ChallengeMissionResponseDto toResponseDto(MemberMission memberMission) {
        return MissionResponseDTO.ChallengeMissionResponseDto.builder()
                .challengeId(memberMission.getId())
                .userId(memberMission.getMember().getId())
                .missionId(memberMission.getMission().getId())
                .status(memberMission.getStatus().name())
                .completedAt(memberMission.getCompletedAt())
                .build();
    }
}

