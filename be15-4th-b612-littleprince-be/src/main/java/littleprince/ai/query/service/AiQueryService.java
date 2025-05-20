package littleprince.ai.query.service;

import littleprince.ai.query.dto.response.GeneratePlaneResponse;
import littleprince.config.security.model.CustomUserDetail;
import littleprince.plan.query.dto.response.ShortListResponse;
import org.springframework.stereotype.Service;

@Service
public interface AiQueryService {
    GeneratePlaneResponse generateAiPlan(CustomUserDetail userDetail);
}
