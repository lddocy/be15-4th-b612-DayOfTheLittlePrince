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

    // 1. 전체 + 이번 달 달성률 계산 (기존 메서드)
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

    // 2. 특정 기간에 대한 이번 달 달성률만 계산 (추가 메서드)
    public RateResponse getMonthlyCompletionRate(Long memberId, LocalDateTime start, LocalDateTime end) {
        int monthly = rateQueryMapper.countMonthly(memberId, start, end);
        int monthlyCompleted = rateQueryMapper.countMonthlyCompleted(memberId, start, end);
        int monthlyRate = monthly == 0 ? 0 : (int) Math.round((monthlyCompleted * 100.0) / monthly);

        return new RateResponse(0, monthlyRate); // totalRate는 사용하지 않으므로 0 처리
    }
}
