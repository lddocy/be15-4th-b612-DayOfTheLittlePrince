package littleprince.item.Exception;

import littleprince.common.exception.ErrorCode;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public enum ItemErrorCode implements ErrorCode {
    ITEM_NOT_FOUND("30001", "아이템을 찾을 수 없습니다.", HttpStatus.UNAUTHORIZED),
    ITEM_BADGE_NOT_OWNED("30002","보유한 칭호가 아닙니다.", HttpStatus.UNAUTHORIZED);

    private final String code;
    private final String message;
    private final HttpStatus httpStatus;
}
