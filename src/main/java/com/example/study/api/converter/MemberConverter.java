package com.example.study.api.converter;

import com.example.study.api.dto.request.MemberRequestDTO;
import com.example.study.api.dto.response.MemberResponseDTO;
import com.example.study.api.entity.Member;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class MemberConverter {

    public static MemberResponseDTO.JoinResultDTO toJoinResultDTO(Member member){
        return MemberResponseDTO.JoinResultDTO.builder()
                .memberId(member.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Member toMember(MemberRequestDTO.JoinDto request){

        Member.Gender gender = null;

        switch (request.getGender()){
            case 1:
                gender = Member.Gender.MALE;
                break;
            case 2:
                gender = Member.Gender.FEMALE;
                break;
            case 3:
                gender = Member.Gender.NULL;
                break;
        }

        return Member.builder()
                .address(request.getAddress())
                .gender(gender)
                .nickname(request.getName())
                .preferCategories(new ArrayList<>())
                .build();
    }
}
