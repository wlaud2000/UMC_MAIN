package com.example.study.dto.response;

import lombok.Builder;
import lombok.Getter;

public class StoreResponseDTO {

    @Getter
    @Builder
    public class AddStoreResponseDto {
        private Long storeId;
        private String message;
    }

}
