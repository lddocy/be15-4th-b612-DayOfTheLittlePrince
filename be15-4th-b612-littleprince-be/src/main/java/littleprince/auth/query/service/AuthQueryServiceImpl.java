package littleprince.auth.query.service;

import littleprince.auth.command.dto.response.TokenResponse;
import littleprince.auth.query.dto.request.LoginRequest;
import littleprince.common.exception.BusinessException;
import littleprince.common.exception.GlobalErrorCode;
import littleprince.infra.redis.FindPasswordRepository;
import littleprince.infra.redis.RefreshTokenRepository;
import littleprince.member.exception.MemberErrorCode;
import littleprince.member.query.dto.FindMemberDTO;
import littleprince.member.query.mapper.MemberQueryMapper;
import littleprince.utils.EmailUtils;
import littleprince.utils.RandomStringUtils;
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
    private final FindPasswordRepository findPasswordRepository;

    private final EmailUtils emailUtil;
    private final RandomStringUtils randomStringUtils;

    @Override
    public TokenResponse login(LoginRequest request) {

        /* 1. DB에서 유저 정보 존재 확인 */
        FindMemberDTO foundMember = memberQueryMapper.findMemberByEmail(request.getEmail())
                .orElseThrow(() -> new BusinessException(MemberErrorCode.INVALID_LOGIN_REQUEST));


        /* 2. 유저 정보 일치 확인 */
        if (!passwordEncoder.matches(request.getPassword(), foundMember.getPassword())) {
            throw new BusinessException(MemberErrorCode.INVALID_LOGIN_REQUEST);
        }
        log.info("유저 찾기 완료");

        /* 로그인 성공 */

        /* 3. access token 발급 */
        String accessToken = jwtTokenProvider.createAccessToken(foundMember.getMemberId(), foundMember.getEmail());

        // 4. refresh 토큰 발급
        String refreshToken = jwtTokenProvider.createRefreshToken(foundMember.getMemberId(), foundMember.getEmail());


        /* 5. redis에 토큰 저장 */
        refreshTokenRepository.save(foundMember.getMemberId(), refreshToken);
        log.info("레디스에 토큰 저장 완료");
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

        refreshTokenRepository.save(requestMemberId, newRefreshToken);

        return TokenResponse.builder()
                .accessToken(newAccessToken)
                .refreshToken(newRefreshToken)
                .build();
    }

    @Override
    public void findPassword(String email) {
        // 1. 이메일이 DB에 존재하는 유저인지 조회
        FindMemberDTO memberDTO = memberQueryMapper.findMemberByEmail(email)
                .orElseThrow(() -> new BusinessException(MemberErrorCode.USER_NOT_FOUND));

        // 2. Redis에 uuid 저장
        // 랜덤 문자열 생성
        String randomString = randomStringUtils.getRandomString(20);

        // 저장
        findPasswordRepository.save(email, randomString);

        // 3. 메일 전송 폼 작성
        String title = "[어린 왕자의 하루] 비밀번호를 잊으셨나요? 지금 바로 변경하세요";

        StringBuilder sb = new StringBuilder();

        sb.append("<h1>비밀번호 변경</h1>")
                .append("<p>아래 버튼을 클릭하면 비밀번호 변경 페이지로 이동합니다.</p>")
                .append("<a href=\"")
                .append("http://localhost:5173/changePassword?uuid=")
                .append(randomString)
                .append("\" ")
                .append("style=\"display:inline-block; padding:12px 24px; background-color:#4CAF50; color:#ffffff; text-align:center; text-decoration:none; font-size:16px; border-radius:6px; font-weight:bold;\">")
                .append("비밀번호 변경하기")
                .append("</a>");


        String content = sb.toString();

        // 4. 이메일 전송
        emailUtil.sendEmail(content, title, email);
    }
}
