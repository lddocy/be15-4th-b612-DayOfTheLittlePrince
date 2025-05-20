package littleprince.item.command.application.dto.request;

import littleprince.item.command.domain.aggregate.HiddenItem;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ItemUpdateRequest {
    private HiddenItem isHidden;
}
