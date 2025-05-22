package littleprince.item.query.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BadgeDto {
    private Long badgeId;
    private String badgeName;
    private String badgeMean;
    private int level;
    private String isSelected;
}