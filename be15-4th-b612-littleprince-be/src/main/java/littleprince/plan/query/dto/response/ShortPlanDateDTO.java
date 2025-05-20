package littleprince.plan.query.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.sql.Date;

@Getter
@Builder
public class ShortPlanDateDTO {
    private Date date;
}
