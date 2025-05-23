package littleprince.plan.query.service;

import littleprince.common.domain.StatusType;
import littleprince.plan.query.dto.response.*;
import littleprince.plan.query.mapper.ShortListQueryMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class ShortListQueryServiceTest {

    @Mock
    private ShortListQueryMapper shortListQueryMapper;

    @InjectMocks
    private ShortListQueryService shortListQueryService;

    private final Long memberId = 1L;
    private final Date date = Date.valueOf(LocalDate.now());

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getShortList_shouldReturnShortListResponse() {
        List<ShortListDTO> mockList = List.of(
                ShortListDTO.builder()
                        .taskId(1L)
                        .memberId(1L)
                        .content("할 일")
                        .isChecked(StatusType.N)
                        .date(Date.valueOf(LocalDate.now()))
                        .build()
        );

        when(shortListQueryMapper.getShortList(memberId, date)).thenReturn(mockList);

        ShortListResponse response = shortListQueryService.getShortList(memberId, date);

        assertThat(response.getShortList()).isEqualTo(mockList);
    }

    @Test
    void getShortDates_shouldReturnPlanDateDTOs() {
        List<ShortPlanDateDTO> planDateList = List.of(
                ShortPlanDateDTO.builder()
                        .date(date)
                        .build()
        );

        when(shortListQueryMapper.getShortDates(memberId)).thenReturn(planDateList);

        ShortPlanDateResponse response = shortListQueryService.getShortDates(memberId);

        assertThat(response.getPlanDateDTO()).isEqualTo(planDateList);
    }

    @Test
    void getTodayTasksText_shouldReturnFormattedString() {
        List<ShortListDTO> list = List.of(
                ShortListDTO.builder()
                        .taskId(1L)
                        .memberId(memberId)
                        .content("공부하기")
                        .isChecked(StatusType.N)
                        .date(date)
                        .build()
        );

        when(shortListQueryMapper.getShortList(memberId, date)).thenReturn(list);

        String result = shortListQueryService.getTodayTasksText(memberId, date);

        assertThat(result).contains("공부하기");
    }

    @Test
    void getTodayTasksText_shouldReturnDefaultMessageIfEmpty() {
        when(shortListQueryMapper.getShortList(memberId, date)).thenReturn(List.of());

        String result = shortListQueryService.getTodayTasksText(memberId, date);

        assertThat(result).isEqualTo("오늘의 할 일이 없습니다.");
    }

    @Test
    void countUncheckedTasks_shouldReturnCount() {
        when(shortListQueryMapper.countUncheckedTasks(memberId, date)).thenReturn(3L);

        long result = shortListQueryService.countUncheckedTasks(memberId, date);

        assertThat(result).isEqualTo(3L);
    }
}
