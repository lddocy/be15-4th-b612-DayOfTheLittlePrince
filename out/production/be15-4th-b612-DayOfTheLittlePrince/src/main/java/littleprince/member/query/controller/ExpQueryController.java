package littleprince.member.query.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import littleprince.common.dto.ApiResponse;
import littleprince.config.security.model.CustomUserDetail;
import littleprince.member.query.dto.response.CurExpResponse;
import littleprince.member.query.dto.response.GetExpHistoryResponse;
import littleprince.member.query.service.ExpQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "프로필")
@RestController
@RequestMapping("/curexp")
@RequiredArgsConstructor
public class ExpQueryController {

    private final ExpQueryService expQueryService;

    @Operation(summary = "경험치 조회",description = "사용자는 자신의 경험치를 조회할 수 있다.")
    @GetMapping
    public ResponseEntity<ApiResponse<CurExpResponse>> getMyExp(@AuthenticationPrincipal CustomUserDetail user) {
        Long memberId = user.getMemberId();
        CurExpResponse response = expQueryService.getUserExp(memberId);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @Operation(summary = "획득 경험치 조회",description = "사용자는 자신의 경험치를 조회할 수 있다.")
    @GetMapping("/get")
    public ResponseEntity<ApiResponse<GetExpHistoryResponse>> getExpHistory(
            @AuthenticationPrincipal CustomUserDetail customUserDetail
    )
    {
        Long memberId = customUserDetail.getMemberId();
        GetExpHistoryResponse response = expQueryService.getExpHistory(memberId);
        return ResponseEntity.ok(ApiResponse.success(response));
    }
}
