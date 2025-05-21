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
    ACCESS_DENIED("20001", "해당 투두 항목에 대한 삭제 권한이 없습니다.", HttpStatus.FORBIDDEN),
    NOT_FOUND_TASK("20002", "해당 투두 항목을 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    INVALID_PROJECT_TASK("20003", "장기 투두 항목이 아닙니다.", HttpStatus.BAD_REQUEST),
    PROJECT_NOT_FOUND("20004", "해당 프로젝트가 존재하지 않습니다.", HttpStatus.NOT_FOUND);


    private final String code;
    private final String message;
    private final HttpStatus httpStatus;
}