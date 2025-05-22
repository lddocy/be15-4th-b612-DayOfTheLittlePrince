package littleprince.item.query.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BadgeDTO {
    private Long badgeId;
    private String badgeName;
    private String badgeMean;
    private int level;
    private String isSelected;
}