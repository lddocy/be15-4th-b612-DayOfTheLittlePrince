package littleprince.item.query.dto;

import littleprince.common.domain.StatusType;
import lombok.Builder;

@Builder
public class GetItemDTO {
    private Long memberId;
    private Long itemId;
    private StatusType idHidden;
}
