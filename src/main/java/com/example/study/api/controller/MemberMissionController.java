package com.example.study.api.controller;

import com.example.study.api.dto.response.MemberMissionResponseDTO;
import com.example.study.api.service.query.MemberMissionQueryService;
import com.example.study.api.service.query.MissionQueryService;
import com.example.study.global.apiPayload.ApiResponse;
import com.example.study.global.validation.annotation.PageValidation;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/missions/members")
@RequiredArgsConstructor
public class MemberMissionController {

    private final MemberMissionQueryService userMissionQueryService;

    @Operation(summary = "내 진행 중인 미션 목록 조회", description = "현재 진행 중인 미션 목록을 페이지네이션으로 조회합니다.")
    @GetMapping("/in-progress")
    public ApiResponse<MemberMissionResponseDTO.MemberMissionListDTO> getInProgressMissions(
            @RequestParam Long userId,
            @RequestParam @Min(1) @PageValidation int page,
            @RequestParam @Min(1) @PageValidation int size) {

        MemberMissionResponseDTO.MemberMissionListDTO response = userMissionQueryService.getUserInProgressMissions(userId, page - 1, size);
        return ApiResponse.onSuccess(response);
    }
}
