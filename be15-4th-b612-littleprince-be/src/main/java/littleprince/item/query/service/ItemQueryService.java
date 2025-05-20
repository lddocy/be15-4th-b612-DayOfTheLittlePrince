package littleprince.item.query.service;

import littleprince.item.query.dto.ItemDTO;
import littleprince.item.query.dto.response.ItemListResponse;
import littleprince.item.query.mapper.ItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemQueryService {
    private final ItemMapper itemMapper;

    /* 사용자 보유 아이템 조회 */
    @Transactional(readOnly = true)
    public ItemListResponse getItemList(Long memberId) {
        List<ItemDTO> itemList = itemMapper.findItemListByLevel(memberId);
        return ItemListResponse.builder()
                .itemList(itemList)
                .build();
    }

}
