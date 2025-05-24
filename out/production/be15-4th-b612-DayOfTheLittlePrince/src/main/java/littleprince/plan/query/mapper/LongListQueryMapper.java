package littleprince.plan.query.mapper;

import littleprince.plan.query.dto.response.LongDetailDTO;
import littleprince.plan.query.dto.response.LongListDTO;
import littleprince.plan.query.dto.response.LongPlanDTO;
import org.apache.ibatis.annotations.Mapper;

import java.sql.Date;
import java.util.List;

@Mapper
public interface LongListQueryMapper {

    /* 장기 플랜 전체 조회*/
    List<LongPlanDTO> getLongPlan(Long memberId);

    /* 장기 플랜 목록 조회 */
    List<LongListDTO> getLongList(Long memberId, Date date);

    /* 장기 플랜 상세 조회  */
    List<LongDetailDTO> getLongDetailList(Long memberId, Date date, Long projectId);
}
