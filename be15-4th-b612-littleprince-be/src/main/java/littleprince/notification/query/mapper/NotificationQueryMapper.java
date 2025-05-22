package littleprince.notification.query.mapper;

import littleprince.notification.query.dto.NotificationDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface NotificationQueryMapper {

    List<NotificationDto> findByMemberIdWithPaging(@Param("memberId") Long memberId,
                                                   @Param("offset") int offset,
                                                   @Param("limit") int limit);

    long countByMemberId(@Param("memberId") Long memberId);

    long countUnreadByMemberId(@Param("memberId") Long memberId);
}
