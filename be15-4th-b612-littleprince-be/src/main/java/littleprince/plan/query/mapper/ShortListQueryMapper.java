package littleprince.plan.query.mapper;

import littleprince.plan.query.dto.response.AiShortPlanDTO;
import littleprince.plan.query.dto.response.ShortListDTO;
import littleprince.plan.query.dto.response.ShortListsAllDTO;
import littleprince.plan.query.dto.response.ShortPlanDateDTO;
import org.apache.ibatis.annotations.Mapper;

import java.sql.Date;
import java.util.List;

@Mapper
public interface ShortListQueryMapper {

    /* 단기 리스트 목록 조회 */
    List<ShortListDTO> getShortList(Long memberId, Date date);

    /* 단기 리스트 전체 조회 */
    List<ShortListsAllDTO> getShortListsAll(Long memberId);

    /* 단기 플랜 여부 조회 */
    List<ShortPlanDateDTO> getShortDates(Long memberId);

    List<AiShortPlanDTO> getShortPlanByMemberId(Long memberId);

    long countUncheckedTasks(Long memberId, Date date);
}
