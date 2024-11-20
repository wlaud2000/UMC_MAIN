package com.example.study.api.controller;

import com.example.study.api.dto.request.ReviewRequestDTO;
import com.example.study.api.dto.response.ReviewResponseDTO;
import com.example.study.api.service.command.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores/{storeId}/reviews")
public class ReviewController {

    private final ReviewService reviewService;

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
}

