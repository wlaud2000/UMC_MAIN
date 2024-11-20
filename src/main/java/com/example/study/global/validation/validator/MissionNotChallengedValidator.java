package com.example.study.global.validation.validator;

import com.example.study.api.repository.MemberMissionRepository;
import com.example.study.global.validation.annotation.MissionNotChallenged;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MissionNotChallengedValidator implements ConstraintValidator<MissionNotChallenged, Long> {

    private final MemberMissionRepository memberMissionRepository;

    @Override
    public boolean isValid(Long missionId, ConstraintValidatorContext context) {
        return !memberMissionRepository.existsByMissionId(missionId); // 중복 도전 여부 확인
    }
}
