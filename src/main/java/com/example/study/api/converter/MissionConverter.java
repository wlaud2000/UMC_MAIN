package com.example.study.api.converter;

import com.example.study.api.dto.request.MissionRequestDTO;
import com.example.study.api.dto.response.MissionResponseDTO;
import com.example.study.api.entity.Mission;
import com.example.study.api.entity.Store;

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
}

