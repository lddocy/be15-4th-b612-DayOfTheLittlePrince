package littleprince.notification.command.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface NotificationCommandMapper {

    int updateIsRead(@Param("memberId") Long memberId,
                     @Param("notificationId") Long notificationId);

    Boolean isAlreadyRead(@Param("memberId") Long memberId,
                          @Param("notificationId") Long notificationId);
}

