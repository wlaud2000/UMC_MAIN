package com.example.study.api.service.command;

import com.example.study.api.converter.StoreConverter;
import com.example.study.api.dto.request.StortRequestDTO;
import com.example.study.api.dto.response.StoreResponseDTO;
import com.example.study.api.entity.Region;
import com.example.study.api.entity.Store;
import com.example.study.api.repository.RegionRepository;
import com.example.study.api.repository.storeRepository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;
    private final RegionRepository regionRepository;

    public StoreResponseDTO.AddStoreResponseDto addStore(StortRequestDTO.AddStoreRequestDto request) {
        Region region = regionRepository.findById(request.getRegionId())
                .orElseThrow(() -> new IllegalArgumentException("Region not found"));
        Store store = StoreConverter.toEntity(request, region);
        Store savedStore = storeRepository.save(store);

        return StoreResponseDTO.AddStoreResponseDto.builder()
                .storeId(savedStore.getId())
                .message("Store added successfully")
                .build();
    }
}

