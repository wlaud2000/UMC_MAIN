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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberCommandService {

    private final MemberRepository memberRepository;
    private final FoodCategoryRepository foodCategoryRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Member joinMember(MemberRequestDTO.JoinDto request) {
        System.out.println("Processing JoinMember: " + request);

        if (request.getPreferCategory() == null || request.getPreferCategory().isEmpty()) {
            throw new IllegalArgumentException("선호 카테고리는 필수입니다.");
        }

        Member newMember = MemberConverter.toMember(request);
        newMember.encodePassword(passwordEncoder.encode(request.getPassword()));
        System.out.println("New Member Created: " + newMember);

        List<FoodCategory> foodCategoryList = request.getPreferCategory().stream()
                .map(categoryId -> foodCategoryRepository.findById(categoryId)
                        .orElseThrow(() -> new FoodCategoryHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND)))
                .collect(Collectors.toList());
        System.out.println("Food Categories Fetched: " + foodCategoryList);

        List<MemberPrefer> memberPreferList = MemberPreferConverter.toMemberPreferList(foodCategoryList);

        memberPreferList.forEach(memberPrefer -> {
            memberPrefer.setMember(newMember);
            newMember.getPreferCategories().add(memberPrefer);
        });

        Member savedMember = memberRepository.save(newMember);
        System.out.println("Saved Member: " + savedMember);

        return savedMember;
    }
}
