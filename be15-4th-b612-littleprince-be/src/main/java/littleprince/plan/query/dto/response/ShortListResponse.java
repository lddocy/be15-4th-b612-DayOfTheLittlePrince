package littleprince.plan.query.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class ShortListResponse {
    private final List<ShortListDTO> shortList;
}
