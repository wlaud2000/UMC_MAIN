package com.example.study.converter;

import com.example.study.dto.request.StortRequestDTO;
import com.example.study.entity.Region;
import com.example.study.entity.Store;

public class StoreConverter {

    public static Store toEntity(StortRequestDTO.AddStoreRequestDto request, Region region) {
        return Store.builder()
                .name(request.getName())
                .address(request.getAddress())
                .region(region)
                .score(0.0f)
                .build();
    }
}

