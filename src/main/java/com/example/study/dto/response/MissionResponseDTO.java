package com.example.study.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class MissionResponseDTO {

    @Getter
    @Builder
    public class ChallengeMissionResponseDto {
        private Long challengeId;
        private Long userId;
        private Long missionId;
        private String status;
        private LocalDateTime completedAt;
    }
}
