package com.example.study.dto.response;

import lombok.Builder;
import lombok.Getter;

public class ReviewResponseDTO {

    @Getter
    @Builder
    public static class AddReviewResponseDto {
        private Long reviewId;
        private String title;
        private Float score;
        private String body;
        private Long memberId;
        private Long storeId;
    }
}
