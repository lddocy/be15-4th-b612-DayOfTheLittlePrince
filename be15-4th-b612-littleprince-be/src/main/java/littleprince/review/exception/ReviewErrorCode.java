package littleprince.review.exception;

import littleprince.common.exception.ErrorCode;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public enum ReviewErrorCode implements ErrorCode {
    // review 오류
    /* 각 도메인마다 ERROR CODE 작성 */
    REVIEW_ALREADY_EXISTS("30001", "이미 작성하신 회고가 존재합니다.", HttpStatus.BAD_REQUEST);

    private final String code;
    private final String message;
    private final HttpStatus httpStatus;
}