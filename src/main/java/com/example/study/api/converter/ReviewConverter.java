package com.example.study.api.converter;

import com.example.study.api.dto.request.ReviewRequestDTO;
import com.example.study.api.dto.response.ReviewResponseDTO;
import com.example.study.api.entity.Member;
import com.example.study.api.entity.Review;
import com.example.study.api.entity.Store;

public class ReviewConverter {

    // DTO -> Review Entity 변환
    public static Review toEntity(ReviewRequestDTO.AddReviewRequestDto request, Store store, Member member) {
        return Review.builder()
                .title(request.getTitle())
                .score(request.getScore())
                .body(request.getBody())
                .store(store)
                .member(member)
                .build();
    }

    // Review Entity -> Response DTO 변환 (필요 시)
    public static ReviewResponseDTO.AddReviewResponseDto toResponseDto(Review review) {
        return ReviewResponseDTO.AddReviewResponseDto.builder()
                .reviewId(review.getId())
                .title(review.getTitle())
                .score(review.getScore())
                .body(review.getBody())
                .storeId(review.getStore().getId())
                .memberId(review.getMember().getId())
                .build();
    }
}
