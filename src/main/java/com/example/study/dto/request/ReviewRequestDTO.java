package com.example.study.dto.request;

import com.example.study.validation.annotation.ExistStore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

public class ReviewRequestDTO {

    @Getter
    @Setter
    public static class AddReviewRequestDto {

        @NotBlank(message = "리뷰 제목은 필수입니다.")
        private String title;

        @NotNull(message = "리뷰 점수는 필수입니다.")
        private Float score;

        @NotBlank(message = "리뷰 본문은 필수입니다.")
        private String body;

        @NotNull(message = "회원 ID는 필수입니다.")
        private Long memberId;

        @ExistStore
        private Long storeId;
    }
}
