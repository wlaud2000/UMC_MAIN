package com.example.study.api.service.command;

import com.example.study.api.converter.MemberConverter;
import com.example.study.api.converter.MemberPreferConverter;
import com.example.study.api.dto.request.MemberRequestDTO;
import com.example.study.api.entity.FoodCategory;
import com.example.study.api.entity.Member;
import com.example.study.api.entity.MemberPrefer;
import com.example.study.global.apiPayload.handler.FoodCategoryHandler;
import com.example.study.global.apiPayload.status.ErrorStatus;
import com.example.study.api.repository.FoodCategoryRepository;
import com.example.study.api.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberCommandService {

    private final MemberRepository memberRepository;

    private final FoodCategoryRepository foodCategoryRepository;

    @Transactional
    public Member joinMember(MemberRequestDTO.JoinDto request) {

        Member newMember = MemberConverter.toMember(request);
        List<FoodCategory> foodCategoryList = request.getPreferCategory().stream()
                .map(category -> {
                    return foodCategoryRepository.findById(category).orElseThrow(() -> new FoodCategoryHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND));
                }).collect(Collectors.toList());

        List<MemberPrefer> memberPreferList = MemberPreferConverter.toMemberPreferList(foodCategoryList);

        memberPreferList.forEach(memberPrefer -> {memberPrefer.setMember(newMember);});

        return memberRepository.save(newMember);
    }
}
