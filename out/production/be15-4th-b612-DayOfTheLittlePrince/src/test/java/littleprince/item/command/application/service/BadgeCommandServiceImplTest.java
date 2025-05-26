package littleprince.item.command.application.service;

import littleprince.common.exception.BusinessException;
import littleprince.item.Exception.ItemErrorCode;
import littleprince.item.query.mapper.GetBadgeCommandMapper;
import littleprince.member.command.domain.aggregate.MemberDTO;
import littleprince.member.exception.MemberErrorCode;
import littleprince.member.query.mapper.MemberQueryMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class BadgeCommandServiceImplTest {

    @Mock
    private GetBadgeCommandMapper getBadgeCommandMapper;

    @Mock
    private MemberQueryMapper memberQueryMapper;

    @InjectMocks
    private BadgeCommandServiceImpl badgeCommandService;

    private final Long memberId = 1L;
    private final Long badgeId = 100L;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void selectBadge_보유하지_않은_뱃지면_예외발생() {
        // given
        when(getBadgeCommandMapper.hasBadge(memberId, badgeId)).thenReturn(false);

        // when & then
        assertThatThrownBy(() -> badgeCommandService.selectBadge(memberId, badgeId))
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining(ItemErrorCode.ITEM_BADGE_NOT_OWNED.getMessage());
    }

    @Test
    void selectBadge_정상동작_시_뱃지선택_로직실행() {
        // given
        when(getBadgeCommandMapper.hasBadge(memberId, badgeId)).thenReturn(true);
        when(getBadgeCommandMapper.existsSelectedBadge(memberId)).thenReturn(true);

        // when
        badgeCommandService.selectBadge(memberId, badgeId);

        // then
        verify(getBadgeCommandMapper).clearSelectedBadge(memberId);
        verify(getBadgeCommandMapper).updateSelectedBadge(memberId, badgeId);
        verify(getBadgeCommandMapper, never()).selectLv0Badge(memberId);
    }

    @Test
    void selectBadge_선택된_뱃지가_없으면_Lv0_자동선택() {
        when(getBadgeCommandMapper.hasBadge(memberId, badgeId)).thenReturn(true);
        when(getBadgeCommandMapper.existsSelectedBadge(memberId)).thenReturn(false);

        badgeCommandService.selectBadge(memberId, badgeId);

        verify(getBadgeCommandMapper).selectLv0Badge(memberId);
    }

    @Test
    void addBadge_회원없으면_예외() {
        when(memberQueryMapper.findById(memberId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> badgeCommandService.addBadge(memberId, 3))
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining(MemberErrorCode.USER_NOT_FOUND.getMessage());
    }

    @Test
    void addBadge_정상추가() {
        MemberDTO mockMember = MemberDTO.builder().memberId(memberId).build();
        when(memberQueryMapper.findById(memberId)).thenReturn(Optional.of(mockMember));

        badgeCommandService.addBadge(memberId, 2);

        verify(getBadgeCommandMapper).insertGetBadge(memberId, 2);
    }

    @Test
    void addDefaultBadge_정상추가() {
        MemberDTO mockMember = MemberDTO.builder().memberId(memberId).build();
        when(memberQueryMapper.findById(memberId)).thenReturn(Optional.of(mockMember));

        badgeCommandService.addDefaultBadge(memberId);

        verify(getBadgeCommandMapper).insertDefaultGetBadge(memberId, 0);
    }
}
