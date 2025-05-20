package littleprince.member.query.controller;

import littleprince.common.dto.ApiResponse;
import littleprince.config.security.model.CustomUserDetail;
import littleprince.member.query.dto.response.CurExpResponse;
import littleprince.member.query.service.ExpQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/curexp")
@RequiredArgsConstructor
public class ExpQueryController {

    private final ExpQueryService expQueryService;

    @GetMapping
    public ResponseEntity<ApiResponse<CurExpResponse>> getMyExp(@AuthenticationPrincipal CustomUserDetail user) {
        Long memberId = user.getMemberId();
        CurExpResponse response = expQueryService.getUserExp(memberId);
        return ResponseEntity.ok(ApiResponse.success(response));
    }
}
