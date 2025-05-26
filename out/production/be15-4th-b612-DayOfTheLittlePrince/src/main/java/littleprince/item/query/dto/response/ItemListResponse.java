package littleprince.item.query.dto.response;

import littleprince.item.query.dto.ItemDTO;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class ItemListResponse {
    List<ItemDTO> itemList;
}
