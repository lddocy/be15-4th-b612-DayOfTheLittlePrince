package littleprince.plan.query.service;

import littleprince.plan.query.dto.response.LongListDTO;
import littleprince.plan.query.dto.response.LongListResponse;
import littleprince.plan.query.mapper.LongListQueryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LongListQueryService {

    private final LongListQueryMapper longListQueryMapper;
    /* 장기 플랜 목록 조회 */
    public LongListResponse getLongList(Long memberId, Date date) {

        List<LongListDTO> listDTO = longListQueryMapper.getLongList(memberId, date);

        return LongListResponse.builder()
                .longListDTOS(listDTO)
                .build();
    }
}
