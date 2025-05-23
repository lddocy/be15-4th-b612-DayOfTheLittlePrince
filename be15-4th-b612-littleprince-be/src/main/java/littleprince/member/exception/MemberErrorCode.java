package littleprince.member.exception;

import littleprince.common.exception.ErrorCode;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public enum MemberErrorCode implements ErrorCode {
    // member 오류
    /* 각 도메인마다 ERROR CODE 작성 */
    INVALID_LOGIN_REQUEST("10001", "아이디 또는 비밀번호가 잘못되었습니다.", HttpStatus.UNAUTHORIZED),
    USER_NOT_FOUND("10002", "존재하지 않는 회원입니다.", HttpStatus.BAD_REQUEST),
    DUPLICATE_EMAIL_EXISTS("10003", "이미 가입된 이메일입니다.", HttpStatus.BAD_REQUEST),
    PASSWORD_MISMATCH("10004","비밀번호가 동일하지 않습니다", HttpStatus.BAD_REQUEST),
    BADGE_NOT_FOUND("10005", "보유하고 있는 칭호가 없습니다.", HttpStatus.BAD_REQUEST),
    EMAIL_CODE_EXPIRED("10006", "이메일 코드가 만료되었습니다.", HttpStatus.BAD_REQUEST),
    PLANET_NAME_REQUIRED("10007", "행성 이름은 비어 있을 수 없습니다.", HttpStatus.BAD_REQUEST);

    private final String code;
    private final String message;
    private final HttpStatus httpStatus;
}