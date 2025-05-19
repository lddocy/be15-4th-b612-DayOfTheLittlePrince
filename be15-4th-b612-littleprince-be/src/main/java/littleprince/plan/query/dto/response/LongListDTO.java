package littleprince.plan.query.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LongListDTO {
    private Long projectId;
    private Long memberId;
    private String title;
}
