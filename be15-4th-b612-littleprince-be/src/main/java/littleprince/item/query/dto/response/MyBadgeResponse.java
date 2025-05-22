package littleprince.item.query.dto.response;

import littleprince.item.query.dto.BadgeDTO;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class MyBadgeResponse {
    private List<BadgeDTO> badges;
}
