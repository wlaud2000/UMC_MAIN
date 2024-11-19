package com.example.study.converter;

import com.example.study.dto.request.MissionRequestDTO;
import com.example.study.dto.response.MissionResponseDTO;
import com.example.study.entity.Mission;
import com.example.study.entity.Store;

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

