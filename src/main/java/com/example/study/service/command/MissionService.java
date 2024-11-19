package com.example.study.service.command;

import com.example.study.converter.MissionConverter;
import com.example.study.dto.request.MissionRequestDTO;
import com.example.study.dto.response.MissionResponseDTO;
import com.example.study.entity.Mission;
import com.example.study.entity.Store;
import com.example.study.repository.MissionRepository;
import com.example.study.repository.storeRepository.StoreRepository;
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

