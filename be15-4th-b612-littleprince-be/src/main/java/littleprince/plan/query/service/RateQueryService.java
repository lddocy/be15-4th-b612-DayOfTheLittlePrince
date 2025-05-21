package littleprince.plan.query.service;

import littleprince.plan.query.dto.response.RateResponse;
import littleprince.plan.query.mapper.RateQueryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RateQueryService {

    private final RateQueryMapper rateQueryMapper;

    public RateResponse getCompletionRate(Long memberId) {
        // 전체 달성률 계산
        int total = rateQueryMapper.countAll(memberId);
        int completed = rateQueryMapper.countCompleted(memberId);
        int totalRate = total == 0 ? 0 : (int) Math.round((completed * 100.0) / total);

        // 이번 달 범위 설정
        LocalDateTime startOfMonth = LocalDate.now().withDayOfMonth(1).atStartOfDay();
        LocalDateTime endOfMonth = startOfMonth.plusMonths(1);

        // 이번 달 달성률 계산
        int monthly = rateQueryMapper.countMonthly(memberId, startOfMonth, endOfMonth);
        int monthlyCompleted = rateQueryMapper.countMonthlyCompleted(memberId, startOfMonth, endOfMonth);
        int monthlyRate = monthly == 0 ? 0 : (int) Math.round((monthlyCompleted * 100.0) / monthly);

        return new RateResponse(totalRate, monthlyRate);
    }
}
