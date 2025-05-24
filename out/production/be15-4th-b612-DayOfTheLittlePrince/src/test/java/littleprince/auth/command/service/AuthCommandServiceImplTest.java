package littleprince.auth.command.service;

import littleprince.auth.fixture.LogoutRequest;
import littleprince.auth.fixture.LogoutRequestFixture;
import littleprince.common.exception.BusinessException;
import littleprince.common.exception.GlobalErrorCode;
import littleprince.common.exception.UnAuthorizationException;
import littleprince.infra.redis.RefreshTokenRepository;
import littleprince.utils.jwt.JwtTokenProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class AuthCommandServiceImplTest {
    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @Mock
    private RefreshTokenRepository refreshTokenRepository;

    @InjectMocks
    private AuthCommandServiceImpl authCommandService;

    @Test
    void 예외_유효하지_않은_토큰(){
        LogoutRequest request = LogoutRequestFixture.invalidTokenRequest();

        when(jwtTokenProvider.validateToken(request.getRefreshToken()))
                .thenThrow(new UnAuthorizationException(GlobalErrorCode.UNAUTHORIZED_REQUEST));

        assertThrows(UnAuthorizationException.class, () -> {
            authCommandService.logout(request.getUserDetail(), request.getRefreshToken());
        });

        verify(refreshTokenRepository, never()).delete(any());
    }

    @Test
    void 예외_서로_다른_엑세스토큰_리프레쉬토큰() {
        LogoutRequest request = LogoutRequestFixture.diffTokenRequest();

        when(jwtTokenProvider.validateToken(request.getRefreshToken())).thenReturn(true);
        /* accessToken 과 refreshToken의 멤버 아이디가 다른 경우 가정*/
        when(jwtTokenProvider.getMemberIdFromJWT(request.getRefreshToken())).thenReturn(request.getUserDetail().getMemberId() + 1L);

        assertThrows(BusinessException.class, () -> {
            authCommandService.logout(request.getUserDetail(), request.getRefreshToken());
        });

        verify(refreshTokenRepository, never()).delete(any());
    }


    @Test
    void 정상_로그_아웃(){
        LogoutRequest request = LogoutRequestFixture.validRequest();

        when(jwtTokenProvider.getMemberIdFromJWT(request.getRefreshToken())).thenReturn(request.getUserDetail().getMemberId());

        authCommandService.logout(request.getUserDetail(), request.getRefreshToken());

        verify(refreshTokenRepository).delete(request.getUserDetail().getMemberId());
    }

}