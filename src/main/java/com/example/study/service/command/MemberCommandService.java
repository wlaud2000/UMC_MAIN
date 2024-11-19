package com.example.study.service.command;

import com.example.study.converter.MemberConverter;
import com.example.study.converter.MemberPreferConverter;
import com.example.study.dto.request.MemberRequestDTO;
import com.example.study.entity.FoodCategory;
import com.example.study.entity.Member;
import com.example.study.entity.MemberPrefer;
import com.example.study.global.apiPayload.handler.FoodCategoryHandler;
import com.example.study.global.apiPayload.status.ErrorStatus;
import com.example.study.repository.foodCategory.FoodCategoryRepository;
import com.example.study.repository.memberRepository.MemberRepository;
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
