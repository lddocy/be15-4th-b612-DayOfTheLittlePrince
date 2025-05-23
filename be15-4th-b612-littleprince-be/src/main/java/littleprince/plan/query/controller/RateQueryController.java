package littleprince.plan.query.controller;

import littleprince.common.dto.ApiResponse;
import littleprince.config.security.model.CustomUserDetail;
import littleprince.plan.query.dto.response.RateResponse;
import littleprince.plan.query.service.RateQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("plans")
@RequiredArgsConstructor
public class RateQueryController {

    private final RateQueryService rateQueryService;

    @GetMapping("/rate")
    public ResponseEntity<ApiResponse<RateResponse>> getCompletionRate(@AuthenticationPrincipal CustomUserDetail user) {
        Long memberId = user.getMemberId();
        RateResponse response = rateQueryService.getCompletionRate(memberId);
        return ResponseEntity.ok(ApiResponse.success(response));
    }
}
