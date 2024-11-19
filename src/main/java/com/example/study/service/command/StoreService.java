package com.example.study.service.command;

import com.example.study.converter.StoreConverter;
import com.example.study.dto.request.StortRequestDTO;
import com.example.study.dto.response.StoreResponseDTO;
import com.example.study.entity.Region;
import com.example.study.entity.Store;
import com.example.study.repository.RegionRepository;
import com.example.study.repository.storeRepository.StoreRepository;
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

