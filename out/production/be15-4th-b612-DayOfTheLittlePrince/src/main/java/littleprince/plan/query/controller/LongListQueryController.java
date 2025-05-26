package littleprince.plan.query.controller;

import littleprince.common.dto.ApiResponse;
import littleprince.config.security.model.CustomUserDetail;
import littleprince.plan.query.dto.response.LongDetailResponse;
import littleprince.plan.query.dto.response.LongListResponse;
import littleprince.plan.query.dto.response.LongPlanResponse;
import littleprince.plan.query.service.LongListQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/plan")
public class LongListQueryController {

    private final LongListQueryService longListQueryService;

    /* 장기 플랜 전체 조회 */
    @GetMapping("/long")
    public ResponseEntity<ApiResponse<LongPlanResponse>> getLongPlan(
            @AuthenticationPrincipal CustomUserDetail customUserDetail
    )
    {
        Long memberId = customUserDetail.getMemberId();

        LongPlanResponse response = longListQueryService.getLongPlan(memberId);

        return ResponseEntity.ok(ApiResponse.success(response));
    }

    /* 장기 플랜 목록 조회 */
    @GetMapping("/long/{date}")
    public ResponseEntity<ApiResponse<LongListResponse>> getLongList(
            @AuthenticationPrincipal CustomUserDetail customUserDetail,
            @PathVariable Date date
            )
    {
        Long memberId = customUserDetail.getMemberId();
        LongListResponse response = longListQueryService.getLongList(memberId, date);

        return ResponseEntity.ok(ApiResponse.success(response));
    }

    /* 장기 플랜 상세 조회 */
    @GetMapping("/long/{date}/{projectId}")
    public ResponseEntity<ApiResponse<LongDetailResponse>> getLongDetail(
            @AuthenticationPrincipal CustomUserDetail customUserDetail,
            @PathVariable Date date,
            @PathVariable Long projectId
    )
    {
        Long memberId = customUserDetail.getMemberId();
        LongDetailResponse response = longListQueryService.getLongDetail(memberId, date, projectId);

        return ResponseEntity.ok(ApiResponse.success(response));
    }
}
