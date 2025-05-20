package littleprince.member.command.application.service;


import littleprince.common.exception.BusinessException;
import littleprince.item.query.mapper.GetBadgeCommandMapper;
import littleprince.item.query.mapper.GetBadgeQueryMapper;
import littleprince.member.command.aggregate.domain.MemberDTO;
import littleprince.member.command.application.dto.request.ExpRequest;
import littleprince.member.command.application.dto.request.SignupRequest;
import littleprince.member.command.application.dto.response.ExpResponse;
import littleprince.member.command.mapper.ExpHistoryCommandMapper;
import littleprince.member.command.mapper.MemberCommandMapper;
import littleprince.member.exception.MemberErrorCode;
import littleprince.member.query.dto.FindMemberDTO;
import littleprince.member.query.mapper.MemberQueryMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService {

    private final MemberQueryMapper memberQueryMapper;
    private final MemberCommandMapper memberCommandMapper;
    private final PasswordEncoder passwordEncoder;
    private final GetBadgeCommandMapper getBadgeCommandMapper;
    private final ExpHistoryCommandMapper expHistoryCommandMapper;
    private final GetBadgeQueryMapper getBadgeQueryMapper;


    @Override
    public void signup(SignupRequest request) {

        /* 비밀번호와 비밀번호 확인 일치 확인 */
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new BusinessException(MemberErrorCode.PASSWORD_MISMATCH);
        }

        /* 1. DB에서 email 중복 확인 */
        Optional<FindMemberDTO> foundMember = memberQueryMapper.findMemberByEmail(request.getEmail());

        /* 중복인 경우 에러 응답*/
        if (foundMember.isPresent()) {
            throw new BusinessException(MemberErrorCode.DUPLICATE_EMAIL_EXISTS);
        }

        String encryptPassword = passwordEncoder.encode(request.getPassword());

        MemberDTO member = MemberDTO.builder()
                .email(request.getEmail())
                .password(encryptPassword)
                .build();

        /* 2. 회원 가입 완료 */
        memberCommandMapper.insertMember(member);
    }

    @Override
    @Transactional
    public ExpResponse addExp(ExpRequest request) {
        Long memberId = request.getMemberId();
        int amount = request.getExpPoint();

        MemberDTO member = memberQueryMapper.findById(memberId)
                .orElseThrow(() -> new RuntimeException("회원이 존재하지 않습니다."));

        int currentLevel = member.getLevel();
        int currentExp = member.getExp() + amount;
        boolean levelUp = false;

        // 경험치 획득 기록
        expHistoryCommandMapper.insertExpHistory(memberId, amount);

        int nextLevel = currentLevel + 1;

        // 다음 레벨이 존재하는 경우에만 레벨업 시도
        if (nextLevel < LEVEL_REQUIRED_EXP.size()) {
            int requiredTotalExpForNextLevel = LEVEL_REQUIRED_EXP.get(nextLevel);
            int requiredTotalExpForCurrent = LEVEL_REQUIRED_EXP.get(currentLevel);
            int requiredThisLevelExp = requiredTotalExpForNextLevel - requiredTotalExpForCurrent;

            if (currentExp >= requiredThisLevelExp) {
                currentExp -= requiredThisLevelExp;
                currentLevel++;
                levelUp = true;

                // 칭호 지급
                Long badgeId = getBadgeQueryMapper.findBadgeIdByLevel(currentLevel);
                if (badgeId != null) {
                    getBadgeCommandMapper.insertGetBadge(memberId, badgeId);
                }
            }
        }

        // 최종 상태 반영
        member.setLevel(currentLevel);
        member.setExp(currentExp);
        memberCommandMapper.updateLevelAndExp(member);

        return ExpResponse.builder()
                .memberId(memberId)
                .updatedExp(currentExp)
                .updatedLevel(currentLevel)
                .levelUp(levelUp)
                .build();
    }


    /* 레벨들의 경험치 */
    private static final List<Integer> LEVEL_REQUIRED_EXP = List.of(
            0, 10, 30, 60, 100, 150, 210, 280, 360, 460, 600
    );

}
