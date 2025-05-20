package littleprince.ai.query.exception;

import littleprince.common.exception.ErrorCode;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public enum AiErrorCode implements ErrorCode {

    TODO_HISTORY_TOO_SHORT("40001", "최근 입력된 TODO 리스트가 너무 적습니다.", HttpStatus.BAD_REQUEST),
    AI_PLAN_GENERATION_FAILED("40002", "AI 응답 생성에 실패하였습니다.", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String code;
    private final String message;
    private final HttpStatus httpStatus;
}
