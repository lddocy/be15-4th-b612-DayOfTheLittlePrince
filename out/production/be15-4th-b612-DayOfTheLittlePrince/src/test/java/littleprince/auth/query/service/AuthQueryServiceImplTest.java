package littleprince.auth.query.service;

import littleprince.auth.command.dto.response.TokenResponse;
import littleprince.auth.fixture.LoginRequestFixture;
import littleprince.auth.query.dto.request.LoginRequest;
import littleprince.common.exception.BusinessException;
import littleprince.infra.redis.RefreshTokenRepository;
import littleprince.member.exception.MemberErrorCode;
import littleprince.member.query.dto.FindMemberDTO;
import littleprince.member.query.mapper.MemberQueryMapper;
import littleprince.utils.jwt.JwtTokenProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthQueryServiceImplTest {
    @Mock
    private MemberQueryMapper memberQueryMapper;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private JwtTokenProvider jwtTokenProvider;
    @Mock
    private RefreshTokenRepository refreshTokenRepository;

    @InjectMocks
    private AuthQueryServiceImpl authQueryService;

    @Test
    void 예외_가입된_이메일_없음(){
        LoginRequest request = LoginRequestFixture.wrongEmail();

        when(memberQueryMapper.findMemberByEmail(request.getEmail()))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> authQueryService.login(request))
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining(MemberErrorCode.INVALID_LOGIN_REQUEST.getMessage());
    }

    @Test
    void 예외_비밀번호_틀림(){
        LoginRequest request = LoginRequestFixture.wrongPassword();

        FindMemberDTO foundMember = FindMemberDTO.builder()
                .memberId(1L)
                .email(request.getEmail())
                /* 인코딩 되어 자장된 비밀번호 */
                .password("4321")
                .build();
        when(memberQueryMapper.findMemberByEmail(request.getEmail()))
                .thenReturn(Optional.of(foundMember));

        when(passwordEncoder.matches(request.getPassword(), "4321"))
                .thenReturn(false);

        assertThatThrownBy(() -> authQueryService.login(request))
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining(MemberErrorCode.INVALID_LOGIN_REQUEST.getMessage());

    }

    @Test
    void 정상_로그인() {
        // given
        LoginRequest request = LoginRequestFixture.validRequest();

        FindMemberDTO foundMember = FindMemberDTO.builder()
                .memberId(1L)
                .email(request.getEmail())
                .password("4321") // DB에 저장된 암호화된 패스워드
                .build();

        when(memberQueryMapper.findMemberByEmail(request.getEmail()))
                .thenReturn(Optional.of(foundMember));

        when(passwordEncoder.matches(request.getPassword(), "4321"))
                .thenReturn(true);

        when(jwtTokenProvider.createAccessToken(1L, request.getEmail()))
                .thenReturn("mockAT");

        when(jwtTokenProvider.createRefreshToken(1L, request.getEmail()))
                .thenReturn("mockRT");

        TokenResponse response = authQueryService.login(request);

        assertThat(response.getAccessToken()).isEqualTo("mockAT");
        assertThat(response.getRefreshToken()).isEqualTo("mockRT");

        verify(refreshTokenRepository).save(1L, "mockRT");
    }

    @Test
    void 예외_Redis에_저장된_토큰_없음_또는_만료됨() {
        // given
        String requestRefreshToken = "requestRefreshToken";
        Long memberId = 1L;
        String email = "user01@gmail.com";

        when(jwtTokenProvider.validateToken(requestRefreshToken)).thenReturn(true);
        when(jwtTokenProvider.getMemberIdFromJWT(requestRefreshToken)).thenReturn(memberId);
        when(jwtTokenProvider.getEmailFromJWT(requestRefreshToken)).thenReturn(email);

        // Redis에서 토큰 못 찾음
        when(refreshTokenRepository.find(memberId)).thenReturn(null);

        assertThrows(BusinessException.class, () -> {
            authQueryService.reissue(requestRefreshToken);
        });

        verify(refreshTokenRepository, never()).save(any(), any());
    }



    @Test
    void 정상_토큰_재발행(){
        String requestRefreshToken = "requestRefreshToken";
        Long memberId = 1L;
        String email = "user01@gmail.com";
        String storedRefreshToken = "storedRefreshToken";

        /* 토큰 검증 진행 X*/
        when(jwtTokenProvider.validateToken(requestRefreshToken)).thenReturn(true);
        when(jwtTokenProvider.getMemberIdFromJWT(requestRefreshToken)).thenReturn(memberId);
        when(jwtTokenProvider.getEmailFromJWT(requestRefreshToken)).thenReturn(email);
        when(refreshTokenRepository.find(memberId)).thenReturn(storedRefreshToken);

        when(jwtTokenProvider.createAccessToken(memberId, email)).thenReturn("newAccessToken");
        when(jwtTokenProvider.createRefreshToken(memberId, email)).thenReturn("newRefreshToken");

        // when
        TokenResponse response = authQueryService.reissue(requestRefreshToken);

        // then
        assertThat(response.getAccessToken()).isEqualTo("newAccessToken");
        assertThat(response.getRefreshToken()).isEqualTo("newRefreshToken");
        verify(refreshTokenRepository).save(eq(memberId), eq("newRefreshToken"));
    }

}