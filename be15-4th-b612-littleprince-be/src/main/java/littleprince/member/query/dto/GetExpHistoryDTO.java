package littleprince.member.query.dto;

import lombok.Builder;
import lombok.Getter;

import java.sql.Date;

@Getter
@Builder
public class GetExpHistoryDTO {
    private Long historyId;
    private Long projectId;
    private String title;
    private Date endDate;
    private int expPoint;
}
