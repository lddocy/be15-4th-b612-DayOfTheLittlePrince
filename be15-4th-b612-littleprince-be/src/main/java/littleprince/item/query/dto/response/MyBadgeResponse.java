package littleprince.item.query.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class MyBadgeResponse {
    private List<BadgeDto> badges;
}
