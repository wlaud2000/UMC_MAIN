package com.example.study.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

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

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewPreViewListDTO {
        private List<ReviewPreViewDTO> reviewList; // 리뷰 목록
        private Integer listSize;                  // 현재 페이지의 리뷰 수
        private Integer totalPage;                 // 전체 페이지 수
        private Long totalElements;                // 전체 리뷰 수
        private Boolean isFirst;                   // 첫 페이지 여부
        private Boolean isLast;                    // 마지막 페이지 여부
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewPreViewDTO {
        private Long reviewId;
        private String title;
        private Float score;
        private String bodyPreview;
    }
}
