package littleprince.item.query.mapper;

import littleprince.item.query.dto.response.BadgeDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GetBadgeMapper {
    List<BadgeDto> findBadgesByMemberId(@Param("memberId") Long memberId);
}

