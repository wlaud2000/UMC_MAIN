package com.example.study.controller;

import com.example.study.dto.request.ReviewRequestDTO;
import com.example.study.dto.response.ReviewResponseDTO;
import com.example.study.service.command.ReviewService;
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
    public ResponseEntity<ReviewResponseDTO.AddReviewResponseDto> addReview(
            @PathVariable Long storeId,
            @RequestBody @Valid ReviewRequestDTO.AddReviewRequestDto request) {
        ReviewResponseDTO.AddReviewResponseDto response = reviewService.addReview(request);
        return ResponseEntity.ok(response);
    }
}
