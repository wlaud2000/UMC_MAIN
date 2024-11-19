package com.example.study.controller;

import com.example.study.dto.request.StortRequestDTO;
import com.example.study.dto.response.StoreResponseDTO;
import com.example.study.service.command.StoreService;
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
@RequestMapping("/stores")
public class StoreController {

    private final StoreService storeService;

    @PostMapping
    @Operation(
            summary = "가게 추가 API",
            description = "새로운 가게를 데이터베이스에 추가합니다."
    )
    public ResponseEntity<StoreResponseDTO.AddStoreResponseDto> addStore(
            @RequestBody @Valid @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "가게 추가 요청 DTO"
            ) StortRequestDTO.AddStoreRequestDto request) {
        StoreResponseDTO.AddStoreResponseDto response = storeService.addStore(request);
        return ResponseEntity.ok(response);
    }
}


