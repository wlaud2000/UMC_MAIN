package com.example.study.api.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class MemberMissionResponseDTO {

    @Builder
    @Getter
    public static class MemberMissionListDTO {
        private List<MemberMissionPreviewDTO> missionList;
        private Integer listSize;
        private Integer totalPage;
        private Long totalElements;
        private Boolean isFirst;
        private Boolean isLast;
    }

    @Builder
    @Getter
    public static class MemberMissionPreviewDTO {
        private Long missionId;
        private String title;
        private String descriptionPreview;
        private String status;
    }
}
