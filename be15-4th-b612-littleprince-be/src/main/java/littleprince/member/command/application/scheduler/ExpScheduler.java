package littleprince.member.command.application.scheduler;

import littleprince.member.command.application.service.MemberCommandService;
import littleprince.member.command.domain.aggregate.MemberDTO;
import littleprince.member.query.dto.MemberExpDTO;
import littleprince.member.query.mapper.ExpHistoryQueryMapper;
import littleprince.member.query.mapper.MemberQueryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@RequiredArgsConstructor
@Component
public class ExpScheduler {

    private final MemberQueryMapper memberQueryMapper;
    private final MemberCommandService memberCommandService;
    private final ExpHistoryQueryMapper expHistoryQueryMapper;

    @Scheduled(cron = "0 * * * * *") // 매 1분마다 실행
    @Transactional
    public void giveDailyExp() {
        List<MemberExpDTO> members = memberQueryMapper.findAllExpInfo();

        for (MemberExpDTO member : members) {
            Long memberId = member.getMemberId();

            boolean alreadyGiven = expHistoryQueryMapper.existsTodayHistory(memberId);
            if (alreadyGiven) continue;

            memberCommandService.addExp(memberId, 5);
        }
    }
}

