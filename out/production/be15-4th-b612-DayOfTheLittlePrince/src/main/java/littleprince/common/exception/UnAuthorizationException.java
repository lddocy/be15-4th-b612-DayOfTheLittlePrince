package littleprince.common.exception;


import lombok.Getter;

@Getter
public class UnAuthorizationException extends RuntimeException{
    private final ErrorCode errorCode;

    public UnAuthorizationException(ErrorCode errorCode){
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
