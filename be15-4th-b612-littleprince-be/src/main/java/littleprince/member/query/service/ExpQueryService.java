package littleprince.member.query.service;

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

        return new CurExpResponse(member.getExp());
    }
}
