package com.example.study.api.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class MissionResponseDTO {

    @Getter
    @Builder
    public static class ChallengeMissionResponseDto {
        private Long challengeId;
        private Long userId;
        private Long missionId;
        private String status;
        private LocalDateTime completedAt;
    }

    @Getter
    @Builder
    public static class AddMissionResponseDto {
        private Long missionId;
        private String title;
        private String description;
    }

}
