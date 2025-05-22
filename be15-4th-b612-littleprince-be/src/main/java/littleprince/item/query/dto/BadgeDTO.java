package littleprince.item.query.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BadgeDTO {
    private Long badgeId;
    private String badgeName;
    private String badgeMean;
    private int level;
}
