package littleprince.member.query.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ExpHistoryQueryMapper {
    boolean existsTodayHistory(@Param("memberId") Long memberId);
}
