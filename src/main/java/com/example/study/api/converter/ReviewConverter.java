package com.example.study.api.converter;

import com.example.study.api.dto.request.ReviewRequestDTO;
import com.example.study.api.dto.response.ReviewResponseDTO;
import com.example.study.api.entity.Member;
import com.example.study.api.entity.Review;
import com.example.study.api.entity.Store;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

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

    public static List<ReviewResponseDTO.ReviewPreViewDTO> toReviewPreviewList(List<Review> reviews) {
        return reviews.stream()
                .map(review -> ReviewResponseDTO.ReviewPreViewDTO.builder()
                        .reviewId(review.getId())
                        .title(review.getTitle())
                        .score(review.getScore())
                        .bodyPreview(review.getBody()) // 본문 프리뷰
                        .build())
                .collect(Collectors.toList());
    }

    // 페이지 전체를 변환
    public static ReviewResponseDTO.ReviewPreViewListDTO toReviewPreviewListDTO(Page<Review> reviewPage) {
        return ReviewResponseDTO.ReviewPreViewListDTO.builder()
                .reviewList(toReviewPreviewList(reviewPage.getContent())) // 리뷰 리스트 변환
                .listSize(reviewPage.getContent().size()) // 현재 페이지의 리뷰 개수
                .totalPage(reviewPage.getTotalPages())    // 전체 페이지 수
                .totalElements(reviewPage.getTotalElements()) // 전체 리뷰 수
                .isFirst(reviewPage.isFirst())            // 첫 페이지 여부
                .isLast(reviewPage.isLast())              // 마지막 페이지 여부
                .build();
    }
}
