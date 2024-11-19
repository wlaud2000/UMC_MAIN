package com.example.study.controller;

import com.example.study.dto.request.StortRequestDTO;
import com.example.study.dto.response.StoreResponseDTO;
import com.example.study.service.command.StoreService;
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
    public ResponseEntity<StoreResponseDTO.AddStoreResponseDto> addStore(@RequestBody @Valid StortRequestDTO.AddStoreRequestDto request) {
        StoreResponseDTO.AddStoreResponseDto response = storeService.addStore(request);
        return ResponseEntity.ok(response);
    }
}

