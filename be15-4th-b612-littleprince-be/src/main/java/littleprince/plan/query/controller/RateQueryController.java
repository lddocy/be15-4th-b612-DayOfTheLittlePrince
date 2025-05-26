package littleprince.plan.query.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import littleprince.common.dto.ApiResponse;
import littleprince.config.security.model.CustomUserDetail;
import littleprince.plan.query.dto.response.RateResponse;
import littleprince.plan.query.service.RateQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "달성률")
@RestController
@RequestMapping("plans")
@RequiredArgsConstructor
public class RateQueryController {

    private final RateQueryService rateQueryService;

    @GetMapping("/rate")
    @Operation(summary = "달성률 조회",description = "사용자는 본인의 플랜 달성률을 조회할 수 있다.")
    public ResponseEntity<ApiResponse<RateResponse>> getCompletionRate(@AuthenticationPrincipal CustomUserDetail user) {
        Long memberId = user.getMemberId();
        RateResponse response = rateQueryService.getCompletionRate(memberId);
        return ResponseEntity.ok(ApiResponse.success(response));
    }
}
