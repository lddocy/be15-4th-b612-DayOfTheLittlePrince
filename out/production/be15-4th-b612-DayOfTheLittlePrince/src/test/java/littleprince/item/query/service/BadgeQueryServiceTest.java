package littleprince.item.query.service;

import littleprince.common.exception.BusinessException;
import littleprince.item.query.dto.BadgeDTO;
import littleprince.item.query.dto.response.MyBadgeResponse;
import littleprince.item.query.mapper.GetBadgeQueryMapper;
import littleprince.member.command.domain.aggregate.MemberDTO;
import littleprince.member.exception.MemberErrorCode;
import littleprince.member.query.mapper.MemberQueryMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class BadgeQueryServiceTest {

    @Mock
    private GetBadgeQueryMapper getBadgeQueryMapper;

    @Mock
    private MemberQueryMapper memberQueryMapper;

    @InjectMocks
    private BadgeQueryService badgeQueryService;

    private final Long memberId = 1L;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getObtainedBadges_정상적으로_리스트를_반환() {
        // given
        BadgeDTO badge = BadgeDTO.builder()
                .badgeId(1L)
                .badgeName("용기")
                .badgeMean("용감함의 상징")
                .level(1)
                .isSelected("Y")
                .build();

        when(getBadgeQueryMapper.findBadgesByMemberId(memberId)).thenReturn(List.of(badge));

        // when
        MyBadgeResponse response = badgeQueryService.getObtainedBadges(memberId);

        // then
        assertThat(response.getBadges()).hasSize(1);
        assertThat(response.getBadges().get(0).getBadgeName()).isEqualTo("용기");
    }

    @Test
    void getSelectedBadge_유저없으면_예외() {
        // given
        when(memberQueryMapper.findById(memberId)).thenReturn(Optional.empty());

        // when & then
        assertThatThrownBy(() -> badgeQueryService.getSelectedBadge(memberId))
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining(MemberErrorCode.USER_NOT_FOUND.getMessage());
    }

    @Test
    void getSelectedBadge_정상반환() {
        // given
        when(memberQueryMapper.findById(memberId)).thenReturn(Optional.of(MemberDTO.builder().memberId(memberId).build()));
        when(getBadgeQueryMapper.findSelectedBadgeByMemberId(memberId)).thenReturn("용기");

        // when
        String badgeName = badgeQueryService.getSelectedBadge(memberId);

        // then
        assertThat(badgeName).isEqualTo("용기");
    }
}
