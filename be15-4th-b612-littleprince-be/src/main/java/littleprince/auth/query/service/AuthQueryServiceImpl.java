package littleprince.auth.query.service;

import littleprince.auth.command.dto.response.TokenResponse;
import littleprince.auth.query.dto.request.LoginRequest;
import littleprince.common.exception.BusinessException;
import littleprince.common.exception.GlobalErrorCode;
import littleprince.infra.redis.RefreshTokenRepository;
import littleprince.member.exception.MemberErrorCode;
import littleprince.member.query.dto.FindMemberDTO;
import littleprince.member.query.mapper.MemberQueryMapper;
import littleprince.utils.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthQueryServiceImpl implements AuthQueryService{

    private final MemberQueryMapper memberQueryMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;


    @Override
    public TokenResponse login(LoginRequest request) {

        /* 1. DB에서 유저 정보 존재 확인 */
        FindMemberDTO foundMember = memberQueryMapper.findMemberByEmail(request.getEmail())
                .orElseThrow(() -> new BusinessException(MemberErrorCode.INVALID_LOGIN_REQUEST));


        /* 2. 유저 정보 일치 확인 */
        if (!passwordEncoder.matches(request.getPassword(), foundMember.getPassword())) {
            throw new BusinessException(MemberErrorCode.INVALID_LOGIN_REQUEST);
        }

        /* 로그인 성공 */

        /* 3. access token 발급 */
        String accessToken = jwtTokenProvider.createAccessToken(foundMember.getMemberId(), foundMember.getEmail());

        // 4. refresh 토큰 발급
        String refreshToken = jwtTokenProvider.createRefreshToken(foundMember.getMemberId(), foundMember.getEmail());


        /* 5. redis에 토큰 저장 */
        refreshTokenRepository.save(foundMember.getMemberId(), refreshToken);

        /* 6. 사용자에게 토큰 전달 */
        return TokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public TokenResponse reissue(String refreshToken) {

        /* 1. 토큰 검증 */
        jwtTokenProvider.validateToken(refreshToken);

        Long requestMemberId = jwtTokenProvider.getMemberIdFromJWT(refreshToken);
        String requestEmail = jwtTokenProvider.getEmailFromJWT(refreshToken);


        log.info("재발행 요청 멤버 ID : " + requestMemberId);
        log.info("재발행 요청 멤버 이메일 : " + requestEmail);

        /* 2. Redis에서 토큰 존재 확인 */
        String storedRefreshToken = refreshTokenRepository.find(requestMemberId);

        /* Redis에서 찾지 못한경우 에러 응답 */
        if(storedRefreshToken == null){
            throw new BusinessException(GlobalErrorCode.REFRESH_TOKEN_EXPIRED);
        }

        String newAccessToken = jwtTokenProvider.createAccessToken(requestMemberId, requestEmail);
        String newRefreshToken = jwtTokenProvider.createRefreshToken(requestMemberId, requestEmail);

        refreshTokenRepository.save(requestMemberId, requestEmail);

        return TokenResponse.builder()
                .accessToken(newAccessToken)
                .refreshToken(newRefreshToken)
                .build();
    }
}
