package littleprince.ai.query.service;

import littleprince.config.security.model.CustomUserDetail;
import littleprince.plan.query.dto.response.ShortListResponse;
import org.springframework.stereotype.Service;

@Service
public interface AiQueryService {
    ShortListResponse generateAiPlan(CustomUserDetail userDetail);
}
