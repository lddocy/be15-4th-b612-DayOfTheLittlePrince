package littleprince.member.query.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ExpHistoryQueryMapper {
    boolean existsTodayHistory(@Param("memberId") Long memberId);

    int getTaskExp(Long memberId);

    int getProjectExp(Long memberId, Long taskProjectId);

    Long getProjectIdExp(Long memberId, Long taskProjectId);
}
