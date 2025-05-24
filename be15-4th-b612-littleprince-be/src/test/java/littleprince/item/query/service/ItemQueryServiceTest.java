package littleprince.item.query.service;

import littleprince.item.query.dto.ItemDTO;
import littleprince.item.query.dto.response.ItemListResponse;
import littleprince.item.query.mapper.ItemMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class ItemQueryServiceTest {

    @Mock
    private ItemMapper itemMapper;

    @InjectMocks
    private ItemQueryService itemQueryService;

    private final Long memberId = 1L;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getItemList_정상적으로_아이템_리스트_반환() {
        // given
        ItemDTO item1 = ItemDTO.builder().itemId(1).itemName("검").level(1).build();
        ItemDTO item2 = ItemDTO.builder().itemId(2).itemName("방패").level(2).build();

        when(itemMapper.findItemListByLevel(memberId)).thenReturn(List.of(item1, item2));

        // when
        ItemListResponse response = itemQueryService.getItemList(memberId);

        // then
        assertThat(response.getItemList()).hasSize(2);
        assertThat(response.getItemList()).extracting("itemName")
                .containsExactly("검", "방패");
    }
}
