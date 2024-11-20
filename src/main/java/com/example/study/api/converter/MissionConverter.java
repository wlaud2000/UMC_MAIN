package com.example.study.api.converter;

import com.example.study.api.dto.request.MissionRequestDTO;
import com.example.study.api.dto.response.MissionResponseDTO;
import com.example.study.api.entity.Mission;
import com.example.study.api.entity.Store;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class MissionConverter {

    public static Mission toEntity(MissionRequestDTO.AddMissionRequestDto request, Store store) {
        return Mission.builder()
                .store(store)
                .title(request.getTitle())
                .description(request.getDescription())
                .build();
    }

    public static MissionResponseDTO.AddMissionResponseDto toResponseDto(Mission mission) {
        return MissionResponseDTO.AddMissionResponseDto.builder()
                .missionId(mission.getId())
                .title(mission.getTitle())
                .description(mission.getDescription())
                .build();
    }

    public static MissionResponseDTO.MissionListDTO toMissionListDTO(Page<Mission> missionPage) {
        return MissionResponseDTO.MissionListDTO.builder()
                .missionList(toMissionPreviewList(missionPage.getContent()))
                .listSize(missionPage.getNumberOfElements())
                .totalPage(missionPage.getTotalPages())
                .totalElements(missionPage.getTotalElements())
                .isFirst(missionPage.isFirst())
                .isLast(missionPage.isLast())
                .build();
    }

    public static List<MissionResponseDTO.MissionPreviewDTO> toMissionPreviewList(List<Mission> missions) {
        return missions.stream()
                .map(mission -> MissionResponseDTO.MissionPreviewDTO.builder()
                        .missionId(mission.getId())
                        .title(mission.getTitle())
                        .descriptionPreview(
                                mission.getDescription().substring(0, Math.min(50, mission.getDescription().length())) + "..."
                        )
                        .build())
                .collect(Collectors.toList());
    }
}

