package com.example.study.api.service.query;

import com.example.study.api.converter.MemberMissionConverter;
import com.example.study.api.dto.response.MemberMissionResponseDTO;
import com.example.study.api.entity.MemberMission;
import com.example.study.api.entity.Mission;
import com.example.study.api.repository.MemberMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberMissionQueryService {

    private final MemberMissionRepository memberMissionRepository;

    public MemberMissionResponseDTO.MemberMissionListDTO getUserInProgressMissions(Long userId, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<MemberMission> userMissionPage =  memberMissionRepository.findByMemberIdAndStatus(userId, MemberMission.Status.IN_PROGRESS, pageRequest);

        return MemberMissionConverter.toUserMissionListDTO(userMissionPage);
    }
}
