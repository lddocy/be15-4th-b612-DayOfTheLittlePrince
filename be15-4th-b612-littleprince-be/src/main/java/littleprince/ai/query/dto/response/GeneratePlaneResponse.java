package littleprince.ai.query.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class GeneratePlaneResponse {
    private List<String> generatePlanList;
}
