package littleprince.item.query.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ItemDTO {
    private int itemId;
    private String isHidden;
    private String itemName;
    private int level;
}