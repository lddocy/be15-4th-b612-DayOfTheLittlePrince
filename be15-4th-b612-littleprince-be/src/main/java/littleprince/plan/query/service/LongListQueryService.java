package littleprince.plan.query.service;

import littleprince.plan.query.dto.response.LongDetailDTO;
import littleprince.plan.query.dto.response.LongDetailResponse;
import littleprince.plan.query.dto.response.LongListDTO;
import littleprince.plan.query.dto.response.LongListResponse;
import littleprince.plan.query.dto.response.LongPlanDTO;
import littleprince.plan.query.dto.response.LongPlanResponse;
import littleprince.plan.query.mapper.LongListQueryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LongListQueryService {

    private final LongListQueryMapper longListQueryMapper;

    /* 장기 플랜 전체 조회 */
    public LongPlanResponse getLongPlan(Long memberId) {

        List<LongPlanDTO> response = longListQueryMapper.getLongPlan(memberId);

        return LongPlanResponse.builder()
                .planDTOList(response)
                .build();
    }

    /* 장기 플랜 목록 조회 */
    public LongListResponse getLongList(Long memberId, Date date) {

        List<LongListDTO> listDTO = longListQueryMapper.getLongList(memberId, date);

        return LongListResponse.builder()
                .longListDTOS(listDTO)
                .build();
    }

    /* 장기 플랜 상세 조회 */
    public LongDetailResponse getLongDetail(Long memberId, Date date, Long projectId) {

        List<LongDetailDTO> detailDTO = longListQueryMapper.getLongDetailList(memberId, date, projectId);

        return LongDetailResponse.builder()
                .detailDTOS(detailDTO)
                .build();
    }
}
