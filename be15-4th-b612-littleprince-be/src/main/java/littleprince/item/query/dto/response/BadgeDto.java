package littleprince.item.query.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BadgeDto {
    private Long badgeId;
    private String badgeName;
    private String badgeMean;
    private int level;
}
