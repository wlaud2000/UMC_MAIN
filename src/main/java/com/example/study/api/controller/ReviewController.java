package com.example.study.api.controller;

import com.example.study.api.dto.request.ReviewRequestDTO;
import com.example.study.api.dto.response.ReviewResponseDTO;
import com.example.study.api.service.command.ReviewService;
import com.example.study.api.service.query.ReviewQueryService;
import com.example.study.global.apiPayload.ApiResponse;
import com.example.study.global.validation.annotation.PageValidation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews/stores/{storeId}")
public class ReviewController {

    private final ReviewService reviewService;
    private final ReviewQueryService reviewQueryService;

    @PostMapping
    @Operation(
            summary = "리뷰 추가 API",
            description = "특정 가게에 대한 리뷰를 추가합니다."
    )
    public ResponseEntity<ReviewResponseDTO.AddReviewResponseDto> addReview(
            @PathVariable @Parameter(description = "리뷰를 추가할 가게의 ID") Long storeId,
            @RequestBody @Valid @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "리뷰 추가 요청 DTO"
            ) ReviewRequestDTO.AddReviewRequestDto request) {
        ReviewResponseDTO.AddReviewResponseDto response = reviewService.addReview(request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "내가 작성한 리뷰 목록 조회", description = "로그인한 사용자가 작성한 리뷰 목록을 조회합니다.")
    @GetMapping("/my-reviews")
    public ApiResponse<ReviewResponseDTO.ReviewPreViewListDTO> getMyReviews(
            @RequestParam Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        ReviewResponseDTO.ReviewPreViewListDTO response = reviewQueryService.getMyReviews(userId, page, size);
        return ApiResponse.onSuccess(response);
    }
}

