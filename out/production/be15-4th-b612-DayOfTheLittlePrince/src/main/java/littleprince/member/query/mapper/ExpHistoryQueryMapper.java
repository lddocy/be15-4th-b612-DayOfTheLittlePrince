package littleprince.member.query.mapper;

import littleprince.member.query.dto.GetExpHistoryDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ExpHistoryQueryMapper {
    boolean existsTodayHistory(@Param("memberId") Long memberId);

    /* 획득 경험치 조회 */
    List<GetExpHistoryDTO> getExpHistory(Long memberId);

    int getTaskExp(Long memberId);

    int getProjectExp(Long memberId, Long taskProjectId);

    Long getProjectIdExp(Long memberId, Long taskProjectId);
}
