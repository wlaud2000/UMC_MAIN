package com.example.study.validation.validator;

import com.example.study.repository.UserMissionRepository;
import com.example.study.validation.annotation.MissionNotChallenged;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MissionNotChallengedValidator implements ConstraintValidator<MissionNotChallenged, Long> {

    private final UserMissionRepository userMissionRepository;

    @Override
    public boolean isValid(Long missionId, ConstraintValidatorContext context) {
        return !userMissionRepository.existsByMissionId(missionId); // 중복 도전 여부 확인
    }
}
