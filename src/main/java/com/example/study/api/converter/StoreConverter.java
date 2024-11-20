package com.example.study.api.converter;

import com.example.study.api.dto.request.StortRequestDTO;
import com.example.study.api.entity.Region;
import com.example.study.api.entity.Store;

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

