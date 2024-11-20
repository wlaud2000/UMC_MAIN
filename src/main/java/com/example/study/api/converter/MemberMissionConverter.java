package com.example.study.api.converter;

import com.example.study.api.dto.request.MissionRequestDTO;
import com.example.study.api.dto.response.MemberMissionResponseDTO;
import com.example.study.api.dto.response.MissionResponseDTO;
import com.example.study.api.entity.Member;
import com.example.study.api.entity.MemberMission;
import com.example.study.api.entity.Mission;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class MemberMissionConverter {

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

    public static MemberMissionResponseDTO.MemberMissionListDTO toUserMissionListDTO(Page<MemberMission> memberMissionPage) {
        return MemberMissionResponseDTO.MemberMissionListDTO.builder()
                .missionList(toUserMissionPreviewList(memberMissionPage.getContent()))
                .listSize(memberMissionPage.getNumberOfElements())
                .totalPage(memberMissionPage.getTotalPages())
                .totalElements(memberMissionPage.getTotalElements())
                .isFirst(memberMissionPage.isFirst())
                .isLast(memberMissionPage.isLast())
                .build();
    }

    public static List<MemberMissionResponseDTO.MemberMissionPreviewDTO> toUserMissionPreviewList(List<MemberMission> userMissions) {
        return userMissions.stream()
                .map(mission -> MemberMissionResponseDTO.MemberMissionPreviewDTO.builder()
                        .missionId(mission.getMission().getId())
                        .title(mission.getMission().getTitle())
                        .descriptionPreview(mission.getMission().getDescription())
                        .status(mission.getStatus().name())
                        .build())
                .collect(Collectors.toList());
    }
}

