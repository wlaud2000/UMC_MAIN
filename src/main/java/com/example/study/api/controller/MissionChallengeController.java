package com.example.study.api.controller;

import com.example.study.api.dto.request.MissionRequestDTO;
import com.example.study.api.dto.response.MissionResponseDTO;
import com.example.study.api.service.command.MissionChallengeService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions/{missionId}/challenges")
public class MissionChallengeController {

    private final MissionChallengeService missionChallengeService;

    @PostMapping
    @Operation(
            summary = "미션 도전 API",
            description = "특정 미션에 회원이 도전하도록 등록합니다."
    )
    public ResponseEntity<MissionResponseDTO.ChallengeMissionResponseDto> challengeMission(
            @RequestBody @Valid @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "미션 도전 요청 DTO"
            ) MissionRequestDTO.ChallengeMissionRequestDto request) {
        MissionResponseDTO.ChallengeMissionResponseDto response = missionChallengeService.challengeMission(request);
        return ResponseEntity.ok(response);
    }
}

