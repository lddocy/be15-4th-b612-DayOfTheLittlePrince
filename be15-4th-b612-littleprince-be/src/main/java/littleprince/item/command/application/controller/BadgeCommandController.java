package littleprince.item.command.application.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import littleprince.common.dto.ApiResponse;
import littleprince.config.security.model.CustomUserDetail;
import littleprince.item.command.application.dto.request.BadgeSelectRequest;
import littleprince.item.command.application.service.BadgeCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "칭호")
@RestController
@RequestMapping("/badges")
@RequiredArgsConstructor
public class BadgeCommandController {

    private final BadgeCommandService badgeCommandService;

    @Operation(summary = "칭호 선택",description = "사용자는 원하는 칭호를 선택할 수 있다.")
    @PatchMapping("/select")
    public ResponseEntity<ApiResponse<Void>> selectBadge(@AuthenticationPrincipal CustomUserDetail user,
                                                         @RequestBody BadgeSelectRequest request) {
        Long memberId = user.getMemberId();
        badgeCommandService.selectBadge(memberId, request.getBadgeId());
        return ResponseEntity.ok(ApiResponse.success(null));
    }
}
