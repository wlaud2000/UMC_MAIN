package com.example.study.api.controller;

import com.example.study.api.dto.request.MissionRequestDTO;
import com.example.study.api.dto.response.MissionResponseDTO;
import com.example.study.api.service.command.MissionService;
import com.example.study.api.service.query.MissionQueryService;
import com.example.study.global.validation.annotation.PageValidation;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores/{storeId}/missions")
public class MissionController {

    private final MissionService missionService;
    private final MissionQueryService missionQueryService;

    @Operation(
            summary = "가게에 새로운 미션 추가",
            description = "특정 가게에 새로운 미션을 추가하는 API입니다. `storeId`를 경로 변수로 전달하고, 미션 정보를 요청 본문에 포함하여 요청합니다."
    )
    @PostMapping
    public ResponseEntity<MissionResponseDTO.AddMissionResponseDto> addMission(
            @PathVariable Long storeId,
            @RequestBody @Valid MissionRequestDTO.AddMissionRequestDto request) {
        MissionResponseDTO.AddMissionResponseDto response = missionService.addMission(storeId, request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "특정 가게의 미션 목록 조회", description = "특정 가게의 미션을 페이지네이션 형태로 조회합니다.")
    @GetMapping
    public ResponseEntity<MissionResponseDTO.MissionListDTO> getMissionsByStoreId(
            @PathVariable Long storeId,
            @RequestParam @Min(1) @PageValidation int page,
            @RequestParam @Min(1) @PageValidation int size) {

        MissionResponseDTO.MissionListDTO response = missionQueryService.getMissionsByStoreId(storeId, page - 1, size);
        return ResponseEntity.ok(response);
    }
}


