package littleprince.auth.command.service;

import littleprince.common.exception.BusinessException;
import littleprince.common.exception.GlobalErrorCode;
import littleprince.config.security.model.CustomUserDetail;
import littleprince.infra.redis.RefreshTokenRepository;
import littleprince.utils.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthCommandServiceImpl implements AuthCommandService{
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public void logout(CustomUserDetail userDetail, String refreshToken) {
        /* 0. 토큰 검증 */
        jwtTokenProvider.validateToken(refreshToken);

        /* 1. 리프레쉬 토큰에서 값 추출 */
        Long memberId = jwtTokenProvider.getMemberIdFromJWT(refreshToken);

        /* 2. 유저 정보 확인 */
        /* 예외 상황, accessToken의 memberId와 refreshToken의 memberId가 다른 경우! */
        if(!Objects.equals(userDetail.getMemberId(), memberId)){
            throw new BusinessException(GlobalErrorCode.UNAUTHORIZED_REQUEST);
        }

        /* 3. Redis에 있는 리프레쉬 토큰 제거  */
        refreshTokenRepository.delete(memberId);
    }
}
