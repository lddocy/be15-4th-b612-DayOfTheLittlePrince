package littleprince.plan.query.service;

import littleprince.plan.query.dto.response.RateResponse;
import littleprince.plan.query.mapper.RateQueryMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class RateQueryServiceTest {

    @Mock
    private RateQueryMapper rateQueryMapper;

    @InjectMocks
    private RateQueryService rateQueryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getCompletionRate_전체와이번달_달성률을_계산한다() {
        // given
        Long memberId = 1L;
        when(rateQueryMapper.countAll(memberId)).thenReturn(10);
        when(rateQueryMapper.countCompleted(memberId)).thenReturn(7);

        LocalDateTime startOfMonth = LocalDateTime.now().withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime endOfMonth = startOfMonth.plusMonths(1);

        when(rateQueryMapper.countMonthly(memberId, startOfMonth, endOfMonth)).thenReturn(5);
        when(rateQueryMapper.countMonthlyCompleted(memberId, startOfMonth, endOfMonth)).thenReturn(3);

        // when
        RateResponse response = rateQueryService.getCompletionRate(memberId);

        // then
        assertThat(response.getTotalRate()).isEqualTo(70); // 7 / 10 * 100
        assertThat(response.getMonthlyRate()).isEqualTo(60); // 3 / 5 * 100
    }

    @Test
    void getMonthlyCompletionRate_특정기간_달성률을_계산한다() {
        // given
        Long memberId = 1L;
        LocalDateTime start = LocalDateTime.of(2025, 5, 1, 0, 0);
        LocalDateTime end = LocalDateTime.of(2025, 5, 31, 23, 59);

        when(rateQueryMapper.countMonthly(memberId, start, end)).thenReturn(4);
        when(rateQueryMapper.countMonthlyCompleted(memberId, start, end)).thenReturn(2);

        // when
        RateResponse response = rateQueryService.getMonthlyCompletionRate(memberId, start, end);

        // then
        assertThat(response.getMonthlyRate()).isEqualTo(50); // 2 / 4 * 100
        assertThat(response.getTotalRate()).isEqualTo(0); // 사용하지 않음
    }
}
