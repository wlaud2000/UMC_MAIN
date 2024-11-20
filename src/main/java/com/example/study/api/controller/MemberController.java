package com.example.study.api.controller;

import com.example.study.api.converter.MemberConverter;
import com.example.study.api.dto.request.MemberRequestDTO;
import com.example.study.api.dto.response.MemberResponseDTO;
import com.example.study.api.entity.Member;
import com.example.study.global.apiPayload.ApiResponse;
import com.example.study.api.service.command.MemberCommandService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberCommandService memberCommandService;

    @PostMapping("/")
    @Operation(
            summary = "회원 가입 API",
            description = "새로운 사용자를 생성하고 데이터베이스에 저장합니다."
    )
    public ApiResponse<MemberResponseDTO.JoinResultDTO> join(
            @RequestBody @Valid @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "회원 가입 요청 DTO"
            ) MemberRequestDTO.JoinDto request) {
        Member member = memberCommandService.joinMember(request);
        return ApiResponse.onSuccess(MemberConverter.toJoinResultDTO(member));
    }
}

