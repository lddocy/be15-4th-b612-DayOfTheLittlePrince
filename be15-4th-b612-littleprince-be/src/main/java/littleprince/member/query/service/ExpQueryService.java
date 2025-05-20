package littleprince.member.query.service;

import littleprince.common.exception.BusinessException;
import littleprince.member.command.application.dto.constant.MemberLevel;
import littleprince.member.command.application.repository.MemberRepository;
import littleprince.member.exception.MemberErrorCode;
import littleprince.member.query.dto.response.CurExpResponse;
import littleprince.member.command.domain.aggregate.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExpQueryService {
    private final MemberRepository memberRepository;

    public CurExpResponse getUserExp(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new BusinessException(MemberErrorCode.USER_NOT_FOUND));

        int currentExp = member.getExp();
        int currentLevel = member.getLevel();

        int totalExpToNext = 0;
        if (MemberLevel.fromLevel(currentLevel + 1).isPresent()) {
            totalExpToNext = MemberLevel.getTotalExpByLevel(currentLevel + 1);
        }

        return new CurExpResponse(currentExp, currentLevel, totalExpToNext);
    }
}
