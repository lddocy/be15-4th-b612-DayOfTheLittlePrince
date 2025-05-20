package littleprince.member.query.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetExpHistoryDTO {
    private Long historyId;
    private int expPoint;
}
