package littleprince.item.query.dto;

import littleprince.item.command.domain.aggregate.HiddenItem;
import lombok.Builder;

@Builder
public class GetItemDTO {
    private Long memberId;
    private Long itemId;
    private HiddenItem idHidden;
}
