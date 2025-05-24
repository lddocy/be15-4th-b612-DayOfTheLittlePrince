package littleprince.item.command.application.service;

import littleprince.common.domain.StatusType;
import littleprince.common.exception.BusinessException;
import littleprince.item.Exception.ItemErrorCode;
import littleprince.item.command.domain.aggregate.entity.GetItem;
import littleprince.item.command.domain.aggregate.entity.Item;
import littleprince.item.command.domain.repository.GetItemRepository;
import littleprince.item.command.domain.repository.ItemRepository;
import littleprince.member.command.domain.aggregate.MemberDTO;
import littleprince.member.exception.MemberErrorCode;
import littleprince.member.query.mapper.MemberQueryMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class ItemCommandServiceTest {

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private MemberQueryMapper memberQueryMapper;

    @Mock
    private GetItemRepository getItemRepository;

    @InjectMocks
    private ItemCommandService itemCommandService;

    private final Long memberId = 1L;
    private final int level = 2;
    private final Long itemId = 10L;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addItem_회원이_없으면_예외() {
        when(memberQueryMapper.findById(memberId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> itemCommandService.addItem(memberId, level))
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining(MemberErrorCode.USER_NOT_FOUND.getMessage());
    }

    @Test
    void addItem_아이템이_없으면_예외() {
        MemberDTO mockMember = MemberDTO.builder().memberId(memberId).build();
        when(memberQueryMapper.findById(memberId)).thenReturn(Optional.of(mockMember));
        when(itemRepository.findByLevel(level)).thenReturn(null);

        assertThatThrownBy(() -> itemCommandService.addItem(memberId, level))
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining(ItemErrorCode.ITEM_NOT_FOUND.getMessage());
    }

    @Test
    void addItem_이미_있는_아이템이면_추가하지_않는다() {
        MemberDTO mockMember = MemberDTO.builder().memberId(memberId).build();
        Item mockItem = Item.builder().itemId(itemId).level(level).build();

        when(memberQueryMapper.findById(memberId)).thenReturn(Optional.of(mockMember));
        when(itemRepository.findByLevel(level)).thenReturn(mockItem);
        when(getItemRepository.existsByMemberIdAndItemId(memberId, itemId)).thenReturn(true);

        itemCommandService.addItem(memberId, level);

        verify(getItemRepository, never()).save(any());
    }

    @Test
    void addItem_정상추가() {
        MemberDTO mockMember = MemberDTO.builder().memberId(memberId).build();
        Item mockItem = Item.builder().itemId(itemId).level(level).build();

        when(memberQueryMapper.findById(memberId)).thenReturn(Optional.of(mockMember));
        when(itemRepository.findByLevel(level)).thenReturn(mockItem);
        when(getItemRepository.existsByMemberIdAndItemId(memberId, itemId)).thenReturn(false);

        itemCommandService.addItem(memberId, level);

        verify(getItemRepository).save(any(GetItem.class));
    }

    @Test
    void toggleItemHidden_회원없으면_예외() {
        when(memberQueryMapper.findById(memberId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> itemCommandService.toggleItemHidden(memberId, itemId))
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining(MemberErrorCode.USER_NOT_FOUND.getMessage());
    }

    @Test
    void toggleItemHidden_아이템없으면_예외() {
        when(memberQueryMapper.findById(memberId)).thenReturn(Optional.of(MemberDTO.builder().memberId(memberId).build()));
        when(getItemRepository.findByMemberIdAndItemId(memberId, itemId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> itemCommandService.toggleItemHidden(memberId, itemId))
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining(ItemErrorCode.ITEM_NOT_FOUND.getMessage());
    }

    @Test
    void toggleItemHidden_Y이면_N으로변경() {
        GetItem item = GetItem.builder().memberId(memberId).itemId(itemId).isHidden(StatusType.Y).build();

        when(memberQueryMapper.findById(memberId)).thenReturn(Optional.of(MemberDTO.builder().memberId(memberId).build()));
        when(getItemRepository.findByMemberIdAndItemId(memberId, itemId)).thenReturn(Optional.of(item));

        itemCommandService.toggleItemHidden(memberId, itemId);

        assertThat(item.getIsHidden()).isEqualTo(StatusType.N);
    }

    @Test
    void toggleItemHidden_N이면_Y로변경() {
        GetItem item = GetItem.builder().memberId(memberId).itemId(itemId).isHidden(StatusType.N).build();

        when(memberQueryMapper.findById(memberId)).thenReturn(Optional.of(MemberDTO.builder().memberId(memberId).build()));
        when(getItemRepository.findByMemberIdAndItemId(memberId, itemId)).thenReturn(Optional.of(item));

        itemCommandService.toggleItemHidden(memberId, itemId);

        assertThat(item.getIsHidden()).isEqualTo(StatusType.Y);
    }
}
