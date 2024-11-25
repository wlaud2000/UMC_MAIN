package com.example.study.api.converter;

import com.example.study.api.dto.request.MemberRequestDTO;
import com.example.study.api.dto.response.MemberResponseDTO;
import com.example.study.api.entity.Member;
import com.example.study.api.enums.Gender;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class MemberConverter {

    public static MemberResponseDTO.JoinResultDTO toJoinResultDTO(Member member){
        return MemberResponseDTO.JoinResultDTO.builder()
                .memberId(member.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Member toMember(MemberRequestDTO.JoinDto request) {
        Gender gender = null;
        switch (request.getGender()) {
            case 1: gender = Gender.MALE; break;
            case 2: gender = Gender.FEMALE; break;
            case 3: gender = Gender.NONE; break;
            default: throw new IllegalArgumentException("Invalid gender value");
        }

        return Member.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .address(request.getAddress())
                .gender(gender)
                .nickname(request.getName())
                .birthDate(LocalDate.of(request.getBirthYear(), request.getBirthMonth(), request.getBirthDay())) // 추가된 코드
                .role(request.getRole())
                .preferCategories(new ArrayList<>())
                .build();
    }

}
