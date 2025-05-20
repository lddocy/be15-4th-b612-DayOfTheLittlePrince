package littleprince.plan.query.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.sql.Date;

@Getter
@Builder
public class LongPlanDTO {
    private Long projectId;
    private String title;
    private Date startDate;
    private Date endDate;
}
