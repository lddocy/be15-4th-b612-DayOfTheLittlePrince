package littleprince.auth.command.service;

import littleprince.auth.command.dto.request.ChangePasswordRequest;
import littleprince.common.domain.StatusType;
import littleprince.common.exception.BusinessException;
import littleprince.common.exception.GlobalErrorCode;
import littleprince.config.security.model.CustomUserDetail;
import littleprince.infra.redis.FindPasswordRepository;
import littleprince.infra.redis.RefreshTokenRepository;
import littleprince.member.command.application.repository.MemberRepository;
import littleprince.member.command.domain.aggregate.Member;
import littleprince.member.exception.MemberErrorCode;
import littleprince.utils.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthCommandServiceImpl implements AuthCommandService{
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final FindPasswordRepository findPasswordRepository;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

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

    @Override
    public void changePassword(ChangePasswordRequest request) {
        String uuid = request.getUuid();
        String password = request.getPassword();
        String confirmPassword = request.getConfirmPassword();

        // 1. 비밀번호가 일치하지 않을 경우
        if(!password.equals(confirmPassword)){
            throw new BusinessException(MemberErrorCode.PASSWORD_MISMATCH);
        }

        // 2. uuid 에서 이메일 찾기
        String foundEmail = findPasswordRepository.find(uuid);

        // 3. 비밀번호 변경 해주기
        Member member = (Member) memberRepository.findByEmailAndIsDeleted(foundEmail, StatusType.N).orElseThrow(
                () -> new BusinessException(MemberErrorCode.USER_NOT_FOUND)
        );

        String encryptPassword = passwordEncoder.encode(request.getPassword());

        member.changePassword(encryptPassword);

        memberRepository.save(member);
        findPasswordRepository.delete(uuid);
    }
}
