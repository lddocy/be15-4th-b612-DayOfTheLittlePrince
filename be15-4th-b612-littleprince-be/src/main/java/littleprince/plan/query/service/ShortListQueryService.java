package littleprince.plan.query.service;

import littleprince.plan.query.dto.ShortListDTO;
import littleprince.plan.query.dto.response.ShortListResponse;
import littleprince.plan.query.mapper.ShortListQueryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
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

}
