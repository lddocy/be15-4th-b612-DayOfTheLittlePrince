package littleprince.plan.exception;

import littleprince.common.exception.ErrorCode;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public enum PlanErrorCode implements ErrorCode {
    // member 오류
    /* 각 도메인마다 ERROR CODE 작성 */
    ACCESS_DENIED("20001", "해당 투두 항목에 대한 삭제 권한이 없습니다.", HttpStatus.FORBIDDEN);

    private final String code;
    private final String message;
    private final HttpStatus httpStatus;
}