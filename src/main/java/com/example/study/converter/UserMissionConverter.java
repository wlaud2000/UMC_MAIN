package com.example.study.converter;

import com.example.study.dto.request.MissionRequestDTO;
import com.example.study.dto.response.MissionResponseDTO;
import com.example.study.entity.Member;
import com.example.study.entity.Mission;
import com.example.study.entity.UserMission;

public class UserMissionConverter {

    public static UserMission toEntity(MissionRequestDTO.ChallengeMissionRequestDto request, Member member, Mission mission) {
        return UserMission.builder()
                .member(member)
                .mission(mission)
                .status(UserMission.Status.IN_PROGRESS)
                .completedAt(null)
                .build();
    }

    public static MissionResponseDTO.ChallengeMissionResponseDto toResponseDto(UserMission userMission) {
        return MissionResponseDTO.ChallengeMissionResponseDto.builder()
                .challengeId(userMission.getId())
                .userId(userMission.getMember().getId())
                .missionId(userMission.getMission().getId())
                .status(userMission.getStatus().name())
                .completedAt(userMission.getCompletedAt())
                .build();
    }
}

