package com.example.study.dto.request;

import com.example.study.validation.annotation.MissionNotChallenged;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

public class MissionRequestDTO {

    @Getter
    @Setter
    public class ChallengeMissionRequestDto {

        @NotNull(message = "사용자 ID는 필수입니다.")
        private Long userId;

        @MissionNotChallenged
        private Long missionId; // Custom Validator로 중복 도전 여부 확인
    }
}
