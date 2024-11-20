package com.example.study.api.service.query;

import com.example.study.api.converter.MissionConverter;
import com.example.study.api.dto.response.MissionResponseDTO;
import com.example.study.api.entity.Mission;
import com.example.study.api.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissionQueryService {

    private final MissionRepository missionRepository;

    public MissionResponseDTO.MissionListDTO getMissionsByStoreId(Long storeId, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size); // 페이지 요청 객체 생성
        Page<Mission> missionPage = missionRepository.findByStoreId(storeId, pageRequest);

        return MissionConverter.toMissionListDTO(missionPage);
    }
}
