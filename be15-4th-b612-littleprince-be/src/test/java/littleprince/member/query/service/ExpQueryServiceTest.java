package littleprince.member.query.service;

import littleprince.common.exception.BusinessException;
import littleprince.member.command.application.dto.constant.MemberLevel;
import littleprince.member.command.domain.aggregate.Member;
import littleprince.member.command.application.repository.MemberRepository;
import littleprince.member.query.dto.GetExpHistoryDTO;
import littleprince.member.query.dto.response.CurExpResponse;
import littleprince.member.query.dto.response.GetExpHistoryResponse;
import littleprince.member.query.mapper.ExpHistoryQueryMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class ExpQueryServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private ExpHistoryQueryMapper expHistoryQueryMapper;

    @InjectMocks
    private ExpQueryService expQueryService;

    private final Long memberId = 1L;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getUserExp_shouldReturnCorrectExpInfo() {
        // given
        Member member = Member.builder()
                .memberId(memberId)
                .level(1)
                .exp(20)
                .build();

        when(memberRepository.findById(memberId)).thenReturn(Optional.of(member));

        // when
        CurExpResponse response = expQueryService.getUserExp(memberId);

        // then
        assertThat(response.getCurrentExp()).isEqualTo(20);
        assertThat(response.getCurrentLevel()).isEqualTo(1);
        assertThat(response.getTotalExpToNextLevel()).isEqualTo(MemberLevel.getTotalExpByLevel(2));
    }

    @Test
    void getUserExp_shouldThrowException_ifUserNotFound() {
        // given
        when(memberRepository.findById(memberId)).thenReturn(Optional.empty());

        // when & then
        assertThatThrownBy(() -> expQueryService.getUserExp(memberId))
                .isInstanceOf(BusinessException.class);
    }

    @Test
    void getExpHistory_shouldReturnExpHistoryList() {
        // given
        List<GetExpHistoryDTO> historyList = List.of(
                GetExpHistoryDTO.builder().expPoint(5).build(),
                GetExpHistoryDTO.builder().expPoint(10).build()
        );

        when(expHistoryQueryMapper.getExpHistory(memberId)).thenReturn(historyList);

        // when
        GetExpHistoryResponse response = expQueryService.getExpHistory(memberId);

        // then
        assertThat(response.getExpHistoryDTO()).hasSize(2);
        assertThat(response.getExpHistoryDTO().get(0).getExpPoint()).isEqualTo(5);
    }
}
