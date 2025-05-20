package littleprince.ai.query.controller;

import littleprince.ai.query.dto.response.GeneratePlaneResponse;
import littleprince.ai.query.service.AiQueryService;
import littleprince.common.dto.ApiResponse;
import littleprince.config.security.model.CustomUserDetail;
import littleprince.plan.query.dto.response.ShortListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor()
@RequestMapping("/ai")
public class AiQueryController {

    private final AiQueryService aiQueryService;

    @GetMapping("/plan")
    public ResponseEntity<ApiResponse<GeneratePlaneResponse>> generateAiPlan(
            @AuthenticationPrincipal CustomUserDetail userDetail
            ){
        GeneratePlaneResponse response = aiQueryService.generateAiPlan(userDetail);
        return ResponseEntity.ok(ApiResponse.success(response));
    }
}
