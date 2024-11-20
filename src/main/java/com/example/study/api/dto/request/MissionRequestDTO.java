package com.example.study.api.dto.request;

import com.example.study.global.validation.annotation.MissionNotChallenged;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

public class MissionRequestDTO {

    @Getter
    @Setter
    public static class ChallengeMissionRequestDto {

        @NotNull(message = "사용자 ID는 필수입니다.")
        private Long userId;

        @MissionNotChallenged
        private Long missionId; // Custom Validator로 중복 도전 여부 확인
    }

    @Getter
    @Setter
    public static class AddMissionRequestDto {

        @NotBlank(message = "미션 제목은 필수입니다.")
        private String title;

        @NotBlank(message = "미션 설명은 필수입니다.")
        private String description;
    }
}
