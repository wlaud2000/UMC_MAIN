package com.example.study.dto.response;

import lombok.Builder;
import lombok.Getter;

public class StoreResponseDTO {

    @Getter
    @Builder
    public static class AddStoreResponseDto {
        private Long storeId;
        private String message;
    }

}
