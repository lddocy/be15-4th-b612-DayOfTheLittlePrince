package littleprince.member.command.application.service;


import littleprince.common.exception.BusinessException;
import littleprince.item.command.application.service.ItemCommandService;
import littleprince.item.query.mapper.GetBadgeCommandMapper;
import littleprince.item.query.mapper.GetBadgeQueryMapper;
import littleprince.member.command.application.dto.constant.MemberLevel;
import littleprince.member.command.application.dto.request.SignupRequest;
import littleprince.member.command.application.dto.response.ExpResponse;
import littleprince.member.command.application.respository.MemberRepository;
import littleprince.member.command.domain.aggregate.MemberDTO;
import littleprince.member.command.mapper.ExpHistoryCommandMapper;
import littleprince.member.command.mapper.MemberCommandMapper;
import littleprince.member.exception.MemberErrorCode;
import littleprince.member.query.dto.FindMemberDTO;
import littleprince.member.query.mapper.MemberQueryMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private final ItemCommandService itemCommandService;
    private final MemberRepository memberRepository;

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
        
        /* 3. 기본 아이템, 칭호 지급 */
//        itemCommandService.addItem();
    }

    @Override
    @Transactional
    public ExpResponse addExp(Long memberId, int amount) {
        MemberDTO member = memberQueryMapper.findById(memberId)
                .orElseThrow(() -> new RuntimeException("회원이 존재하지 않습니다."));

        int currentLevel = member.getLevel();
        int currentExp = member.getExp() + amount;
        boolean levelUp = false;

        expHistoryCommandMapper.insertExpHistory(memberId, amount);

        int nextLevel = currentLevel + 1;

        if (MemberLevel.fromLevel(nextLevel).isPresent()) {
            int requiredTotalExpForNextLevel = MemberLevel.getTotalExpByLevel(nextLevel);
            int requiredTotalExpForCurrent = MemberLevel.getTotalExpByLevel(currentLevel);
            int requiredThisLevelExp = requiredTotalExpForNextLevel - requiredTotalExpForCurrent;

            if (currentExp >= requiredThisLevelExp) {
                currentExp -= requiredThisLevelExp;
                currentLevel++;
                levelUp = true;

                Long badgeId = getBadgeQueryMapper.findBadgeIdByLevel(currentLevel);
                if (badgeId != null) {
                    getBadgeCommandMapper.insertGetBadge(memberId, badgeId);
                }
            }
        }

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
}
