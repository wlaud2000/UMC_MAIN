package com.example.study.api.service.command;

import com.example.study.api.converter.MissionConverter;
import com.example.study.api.dto.request.MissionRequestDTO;
import com.example.study.api.dto.response.MissionResponseDTO;
import com.example.study.api.entity.Mission;
import com.example.study.api.entity.Store;
import com.example.study.api.repository.MissionRepository;
import com.example.study.api.repository.storeRepository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;

    public MissionResponseDTO.AddMissionResponseDto addMission(Long storeId, MissionRequestDTO.AddMissionRequestDto request) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new IllegalArgumentException("Store not found"));
        Mission mission = MissionConverter.toEntity(request, store);
        Mission savedMission = missionRepository.save(mission);

        return MissionConverter.toResponseDto(savedMission);
    }
}

