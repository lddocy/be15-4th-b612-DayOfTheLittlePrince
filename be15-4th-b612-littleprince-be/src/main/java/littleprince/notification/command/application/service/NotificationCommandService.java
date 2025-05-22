package littleprince.notification.command.application.service;

import littleprince.common.exception.BusinessException;
import littleprince.notification.command.mapper.NotificationCommandMapper;
import littleprince.notification.exception.NotificationErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationCommandService {

    private final NotificationCommandMapper notificationCommandMapper;

    public void markAsRead(Long memberId, Long notificationId) {
        Boolean alreadyRead = notificationCommandMapper.isAlreadyRead(memberId, notificationId);

        if (alreadyRead == null) {
            throw new BusinessException(NotificationErrorCode.NOTIFICATION_NOT_FOUND);
        }

        if (alreadyRead) {
            throw new BusinessException(NotificationErrorCode.ALREADY_READ);
        }
        notificationCommandMapper.updateIsRead(memberId, notificationId);
    }
}



