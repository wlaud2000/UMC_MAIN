package com.example.study.api.service.query;

import com.example.study.api.converter.ReviewConverter;
import com.example.study.api.dto.response.ReviewResponseDTO;
import com.example.study.api.entity.Review;
import com.example.study.api.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewQueryService {
    private final ReviewRepository reviewRepository;

    public ReviewResponseDTO.ReviewPreViewListDTO getMyReviews(Long userId, int page, int size) {

        int validPage = Math.max(page - 1, 0); // 요청한 page를 0-based로 변환
        Page<Review> reviewPage = reviewRepository.findByMemberId(userId, PageRequest.of(validPage, size)); // 사용자 리뷰 조회

        return ReviewConverter.toReviewPreviewListDTO(reviewPage);
    }
}
