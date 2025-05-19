package littleprince.plan.query.dto.response;

import littleprince.common.domain.StatusType;
import lombok.Builder;
import lombok.Getter;

import java.sql.Date;


@Getter
@Builder
public class ShortListDTO {
    private Long taskId;
    private Long memberId;
    private String content;
    private StatusType isChecked;
    private Date date;
}