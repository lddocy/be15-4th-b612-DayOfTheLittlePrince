package littleprince.plan.query.service;

import littleprince.plan.query.dto.response.LongDetailDTO;
import littleprince.plan.query.dto.response.LongListDTO;
import littleprince.plan.query.dto.response.LongPlanDTO;
import littleprince.plan.query.mapper.LongListQueryMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class LongListQueryServiceTest {

    @Mock
    private LongListQueryMapper longListQueryMapper;

    @InjectMocks
    private LongListQueryService longListQueryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getLongPlan_정상조회() {
        // given
        Long memberId = 1L;
        List<LongPlanDTO> mockList = List.of(
                LongPlanDTO.builder().projectId(1L).title("프로젝트1").build()
        );

        when(longListQueryMapper.getLongPlan(memberId)).thenReturn(mockList);

        // when
        var response = longListQueryService.getLongPlan(memberId);

        // then
        assertThat(response.getPlanDTOList()).hasSize(1);
        assertThat(response.getPlanDTOList().get(0).getProjectId()).isEqualTo(1L);
    }

    @Test
    void getLongList_정상조회() {
        // given
        Long memberId = 1L;
        Date date = Date.valueOf(LocalDate.now());
        List<LongListDTO> mockList = List.of(
                LongListDTO.builder().projectId(2L).title("리스트조회").build()
        );

        when(longListQueryMapper.getLongList(memberId, date)).thenReturn(mockList);

        // when
        var response = longListQueryService.getLongList(memberId, date);

        // then
        assertThat(response.getLongListDTOS()).hasSize(1);
        assertThat(response.getLongListDTOS().get(0).getProjectId()).isEqualTo(2L);
    }

    @Test
    void getLongDetail_정상조회() {
        // given
        Long memberId = 1L;
        Long projectId = 3L;
        Date date = Date.valueOf(LocalDate.now());

        List<LongDetailDTO> mockList = List.of(
                LongDetailDTO.builder().taskId(100L).content("상세1").build()
        );

        when(longListQueryMapper.getLongDetailList(memberId, date, projectId)).thenReturn(mockList);

        // when
        var response = longListQueryService.getLongDetail(memberId, date, projectId);

        // then
        assertThat(response.getDetailDTOS()).hasSize(1);
        assertThat(response.getDetailDTOS().get(0).getTaskId()).isEqualTo(100L);
    }
}
