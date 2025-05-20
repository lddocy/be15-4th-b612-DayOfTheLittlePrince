package littleprince.item.query.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface GetBadgeCommandMapper {

    void insertGetBadge(@Param("memberId") Long memberId, @Param("badgeId") Long badgeId);

    void clearSelectedBadge(@Param("memberId") Long memberId);

    void updateSelectedBadge(@Param("memberId") Long memberId, @Param("badgeId") Long badgeId);

    boolean hasBadge(@Param("memberId") Long memberId, @Param("badgeId") Long badgeId); // 🟢

    boolean existsSelectedBadge(@Param("memberId") Long memberId); // 🟢

    void selectLv0Badge(@Param("memberId") Long memberId);
}


