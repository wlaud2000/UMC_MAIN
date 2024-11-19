package com.example.study.controller;

import com.example.study.dto.request.MissionRequestDTO;
import com.example.study.dto.response.MissionResponseDTO;
import com.example.study.service.command.MissionChallengeService;
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
    public ResponseEntity<MissionResponseDTO.ChallengeMissionResponseDto> challengeMission(
            @RequestBody @Valid MissionRequestDTO.ChallengeMissionRequestDto request) {
        MissionResponseDTO.ChallengeMissionResponseDto response = missionChallengeService.challengeMission(request);
        return ResponseEntity.ok(response);
    }
}
