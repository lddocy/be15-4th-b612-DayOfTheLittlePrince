package littleprince.item.query.mapper;

import littleprince.item.query.dto.response.BadgeDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GetBadgeQueryMapper {
    List<BadgeDto> findBadgesByMemberId(@Param("memberId") Long memberId);

    Long findBadgeIdByLevel(int level); // badge 테이블에서 level 기준 badge_id 조회
}

