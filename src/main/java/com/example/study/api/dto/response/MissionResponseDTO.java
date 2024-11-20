package com.example.study.api.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

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

    @Builder
    @Getter
    public static class MissionListDTO {
        private List<MissionPreviewDTO> missionList;
        private Integer listSize;
        private Integer totalPage;
        private Long totalElements;
        private Boolean isFirst;
        private Boolean isLast;
    }

    @Builder
    @Getter
    public static class MissionPreviewDTO {
        private Long missionId;
        private String title;
        private String descriptionPreview;
    }

}
