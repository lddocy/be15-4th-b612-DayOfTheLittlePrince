package littleprince.plan.query.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.sql.Date;

@Getter
@Builder
public class AiShortPlanDTO {
    private String content;
    private Date date;
}
