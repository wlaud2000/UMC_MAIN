package com.example.study.controller;

import com.example.study.dto.request.MissionRequestDTO;
import com.example.study.dto.response.MissionResponseDTO;
import com.example.study.service.command.MissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores/{storeId}/missions")
public class MissionController {

    private final MissionService missionService;

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
}


