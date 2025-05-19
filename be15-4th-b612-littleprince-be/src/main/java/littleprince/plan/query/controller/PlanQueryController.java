package littleprince.plan.query.controller;

import littleprince.common.dto.ApiResponse;
import littleprince.config.security.model.CustomUserDetail;
import littleprince.plan.query.dto.response.ShortListResponse;
import littleprince.plan.query.service.ShortListQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Date;

@RestController
    @RequestMapping("/plan")
    @RequiredArgsConstructor
    public class PlanQueryController {

        private final ShortListQueryService planQueryService;

        /* 단기리스트 조회 */
        @GetMapping("/short/{date}")
        public ResponseEntity<ApiResponse<ShortListResponse>> getShortList(
                @AuthenticationPrincipal CustomUserDetail customUserDetail,
                @PathVariable Date date
        )
    {
        Long memberId =  customUserDetail.getMemberId();

        ShortListResponse response = planQueryService.getShortList(memberId, date);

        return ResponseEntity.ok(ApiResponse.success(response));
    }
}
