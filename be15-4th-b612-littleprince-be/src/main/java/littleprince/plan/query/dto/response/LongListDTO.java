package littleprince.plan.query.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.sql.Date;

@Getter
@Builder
public class LongListDTO {
    private Long projectId;
    private Long memberId;
    private String title;
    private Date startDate;
    private Date endDate;
}
