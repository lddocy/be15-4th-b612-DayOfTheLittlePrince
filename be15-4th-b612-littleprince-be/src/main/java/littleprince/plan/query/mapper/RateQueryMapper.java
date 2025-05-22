package littleprince.plan.query.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;

@Mapper
public interface RateQueryMapper {
    int countAll(Long memberId);

    int countCompleted(Long memberId);

    int countMonthly(Long memberId, LocalDateTime startOfMonth, LocalDateTime endOfMonth);

    int countMonthlyCompleted(Long memberId, LocalDateTime startOfMonth, LocalDateTime endOfMonth);
}
