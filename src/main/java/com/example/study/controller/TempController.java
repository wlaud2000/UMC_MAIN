package com.example.study.controller;

import com.example.study.converter.TempConverter;
import com.example.study.dto.response.TempResponse;
import com.example.study.global.apiPayload.ApiResponse;
import com.example.study.service.query.TempQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/temp")
@RequiredArgsConstructor
public class TempController {
    private final TempQueryService tempQueryService;

    @GetMapping("/test")
    @Operation(
            summary = "테스트 API",
            description = "테스트용 임시 데이터를 반환합니다."
    )
    public ApiResponse<TempResponse.TempTestDTO> testAPI() {
        return ApiResponse.onSuccess(TempConverter.toTempTestDTO());
    }

    @GetMapping("/exception")
    @Operation(
            summary = "예외 테스트 API",
            description = "특정 플래그 값에 따라 예외를 발생시키는 테스트 API입니다."
    )
    public ApiResponse<TempResponse.TempExceptionDTO> exceptionAPI(
            @RequestParam @Parameter(description = "예외 발생 여부를 결정하는 플래그") Integer flag) {
        tempQueryService.CheckFlag(flag);
        return ApiResponse.onSuccess(TempConverter.toTempExceptionDTO(flag));
    }
}

