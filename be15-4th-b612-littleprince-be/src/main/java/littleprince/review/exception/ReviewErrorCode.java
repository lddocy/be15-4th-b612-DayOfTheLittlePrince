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
    REVIEW_SUBMIT_FAILED("30001", "회고를 작성할 수 없습니다.", HttpStatus.BAD_REQUEST);

    private final String code;
    private final String message;
    private final HttpStatus httpStatus;
}