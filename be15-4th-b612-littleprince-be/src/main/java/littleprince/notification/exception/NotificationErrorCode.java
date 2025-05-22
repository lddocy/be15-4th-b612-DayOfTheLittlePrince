package littleprince.notification.exception;

import littleprince.common.exception.ErrorCode;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public enum NotificationErrorCode implements ErrorCode {

    NOTIFICATION_NOT_FOUND("50001", "알림이 존재하지 않습니다.", HttpStatus.NOT_FOUND),
    ALREADY_READ("50002", "이미 읽음 처리된 알림입니다.", HttpStatus.BAD_REQUEST);

    private final String code;
    private final String message;
    private final HttpStatus httpStatus;
}
