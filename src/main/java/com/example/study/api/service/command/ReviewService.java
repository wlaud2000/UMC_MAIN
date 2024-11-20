package com.example.study.api.service.command;

import com.example.study.api.converter.ReviewConverter;
import com.example.study.api.dto.request.ReviewRequestDTO;
import com.example.study.api.dto.response.ReviewResponseDTO;
import com.example.study.api.entity.Member;
import com.example.study.api.entity.Review;
import com.example.study.api.entity.Store;
import com.example.study.api.repository.MemberRepository;
import com.example.study.api.repository.ReviewRepository;
import com.example.study.api.repository.storeRepository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;

    public ReviewResponseDTO.AddReviewResponseDto addReview(ReviewRequestDTO.AddReviewRequestDto request) {
        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new IllegalArgumentException("Store not found"));
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));

        Review review = ReviewConverter.toEntity(request, store, member);
        Review savedReview = reviewRepository.save(review);

        return ReviewConverter.toResponseDto(savedReview);
    }
}
