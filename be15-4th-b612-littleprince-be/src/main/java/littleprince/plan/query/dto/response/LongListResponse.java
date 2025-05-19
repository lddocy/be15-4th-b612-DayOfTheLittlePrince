package littleprince.plan.query.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class LongListResponse {
    private List<LongListDTO> longListDTOS;
}
