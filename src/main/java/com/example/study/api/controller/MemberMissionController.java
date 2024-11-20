package com.example.study.api.controller;

import com.example.study.api.dto.response.MemberMissionResponseDTO;
import com.example.study.api.service.command.MemberMissionCommandService;
import com.example.study.api.service.query.MemberMissionQueryService;
import com.example.study.api.service.query.MissionQueryService;
import com.example.study.global.apiPayload.ApiResponse;
import com.example.study.global.validation.annotation.PageValidation;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/missions/members")
@RequiredArgsConstructor
public class MemberMissionController {

    private final MemberMissionQueryService userMissionQueryService;
    private final MemberMissionCommandService memberMissionCommandService;

    @Operation(summary = "내 진행 중인 미션 목록 조회", description = "현재 진행 중인 미션 목록을 페이지네이션으로 조회합니다.")
    @GetMapping("/in-progress")
    public ApiResponse<MemberMissionResponseDTO.MemberMissionListDTO> getInProgressMissions(
            @RequestParam Long userId,
            @RequestParam @Min(1) @PageValidation int page,
            @RequestParam @Min(1) @PageValidation int size) {

        MemberMissionResponseDTO.MemberMissionListDTO response = userMissionQueryService.getUserInProgressMissions(userId, page - 1, size);
        return ApiResponse.onSuccess(response);
    }

    @Operation(summary = "진행 중인 미션 완료 처리", description = "진행 중인 미션의 상태를 완료로 변경합니다.")
    @PatchMapping("/{memberMissionId}/complete")
    public ApiResponse<String> completeMission(@PathVariable Long memberMissionId) {
        memberMissionCommandService.completeMission(memberMissionId);
        return ApiResponse.onSuccess("진행 중인 미션의 상태를 완료로 변경하였습니다.");
    }
}
