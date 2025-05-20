package littleprince.plan.query.service;

import littleprince.plan.query.dto.response.ShortListDTO;
import littleprince.plan.query.dto.response.ShortListResponse;
import littleprince.plan.query.dto.response.ShortPlanDateDTO;
import littleprince.plan.query.dto.response.ShortPlanDateResponse;
import littleprince.plan.query.mapper.ShortListQueryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShortListQueryService {

    private final ShortListQueryMapper shortListQueryMapper;

    /* 단기 리스트 조회 */
    public ShortListResponse getShortList(Long memberId, Date date) {
        List<ShortListDTO> listDTO = shortListQueryMapper.getShortList(memberId, date);

        return ShortListResponse.builder()
                .shortList(listDTO)
                .build();
    }

    /* 단기플랜 여부 조회 */
    public ShortPlanDateResponse getShortDates(Long memberId) {
        List<ShortPlanDateDTO> planDateDTO = shortListQueryMapper.getShortDates(memberId);

        return ShortPlanDateResponse.builder()
                .planDateDTO(planDateDTO)
                .build();
    }
}
