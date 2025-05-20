package littleprince.item.query.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface GetBadgeCommandMapper {
    void insertGetBadge(@Param("memberId") Long memberId, @Param("badgeId") Long badgeId);
}

