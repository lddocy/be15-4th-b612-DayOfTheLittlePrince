package littleprince.plan.query.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;

@Mapper
public interface RateQueryMapper {

    int countAll(@Param("memberId") Long memberId);

    int countCompleted(@Param("memberId") Long memberId);

    int countMonthly(
            @Param("memberId") Long memberId,
            @Param("startOfMonth") LocalDateTime startOfMonth,
            @Param("endOfMonth") LocalDateTime endOfMonth
    );

    int countMonthlyCompleted(
            @Param("memberId") Long memberId,
            @Param("startOfMonth") LocalDateTime startOfMonth,
            @Param("endOfMonth") LocalDateTime endOfMonth
    );
}
