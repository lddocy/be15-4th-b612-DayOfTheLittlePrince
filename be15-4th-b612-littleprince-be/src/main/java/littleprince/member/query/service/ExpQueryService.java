package littleprince.member.query.service;

import littleprince.member.command.application.dto.constant.MemberLevel;
import littleprince.member.query.dto.response.CurExpResponse;
import littleprince.member.command.application.repository.MemberRepository;
import littleprince.member.command.domain.aggregate.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExpQueryService {

    private final MemberRepository memberRepository;
    public CurExpResponse getUserExp(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        int currentExp = member.getExp();
        int currentLevel = member.getLevel();

        int totalExpToNext = 0;
        if (MemberLevel.fromLevel(currentLevel + 1).isPresent()) {
            totalExpToNext = MemberLevel.getTotalExpByLevel(currentLevel + 1);
        }

        return new CurExpResponse(currentExp, currentLevel, totalExpToNext);
    }

}
