package littleprince.member.command.application.scheduler;

import littleprince.member.command.application.service.MemberCommandService;
import littleprince.member.command.domain.aggregate.MemberDTO;
import littleprince.member.query.mapper.ExpHistoryQueryMapper;
import littleprince.member.query.mapper.MemberQueryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Component
@RequiredArgsConstructor
public class ExpScheduler {

    private final MemberCommandService memberCommandService;
    private final MemberQueryMapper memberQueryMapper;
    private final ExpHistoryQueryMapper expHistoryQueryMapper;

    @Scheduled(cron = "0 */1 * * * *")
    @Transactional
    public void giveDailyExp() {
        List<MemberDTO> members = memberQueryMapper.findAll();

        for (MemberDTO member : members) {
            Long memberId = member.getMemberId();

            boolean alreadyGiven = expHistoryQueryMapper.existsTodayHistory(memberId);
            if (alreadyGiven) continue;

            memberCommandService.addExp(memberId, 5);
        }
    }
}

